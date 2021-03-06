package com.developia.upload;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.developia.fx.ModelAndView;

@Controller
public class StreamingController{
	private final String FOLDER_MOVIE = "C:/dev/workspace_2021-04-06/uploadExam/src/main/webapp/WEB-INF/video/"; 
	//<source src="video/sample.mp4" type="video/mp4">
	
	@RequestMapping(value="/video/{video_name:.+}", method = RequestMethod.GET)
	public String stream(@PathVariable("video_name") String video_name, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException, IOException {
		
		//확장자 확인 //
		String[] filename_seperate = video_name.split("\\.");
		
		
		String exp;
		if(filename_seperate.length <= 1) {
			//확장자 에러//
			throw new RuntimeException("Wrong file name. You need to include expand file name");		
		}
		else {
			exp = filename_seperate[1];
		}

		File file = new File(FOLDER_MOVIE + video_name);

		
		if(!file.exists()) throw new FileNotFoundException();
		
		//임의로 파일에 접근
		RandomAccessFile randomFile = new RandomAccessFile(file, "r");
		long rangeStart = 0; //요청범위 시작위치
		long rangeEnd = 0;	 // 끝 위치
		boolean isPart = false;  // 부분 요청 true, 전체 요청 false
		
		try {
			long movieSize = randomFile.length(); //동영상 파일 크기
			String range = request.getHeader("range"); // 스트림 요청 범위
			if(range != null) { // 브라우저마다 다른데 보통 bytes = {start} - {end}
				if(range.endsWith("-")) { //요청 range에 end 값 없을 경우
					range = range+(movieSize - 1); 
				}
				int idxm = range.trim().indexOf("-");
				rangeStart = Long.parseLong(range.substring(6,idxm));
				rangeEnd = Long.parseLong(range.substring(idxm+1));
				if(rangeStart > 0) {
					isPart = true;
				}
			}else {
				rangeStart = 0;
				rangeEnd = movieSize - 1;
			}
			
			long partSize = rangeEnd - rangeStart + 1; // 전송 파일 크기
			response.reset(); // 전송 시작
			
			response.setStatus(isPart ? 206: 200); // 전체 요청일 경우 200, 부분일 경우 206 반환
			response.setContentType("video/mp4"); //mime type
			
			//전송 내용을 헤드에 넣음
			response.setHeader("Content-Range", "bytes "+rangeStart+"-"+rangeEnd+"/"+movieSize);
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-Length", ""+partSize);

			OutputStream out = response.getOutputStream();
			//동영상 파일의 전송시작 위치 지정
			randomFile.seek(rangeStart);
			
			/*
			 * 파일 전송 . java io는 1회 전송 byte 수가 int 로 지정.
			 * 동영상 파일은 int로 처리 안되는 큰 파일 있기 때문에 8kb로 잘라서 파일 크기로 인한 문제 없도록 구현
			 */
			int bufferSize = 8*1024;
			byte[] buf = new byte[bufferSize];
			do {
				int block = partSize > bufferSize ? bufferSize : (int)partSize;
				int len = randomFile.read(buf, 0, block);
				out.write(buf, 0,len);
				partSize -= block;
			}while(partSize > 0);
		}catch(IOException e) {
			// 전송 중 브라우저 닫거나 화면 전환한 경우 전송 취소
			// progressBar 클릭하면 클릭한 위치값으로 재요청 들어와서 전송 취소
		}finally {
			randomFile.close();
		}
		return null;
	}
	
	@GetMapping(value="/vodMain")
	public ModelAndView crawler(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav = new ModelAndView("vodMain");
		
		return mav;
	}
			
}
