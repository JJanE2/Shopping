<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
    <title>Shopping</title>
<body>
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container d-flex align-items-center w-75 min-vh-100">
    <main class="container">
        <div class="list-group">
            <table class="table table-striped table-hover align-middle">
                <thead class="table-light">
                    <tr>
                        <th scope="col">주문 ID</th>
                        <th scope="col">주문날짜</th>
                        <th scope="col">금액</th>
                        <th scope="col">상태</th>
                        <th scope="col">관리</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="order" items="${orders}">
                        <tr>
                            <td>
                                <a href="/orders/${order.id}"><span>${order.id}</span></a>
                            </td>
                            <td>
                                <fmt:formatDate value="${order.orderDate}" pattern="yyyy년 M월 d일"/>
                            </td>
                            <td>
                                <span>${order.totalPrice}</span>
                            </td>
                            <td>
                                <span class="text-muted">
                                    <c:choose>
                                        <c:when test="${order.status == 'PAID'}">결제완료</c:when>
                                        <c:when test="${order.status == 'SHIPPING'}">배송 중</c:when>
                                        <c:when test="${order.status == 'COMPLETED'}">배송 완료</c:when>
                                        <c:when test="${order.status == 'CANCELED'}">주문 취소</c:when>
                                        <c:otherwise>알 수 없음</c:otherwise>
                                    </c:choose>
                                </span>
                            </td>
                            <td>
                                <a href="/orders/${order.id}/edit" class="btn btn-sm btn-outline-primary me-1">수정</a>
                                <button
                                    type="button"
                                    class="btn btn-sm btn-outline-danger"
                                    data-bs-toggle="modal"
                                    data-bs-target="#cancelModal"
                                    data-order-id="${order.id}">
                                    취소
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</section>
</body>
</html>