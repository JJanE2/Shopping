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
        <c:forEach var="order" items="${orders}">
          <section class="border p-3 mb-4">
              <!-- 주문 정보 -->
              <div class="d-flex justify-content-between mb-2">
                  <div>
                      <a href="/orders/${order.id}">
                        <fmt:formatDate value="${order.orderDate}" pattern="yyyy년 M월 d일"/>
                      </a>
                  </div>
                  <div>
                      <span class="text-muted">
                      <c:choose>
                          <c:when test="${order.status == 'PAID'}">결제완료</c:when>
                          <c:when test="${order.status == 'SHIPPING'}">배송 중</c:when>
                          <c:when test="${order.status == 'COMPLETED'}">배송 완료</c:when>
                          <c:when test="${order.status == 'CANCELED'}">주문 취소</c:when>
                          <c:otherwise>알 수 없음</c:otherwise>
                      </c:choose>
                      </span>
                  </div>
              </div>
              <!-- 주문상품 테이블 -->
              <table class="table table-sm text-center align-middle mb-0">
                  <thead class="table-light">
                  <tr>
                      <th>상품명</th>
                      <th>수량</th>
                      <th>가격</th>
                      <th>리뷰</th>
                  </tr>
                  </thead>
                  <tbody>
                  <c:forEach var="product" items="${order.products}">
                      <tr>
                          <td><a href="/products/${product.id}">${product.productName}</a></td>
                          <td>${product.quantity}</td>
                          <td>${product.price}</td>
                          <td>
                              <c:set var="isCompleted" value="${order.status eq 'COMPLETED'}"/>
                              <!-- 리뷰작성 안했으면 작성, 했으면 수정 버튼 표시 -->
                              <c:if test="${empty product.reviewId}">
                                <a class="btn btn-sm btn-outline-success ${!isCompleted ? 'disabled' : ''}"
                                  href="/reviews/new?orderProductId=${product.id}">
                                  리뷰 작성
                                </a>
                              </c:if>
                              <c:if test="${product.reviewId != null}">
                                <a class="btn btn-sm btn-outline-success" href="/reviews/${product.reviewId}/edit">
                                  리뷰 수정
                                </a>
                                <button
                                    type="button"
                                    class="btn btn-sm btn-outline-danger"
                                    data-bs-toggle="modal"
                                    data-bs-target="#review-delete-modal"
                                    data-review-id="${product.reviewId}">
                                    삭제
                                </button>
                              </c:if>
                          </td>
                      </tr>
                  </c:forEach>
                  </tbody>
              </table>
              <hr>
              <div class="d-flex justify-content-between align-items-center">
                <div>
                  <span class="text-danger fw-bold">총 금액 : ${order.totalPrice}</span>
                </div>
                <div>
                  <button
                      type="button"
                      class="btn btn-sm btn-outline-danger"
                      data-bs-toggle="modal"
                      data-bs-target="#cancelModal"
                      data-order-id="${order.id}">
                      취소
                  </button>
                </div>
              </div>
          </section>
        </c:forEach>
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

// 리뷰 삭제 모달
<div class="modal fade" id="review-delete-modal" tabindex="-1" aria-labelledby="deleteReviewLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteReviewLabel">리뷰 삭제</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p id="deleteMessage">정말 삭제 하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">닫기</button>
        <button type="button" id="confirmReviewDeleteBtn" class="btn btn-danger">삭제</button>
      </div>
    </div>
  </div>
</div>
<script>
let cancelOrderId = null; // 모달에서 취소할 주문 ID 저장
let deleteReviewId = null; // 모달에서 삭제할 리뷰 ID 저장

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
            window.location.reload();
        });
    });

// 모달 열릴 때
const reviewDeleteModal = document.getElementById('review-delete-modal');
reviewDeleteModal.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget; // 클릭한 취소 버튼
    deleteReviewId = button.getAttribute('data-review-id');

    // 모달 메시지 업데이트
    const message = reviewDeleteModal.querySelector('#deleteMessage');
    message.innerText = "리뷰를 정말 삭제하시겠습니까?";
});

// 확인 버튼 클릭 시 fetch DELETE
document.getElementById("confirmReviewDeleteBtn").addEventListener("click", () => {
    if (!deleteReviewId) return;
    fetch(`/api/reviews/` + deleteReviewId, {
        method: "DELETE",
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
        window.location.reload();
    });
});

</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</main>
</section>
</body>
</html>