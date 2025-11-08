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
        <form id="member-update-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">정보수정 페이지</p>
            <div class="mb-3">
                <label for="loginId" class="form-label">아이디는 변경 불가합니다</label>
                <input type="text" class="form-control" id="loginId" name="loginId" value="${member.loginId}" readonly disabled>
            </div>
            <div class="mb-3">
                <label for="nickname" class="form-label">새 닉네임을 입력해주세요</label>
                <input type="text" class="form-control" id="nickname" name="nickname" value="${member.nickname}" required>
            </div>
            <div class="mb-3">
                <label for="password" class="form-label">새 비밀번호를 입력해주세요</label>
                <input type="password" class="form-control" id="password" name="password" required>
            </div>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">수정하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>

            <input type="hidden" id="memberId" name="memberId" value="${member.id}">
        </form>
    </main>
</section>

<script>
    document.getElementById("member-update-form").addEventListener("submit", function(event) {
        event.preventDefault();

        const memberId = Number(document.getElementById("memberId").value);

        const formData = {
            id: memberId,
            nickname: document.getElementById("nickname").value,
            password: document.getElementById("password").value
        };

        fetch(`/api/members/${memberId}`, {
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
          window.location.href = `/members/${memberId}`; // 성공시 회원정보 화면으로 이동
        })
        .catch(error => {
          alert(error.message);
          window.location.reload();  // 실패시 새로고침
        });
    });
</script>
</body>
</html>