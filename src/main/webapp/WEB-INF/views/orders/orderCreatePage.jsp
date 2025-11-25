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
        <form id="order-create-form" action="/orders" method="post">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">주문확인 페이지</p>
            <hr>

            <p class="fs-4 text-primary fw-bold">
                <span id="totalPriceText">${orderCreateDto.totalPrice}</span>
            </p>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">주문하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
            <input type="hidden" name="totalPrice" id="totalPrice" value="${orderCreateDto.totalPrice}">
            <input type="hidden" name="memberId" id="memberId" value="${memberId}">
            <c:forEach var="item" items="${orderCreateDto.products}" varStatus="status">
                <input type="hidden" name="products[${status.index}].productId" value="${item.productId}">
                <input type="hidden" name="products[${status.index}].productName" value="${item.productName}">
                <input type="hidden" name="products[${status.index}].price" value="${item.price}">
                <input type="hidden" name="products[${status.index}].quantity" value="${item.quantity}">
            </c:forEach>
        </form>
    </main>
</section>

<script>
    // 총 금액 표시
    document.addEventListener("DOMContentLoaded", () => {
      const el = document.getElementById("totalPriceText");
      const number = Number(el.textContent);
      el.textContent = "총 금액 : " + number.toLocaleString("ko-KR") + "원";
    });
</script>

</body>
</html>