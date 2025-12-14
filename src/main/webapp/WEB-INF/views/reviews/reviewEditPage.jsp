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
        <form id="review-update-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">리뷰 수정 페이지</p>
            <div class="mb-3">
                <label for="content" class="form-label">내용을 수정해 주세요</label>
                <textarea class="form-control" id="content" name="content" rows="3" required>${review.content}</textarea>
            </div>
            <div class="mb-3">
                <label for="rating" class="form-label">별점은 수정할 수 없습니다</label>
                <select class="form-select" id="rating" disabled>
                  <option value="5" ${review.rating == 5 ? 'selected' : ''}>5점</option>
                  <option value="4" ${review.rating == 4 ? 'selected' : ''}>4점</option>
                  <option value="3" ${review.rating == 3 ? 'selected' : ''}>3점</option>
                  <option value="2" ${review.rating == 2 ? 'selected' : ''}>2점</option>
                  <option value="1" ${review.rating == 1 ? 'selected' : ''}>1점</option>
                </select>
            </div>
            <input type="hidden" name="id" id="id" value="${review.id}">
            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">수정완료</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
        </form>
    </main>
</section>
<script>
    document.getElementById("review-update-form").addEventListener("submit", async function(event) {
        event.preventDefault();
        const id = document.getElementById("id").value;
        const reviewUpdateDto = {
            id: id,
            content: document.getElementById("content").value,
        };
        $.ajax({
            url: "/api/reviews/" + id,
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(reviewUpdateDto),
            success: function (response) {
                const { message, productId } = response;
                alert(message);
                // 리뷰 수정 완료시 해당 리뷰가 있는 상품 상세 페이지로 이동
                location.href = "/products/" + productId;
            },
            error: function (jqXHR) {
                const { message, productId } = jqXHR.responseJSON;
                alert(message);
                window.location.reload();
            }
        });
    });
</script>
</body>
</html>