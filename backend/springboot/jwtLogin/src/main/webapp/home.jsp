<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>홈</title>
</head>
<body>
메인메뉴입니다
<p><a href="/register">회원가입</a></p>
<p><a href="/login">로그인</a></p>
<p><a href="/logout">로그아웃</a></p>
</body>
</html>