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
        <form id="product-create-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">상품등록 페이지</p>
            <div class="mb-3">
                <label for="name" class="form-label">상품명</label>
                <input type="text" class="form-control" id="name" name="name" placeholder="상품명을 입력해주세요" required>
            </div>

            <div class="mb-3">
                <label for="price" class="form-label">가격</label>
                <input type="number" class="form-control" id="price" name="price" min="0" step="100" placeholder="가격을 입력해주세요" required>
            </div>

            <div class="mb-3">
                <label for="description" class="form-label">상품설명</label>
                <textarea class="form-control" id="description" name="description" rows="3" required></textarea>
            </div>

            <div class="mb-3">
                <label for="stockQuantity" class="form-label">수량</label>
                <input type="number" class="form-control" id="stockQuantity" name="stockQuantity" min="0" step="1" placeholder="수량을 입력해주세요">
            </div>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">등록하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>

        </form>
    </main>
</section>

<script>
    document.getElementById("product-create-form").addEventListener("submit", function(event) {

        const memberId = ${memberId};

        event.preventDefault();
        const productCreateDto = {
            memberId: memberId,
            name: document.getElementById("name").value,
            price: Number(document.getElementById("price").value),
            description: document.getElementById("description").value,
            stockQuantity: Number(document.getElementById("stockQuantity").value)
        };

        fetch("/api/products", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(productCreateDto)
        })
        .then(response =>
            response.json().then(data => ({ ok: response.ok, data: data }))
        )
        .then(result => {
            if (result.ok) {
                // JSON에서 메시지와 productId 가져오기
                alert(result.data.message);
                window.location.href = "/products/" + result.data.productId;
            } else {
                alert(result.data.message);
                window.location.reload();
            }
        })
        .catch(error => {
            console.error(error);
            alert("서버 요청 중 오류 발생");
        });
    });
</script>
</body>
</html>