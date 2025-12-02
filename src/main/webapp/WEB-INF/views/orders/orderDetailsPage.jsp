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
        <p class="text-center text-muted">주문 상세 페이지</p>

        <table class="table">
            <thead>
                <tr>
                    <th>상품명</th>
                    <th>가격</th>
                    <th>수량</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${products}">
                    <tr>
                        <td><a href="/products/${item.id}">${item.productName}</a></td>
                        <td>${item.price} 원</td>
                        <td>${item.quantity} 개</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div class="d-flex justify-content-between align-items-center mb-3">
        <span>주문 상태 :
            <c:choose>
                <c:when test="${order.status == 'PAID'}">결제완료</c:when>
                <c:when test="${order.status == 'SHIPPING'}">배송 중</c:when>
                <c:when test="${order.status == 'COMPLETED'}">배송 완료</c:when>
                <c:when test="${order.status == 'CANCELED'}">주문 취소</c:when>
                <c:otherwise>알 수 없음</c:otherwise>
            </c:choose>
        </span>
        <span class="text-danger fw-bold">총 금액: ${order.totalPrice}원</span>
        </div>
        <hr>
        <div class="d-flex justify-content-center mb-3">
            <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
        </div>
    </main>
</section>
</body>
</html>