<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var = "ROOT" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<link rel="icon" type="image/png" sizes="16x16"
href="/xciweb01/img/favicon16.png"/>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <video width="640" height="344" controls autoplay="autoplay">
        <source src="video/sample.mp4" type="video/mp4">
        video tag not find.
    </video>
</body>
</html>