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
        <form id="review-create-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">리뷰 작성 페이지</p>
            <div class="mb-3">
                <label for="content" class="form-label">내용을 작성해주세요</label>
                <textarea class="form-control" id="content" name="content" rows="3" required></textarea>
            </div>
            <div class="mb-3">
                <label for="rating" class="form-label">별점을 선택해주세요</label>
                <select class="form-select" id="rating">
                  <option value="5" selected>5점</option>
                  <option value="4">4점</option>
                  <option value="3">3점</option>
                  <option value="2">2점</option>
                  <option value="1">1점</option>
                </select>
            </div>
            <input type="hidden" name="memberId" id="memberId" value="${memberId}">
            <input type="hidden" name="productId" id="productId" value="${productId}">
            <input type="hidden" name="orderProductId" id="orderProductId" value="${orderProductId}">
            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">작성완료</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
        </form>
    </main>
</section>
<script>
    document.getElementById("review-create-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        const reviewCreateDto = {
            memberId: document.getElementById("memberId").value,
            productId: document.getElementById("productId").value,
            orderProductId: document.getElementById("orderProductId").value,
            content: document.getElementById("content").value,
            rating: document.getElementById("rating").value
        };
        $.ajax({
            url: "/api/reviews",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(reviewCreateDto),
            success: function (response) {
                const { message, reviewId } = response;
                alert(message);
                location.href = "/";
            },
            error: function () {
                alert("리뷰 등록 중 오류가 발생했습니다.");
                window.location.reload();
            }
        });
    });
</script>
</body>
</html>