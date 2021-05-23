package com.developia.upload;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.developia.fx.ModelAndView;

@Controller
public class FileUploadController {
    private static final String SAVE_PATH = "C:/dev/workspace_2021-04-06/uploadExam/src/main/webapp/WEB-INF/video/";
    //@PostMapping("upload")    
    
    @RequestMapping("upload")
    public ModelAndView upload(
            @RequestParam(value="file1", required = false) MultipartFile mf, HttpServletRequest request, HttpServletResponse response) {
            ModelAndView mav = new ModelAndView("vodMain");
 
 
            String originalFileName = mf.getOriginalFilename();
            //long fileSize = mf.getSize();
            String safeFile =  SAVE_PATH + originalFileName;
            //File safeFile =  new File(SAVE_PATH + System.currentTimeMillis() + originalFileName);
            
            
            try {
                mf.transferTo(new File(safeFile));
                
                response.sendRedirect(mav.getViewName());
            	//mf.transferTo(safeFile);
               } catch (IllegalStateException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
        
        return null;
    }

}
