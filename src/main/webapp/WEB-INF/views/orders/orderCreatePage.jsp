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
        <form id="order-create-form">
            <h2 class="text-center mb-3">Shopping</h2>
            <p class="text-center text-muted">주문확인 페이지</p>
            <hr>

            <p class="fs-4 text-primary fw-bold">
                <span id="totalPriceText">${orderRequestDto.totalPrice}</span>
            </p>

            <div class="d-flex justify-content-center gap-3 mb-3">
                <button type="submit" class="btn btn-primary">주문하기</button>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
            <input type="hidden" name="totalPrice" id="totalPrice" value="${orderRequestDto.totalPrice}">
            <input type="hidden" name="memberId" id="memberId" value="${memberId}">
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


    document.getElementById("order-create-form").addEventListener("submit", async function(event) {
        const memberId = Number(document.getElementById("memberId").value);

        event.preventDefault();
        const orderCreateDto = {
            totalPrice: Number(document.getElementById("totalPrice").value),
            memberId: Number(document.getElementById("memberId").value)
        };

        const response = await fetch("/api/orders", {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify(orderCreateDto)
        });

        // 서버가 보낸 메시지 읽기
        const message = await response.text();

        if (response.ok) {
            alert(message);
            window.location.href = `/members/${memberId}`;
        } else {
            alert(message);
            window.location.reload();
        }
    });
</script>

</body>
</html>