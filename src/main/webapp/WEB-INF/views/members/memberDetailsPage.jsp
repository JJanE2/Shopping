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
        <p class="text start"><strong>회원유형 : </strong>
            <c:choose>
                <c:when test="${member.role == 'CUSTOMER'}">손님</c:when>
                <c:otherwise>사장님</c:otherwise>
            </c:choose>
        </p>

        <div class="d-flex justify-content-center gap-3 mb-3">
            <a href="/members/${member.id}/edit">
                <button type="button" class="btn btn-primary">수정하기</button>
            </a>
            <c:choose>
                <c:when test="${member.role == 'OWNER'}">
                    <a href="/products/1/edit">
                        <button type="button" class="btn btn-success">상품 수정</button>
                    </a>
                </c:when>
            </c:choose>
            <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            <button type="button" class="btn btn-danger" data-bs-toggle="modal" data-bs-target="#deleteModal">
                회원 탈퇴
            </button>
        </div>
    </main>
</section>

<!-- Bootstrap Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteModalLabel">회원 탈퇴</h5>
      </div>
      <div class="modal-body">
        정말로 탈퇴하시겠습니까?
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-danger" id="confirmDeleteBtn">예</button>
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
      </div>
    </div>
  </div>
</div>

<script>
    document.getElementById("confirmDeleteBtn").addEventListener("click", () => {

        const id = ${member.id};

        fetch(`/api/members/${id}`, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => response.text())
        .then(message => {
            alert(message);
            window.location.href= "/";
        })
        .catch(err => {
            console.error(err);
            alert("서버 오류가 발생했습니다.");
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>