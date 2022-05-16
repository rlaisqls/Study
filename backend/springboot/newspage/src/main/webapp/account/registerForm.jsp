<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML>
<head>
    <meta charset="UTF-8">
    <title>회원가입</title>
    <style>
        .container{text-align: center; margin-top: 10%;}
        .form-group{margin: 10px}
        #password{width: 150px;}
        #passwordcheck{width: 115px;}
        button{margin: 20px;}
    </style>
</head>
<body>
<div class="container">
    <h2>회원가입</h2>
    <form action="/register/new" method="post">
        <div class="form-group">
            <label for="username">아이디</label>
            <input type="text" id="username" name="username">
        </div>
        <div class="form-group">
            <label for="password">비밀번호</label>
            <input type="text" id="password" name="password">
        </div>
        <div class="form-group">
            <label for="passwordcheck">비밀번호 확인</label>
            <input type="text" id="passwordcheck" name="passwordcheck">
        </div>
        <span>
        <p id="valid" class="alert alert-danger">${exception}</p>
        </span>
        <button type="submit">회원가입</button>
    </form>
</div>
</body>
</html>