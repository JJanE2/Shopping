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
            <c:forEach var="product" items="${products}">
                <div class="list-group-item d-flex justify-content-between align-items-center">
                    <div>
                        <a href="/products/${product.id}">
                            ${product.name}
                        </a>
                        <small class="text-muted">
                            <fmt:formatDate value="${product.createdAt}" pattern="yyyy-MM-dd"/>
                        </small>
                    </div>
                    <div>
                        <a href="/products/${product.id}/edit" class="btn btn-sm btn-primary me-1">수정</a>

                        <button
                            type="button"
                            class="btn btn-sm btn-danger"
                            data-bs-toggle="modal"
                            data-bs-target="#deleteModal"
                            data-product-id="${product.id}"
                            data-product-name="${product.name}">
                            삭제
                        </button>
                    </div>
                </div>
            </c:forEach>
        </div>


    </main>
</section>
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="deleteModalLabel" aria-hidden="true">
  <div class="modal-dialog">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="deleteModalLabel">상품 삭제</h5>
        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
      </div>
      <div class="modal-body">
        <p id="deleteMessage">정말 삭제하시겠습니까?</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">취소</button>
        <button type="button" id="confirmDeleteBtn" class="btn btn-danger">삭제</button>
      </div>
    </div>
  </div>
</div>

<script>
let deleteProductId = null; // 모달에서 삭제할 상품 ID 저장


// 모달 열릴 때
    const deleteModal = document.getElementById('deleteModal');
    deleteModal.addEventListener('show.bs.modal', event => {
        const button = event.relatedTarget; // 클릭한 삭제 버튼
        deleteProductId = button.getAttribute('data-product-id');
        const productName = button.getAttribute('data-product-name');

        console.log("targetPid = " + deleteProductId);
        console.log("targetPName = " + productName);


        // 모달 메시지 업데이트
        const message = deleteModal.querySelector('#deleteMessage');
        message.innerText = productName +  " 상품을 정말 삭제하시겠습니까?";
    });

    // 확인 버튼 클릭 시 fetch DELETE
    document.getElementById("confirmDeleteBtn").addEventListener("click", () => {
        if (!deleteProductId) return;

        fetch(`/api/products/` + deleteProductId, {
            method: "DELETE",
            headers: {
                "Content-Type": "application/json"
            }
        })
        .then(response => {
            if(!response.ok) throw new Error("삭제 실패");
            return response.text();
        })
        .then(message => {
            alert(message);
            window.location.reload(); // 삭제 후 페이지 새로고침
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