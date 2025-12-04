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
                                <span class="text-muted" id="status-${order.id}">
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
                                <button class="btn btn-primary btn-sm change-status-btn"
                                        data-bs-toggle="modal"
                                        data-bs-target="#statusModal"
                                        data-order-id="${order.id}">
                                    상태 변경
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </main>
</section>

<div class="modal fade" id="statusModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title">주문 상태 변경</h5>
                <button type="button" class="btn-close" data-bs-dismiss="modal"></button>
            </div>

            <div class="modal-body">
                다음 단계로 상태를 변경하시겠습니까?
            </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">아니오</button>
                <button type="button" id="confirmStatusBtn" class="btn btn-primary">예</button>
            </div>
        </div>
    </div>
</div>

<script>
    // orderId 초기화
    let id = null;

    // nextStatus 영어에서 한글로 표시
    const statusLabels = {
        PAID: "결제완료",
        SHIPPING: "배송 중",
        COMPLETED: "배송 완료",
        CANCELED: "주문 취소"
    };
    document.querySelectorAll(".change-status-btn").forEach(btn => {
        btn.addEventListener("click", function () {
            id = Number(this.dataset.orderId);
        });
    });

    // 비동기 요청
    document.getElementById("confirmStatusBtn").addEventListener("click", function () {
        fetch(`/api/orders/` +  id + `/status/next`, {
            method: "POST"
        })
        .then(res => res.json())
        .then(data => {

            // UI 업데이트
            document.getElementById(`status-`+ id).innerText = statusLabels[data.nextStatus];

            // 모달 닫기
            const modal = bootstrap.Modal.getInstance(document.getElementById("statusModal"));
            modal.hide();

            // 알림
            alert(data.message);
        })
        .catch(err => {
            alert("상태 변경 중 오류가 발생했습니다.");
            console.error(err);
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>