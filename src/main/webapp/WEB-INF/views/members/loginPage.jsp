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
        <form id="member-login-form" class="mb-0">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">로그인 페이지</p>
            <div class="form-floating mb-3">
                <input type="text" class="form-control" id="loginId" name="loginId" placeholder="" required>
                <label for="loginId">아이디를 입력해주세요</label>
            </div>
            <div class="form-floating mb-3">
                <input type="password" class="form-control" id="password" name="password"  placeholder="" required>
                <label for="password">비밀번호를 입력해주세요</label>
            </div>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">로그인</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
            <hr>
            <div class="text-center">
                <a href="https://kauth.kakao.com/oauth/authorize?client_id=${kakaoClientId}&redirect_uri=${kakaoRedirectUri}&response_type=code">
                    <img src="/resources/images/kakao_login_medium_wide.png"></img>
                </a>
                <p class="text-muted small mt-2 mb-0">
                    ( 카카오 로그인 시 <strong>손님</strong>으로 가입됩니다 )
                </p>
            </div>
        </form>
    </main>
</section>

<script>
    document.getElementById("member-login-form").addEventListener("submit", function(event) {
        event.preventDefault();
        const formData = {
            loginId: document.getElementById("loginId").value,
            password: document.getElementById("password").value
        };

        fetch("/api/login", {
            method: "POST",
            headers: {
              "Content-Type": "application/json"
            },
            body: JSON.stringify(formData)
        })
        .then(response => {
            if (!response.ok) {
              return response.text().then(text => { throw new Error(text); });
            }
            return response.text();
          })
        .then(message => {
          alert(message);
          window.location.href = "/"; // 성공시 메인화면 이동
        })
        .catch(error => {
          alert(error.message);
          window.location.reload();  // 실패시 새로고침
        });
    });
</script>

</body>
</html>