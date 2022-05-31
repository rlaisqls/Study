<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <title>로그인</title>
    <style>
        .container{text-align: center; margin-top: 10%;}
        .form-group{margin: 10px}
        #password{width: 150px;}
        button{margin: 20px;}
    </style>
</head>
<body>
<div class="container">
    <h2>로그인</h2>
    <form action="/login/account" method="post">
        <div class="form-group">
            <label for="username">아이디</label>
            <input type="text" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="text" id="password" name="password">
        </div>
        ${error}
        <button type="submit">로그인</button> <br>
        계정이 없다면? <a href="/register">회원가입</a>
    </form>
</div>
</body>
</html>