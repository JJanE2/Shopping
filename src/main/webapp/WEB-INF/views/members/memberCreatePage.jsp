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
                <input type="text" class="form-control" id="loginId" name="loginId" placeholder="아이디는 최대 10자 까지 가능합니다" required>
                <div id="loginIdError" class="error-msg text-danger fw-bold pt-1"></div>
            </div>
            <div class="mb-3">
                <label for="nickname" class="form-label">닉네임</label>
                <input type="text" class="form-control" id="nickname" name="nickname" placeholder="닉네임은 최대 8자 까지 가능합니다" required>
                <div id="nicknameError" class="error-msg text-danger fw-bold pt-1"></div>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">비밀번호</label>
                <input type="password" class="form-control" id="password" name="password" required>
                <div id="passwordError" class="error-msg text-danger fw-bold pt-1"></div>
            </div>

            <div class="mb-3">
              <label class="form-label">회원 유형</label>
              <div class="form-check form-check-inline ms-3">
                <input class="form-check-input" type="radio" name="role" id="roleCustomer" value="CUSTOMER" checked>
                <label class="form-check-label" for="roleCustomer">손님</label>
              </div>
              <div class="form-check form-check-inline">
                <input class="form-check-input" type="radio" name="role" id="roleOwner" value="OWNER">
                <label class="form-check-label" for="roleOwner">사장</label>
              </div>
            </div>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">가입하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
        </form>
    </main>
</section>

<script>
// valid 실패 시 각 속성에 에러 메세지 표시 함수
function displayErrors(errors) {
    // 기존 에러 메시지 초기화
    document.querySelectorAll(".error-msg")
        .forEach(el => el.innerText = "");
    if (errors.loginId) {
        document.getElementById("loginIdError").innerText = errors.loginId;
    }
    if (errors.nickname) {
        document.getElementById("nicknameError").innerText = errors.nickname;
    }
    if (errors.password) {
        document.getElementById("passwordError").innerText = errors.password;
    }
}

    document.getElementById("member-create-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        const memberCreateDto = {
            loginId: document.getElementById("loginId").value,
            nickname: document.getElementById("nickname").value,
            password: document.getElementById("password").value,
            role: document.querySelector('input[name="role"]:checked').value
        };

        // valid 요청
        const memberValidDto = {
            loginId: document.getElementById("loginId").value,
            nickname: document.getElementById("nickname").value,
            password: document.getElementById("password").value
        };
        const validateResponse = await fetch("/api/members/validate", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(memberValidDto)
        });

        // errors 값 존재시 error message 표시 및 폼 제출 방지
        const errors = await validateResponse.json();
        if (Object.keys(errors).length > 0) {
            displayErrors(errors);   // 에러 표시
            return;
        }

        const response = await fetch("/api/members", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(memberCreateDto)
        });

        // 서버가 보낸 메시지 읽기
        const message = await response.text();

        if (response.ok) {
            alert(message);
            window.location.href = "/";
        } else {
            alert(message);
            window.location.reload();
        }
    });
</script>
</body>
</html>