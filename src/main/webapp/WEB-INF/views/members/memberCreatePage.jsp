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
        <form id="member-create-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">회원가입 페이지</p>
            <div class="mb-3">
                <label for="loginId" class="form-label">아이디</label>
                <input type="text" class="form-control" id="loginId" name="loginId" placeholder="아이디를 입력해주세요" required>
            </div>
            <div class="mb-3">
                <label for="nickname" class="form-label">닉네임</label>
                <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임을 입력해주세요" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">가입하기</button>
                <button type="button" class="btn btn-secondary">돌아가기</button>
            </div>
        </form>
    </main>
</section>

<script>
    document.getElementById("member-create-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        const memberCreateDto = {
            loginId: document.getElementById("loginId").value,
            nickname: document.getElementById("nickname").value,
            password: document.getElementById("password").value
        };

        const response = await fetch("/api/members", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(memberCreateDto)
        });

        if (response.ok) {
            alert("회원가입이 완료되었습니다!");
            window.location.href = "/";
        } else {
            alert("회원가입 실패");
        }
    });
</script>
</body>
</html>