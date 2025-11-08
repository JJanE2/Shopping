<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
    <title>Shopping</title>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container d-flex align-items-center w-75 min-vh-100">
    <main class="container border rounded shadow py-3">
        <h2 class="text-center mb-3">Shopping</h2>
        <p class="text-center text-muted">회원정보 페이지</p>

        <p class="text start"><strong>로그인 아이디 :</strong> ${member.loginId}</p>
        <p class="text start"><strong>닉네임 :</strong> ${member.nickname}</p>

        <div class="d-flex justify-content-center gap-3 mb-3">
            <a href="/members/${member.id}/edit">
                <button type="button" class="btn btn-primary">수정하기</button>
            </a>
            <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
        </div>
    </main>
</section>
</body>
</html>