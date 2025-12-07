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
        <form id="cart-form" action="/orders" method="post">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">장바구니 페이지</p>
            <c:choose>
                <%-- 장바구니 비어있는 경우 --%>
                <c:when test="${items.size() == 0}">
                    <p class="text-center text-muted">현재 장바구니가 비어 있습니다.</p>
                </c:when>
                <c:otherwise>
                    <!-- 장바구니 테이블 -->
                    <table class="table table-bordered text-center mb-4">
                        <thead class="table-light">
                            <tr>
                                <th>상품명</th>
                                <th>금액</th>
                                <th>수량</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="item" items="${items}" varStatus="status">
                                <tr>
                                    <td>${item.productName}</td>
                                    <td>${item.price}원</td>
                                    <td>${item.quantity}개</td>
                                </tr>
                                <input type="hidden" name="products[${status.index}].productId" value="${item.productId}">
                                <input type="hidden" name="products[${status.index}].productName" value="${item.productName}">
                                <input type="hidden" name="products[${status.index}].price" value="${item.price}">
                                <input type="hidden" name="products[${status.index}].quantity" value="${item.quantity}">
                            </c:forEach>
                        </tbody>
                    </table>
                </c:otherwise>
            </c:choose>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <c:if test="${items.size() != 0}">
                    <button type="submit" class="btn btn-primary">주문하기</button>
                </c:if>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
            <input type="hidden" name="memberId" id="memberId" value="${memberId}">
        </form>
    </main>
</section>
</body>
</html>