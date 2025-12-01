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
                                <span>${order.id}</span>
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
<div class="modal fade" id="cancelModal" tabindex="-1" aria-labelledby="cancelModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="cancelModalLabel">주문 취소</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p id="cancelMessage">정말 취소하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" id="confirmDeleteBtn" class="btn btn-danger">취소</button>
      </div>
    </div>
  </div>
</div>

<script>
let cancelOrderId = null; // 모달에서 취소할 주문 ID 저장

// 모달 열릴 때
    const cancelModal = document.getElementById('cancelModal');
    cancelModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget; // 클릭한 취소 버튼
        cancelOrderId = button.getAttribute('data-order-id');

        // 모달 메시지 업데이트
        const message = cancelModal.querySelector('#cancelMessage');
        message.innerText = "주문을 정말 취소하시겠습니까?";
    });

    // 확인 버튼 클릭 시 fetch DELETE
    document.getElementById("confirmDeleteBtn").addEventListener("click", () => {
        if (!cancelOrderId) return;

        fetch(`/api/orders/` + cancelOrderId + `/cancel`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            return response.text().then(message => {
                if (!response.ok) {
                    throw new Error(message);   // 서버가 보낸 메시지
                }
                return message;                 // 성공 메시지
            });
        })
        .then(message => {
            alert(message);
            window.location.reload();
        })
        .catch(err => {
            console.error(err);
            alert(err.message);                 // 에러 메시지 표시
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</main>
</section>
</body>
</html>