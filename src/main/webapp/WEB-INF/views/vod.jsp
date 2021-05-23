<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>lab.naminsik.com</title>
    <link href="https://vjs.zencdn.net/7.10.2/video-js.css" rel="stylesheet" />
    <style>
        body{
            margin: 0;
            padding: 0;
        }
        #video{
            width: 100%;
            height: 100vh;
        }
    </style>
</head>
<body>
<video id=video width=100% class="video-js" controls autoplay>
    <!-- <source src="d3w4bj25ewtd7d.cloudfront.net/파일명.m3u8" type="application/x-mpegURL"> -->
	<!--  <source src="https://d1hhwciywy00aw.cloudfront.net/mylist.m3u8" type="application/x-mpegURL">-->
	<!--  <source src="https://d2q0hnzyjsk9p6.cloudfront.net/mysample.mp4" type="application/x-mpegURL">-->
	<source src="https://d2q0hnzyjsk9p6.cloudfront.net/mysample.mp4" type="video/mp4">    
</video>
<!-- <script src="https://vjs.zencdn.net/7.8.2/video.min.js"></script> -->
<!-- <script src="https://cdnjs.cloudflare.com/ajax/libs/videojs-contrib-hls/5.15.0/videojs-contrib-hls.min.js"></script> -->
<!-- 
<script>
    var player = videojs('video');
    	//player.play();
</script>
 -->
</body>
</html>