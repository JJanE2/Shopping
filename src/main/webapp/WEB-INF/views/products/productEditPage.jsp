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
        <form id="product-update-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">상품 정보수정 페이지</p>
            <div class="mb-3">
                <label for="name" class="form-label">상품명</label>
                <input type="text" class="form-control" id="name" name="name" value="${product.name}" required>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">가격</label>
                <input type="number" class="form-control" id="price" name="price" min="0" step="100" value="${product.price}" required>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">상품설명</label>
                <textarea class="form-control" id="description" name="description" rows="3" required>${product.description}</textarea>
            </div>

            <div class="mb-3">
                <label for="stockQuantity" class="form-label">수량</label>
                <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" min="1" step="1" value="${product.stockQuantity}" required>
            </div>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">수정하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
        </form>
    </main>
</section>
<script>
    document.getElementById("product-update-form").addEventListener("submit", function(event) {

        const productId = ${product.id};
        const memberId = ${product.memberId};

        event.preventDefault();
        const productUpdateDto = {
            id: productId,
            memberId: memberId,
            name: document.getElementById("name").value,
            price: Number(document.getElementById("price").value),
            description: document.getElementById("description").value,
            stockQuantity: Number(document.getElementById("stockQuantity").value)
        };

        fetch(`/api/products/${product.id}`, {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(productUpdateDto)
        })
        .then(response => {
            if (!response.ok) {
              return response.text().then(text => { throw new Error(text); });
            }
            return response.text();
          })
        .then(message => {
          alert(message);
          window.location.href = `/products/${product.id}`; // 성공시 상품사에 화면으로 이동
        })
        .catch(error => {
          alert(error.message);
          window.location.reload();  // 실패시 새로고침
        });
    });
</script>
</body>
</html>