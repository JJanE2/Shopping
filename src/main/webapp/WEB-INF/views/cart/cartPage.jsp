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
                            <th>단가</th>
                            <th>수량</th>
                            <th>가격</th>
                            <th>삭제</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="item" items="${items}">
                        <tr data-item-id="${item.id}">
                            <td>${item.productName}</td>
                            <td class="unitPrice">${item.price}</td>
                            <td>
                                <input type="number" class="quantityInput form-control form-control-sm w-50 mx-auto text-center" value="${item.quantity}" min="1" max="50">
                            </td>
                            <td class="itemTotalPrice">
                                ${item.price * item.quantity}
                            </td>
                            <td>
                                <button class="deleteBtn btn btn-sm btn-outline-danger">삭제</button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>

        <hr>

        <c:if test="${items.size() != 0}">
            <p class="text-danger fw-bold">
                총 금액: <span id="totalPrice">${totalPrice}</span>원
            </p>
        </c:if>

        <div class="d-flex justify-content-center align-items-center gap-3 mb-1">
            <c:if test="${items.size() != 0}">
                <div>
                    <button id="clearCartBtn" class="btn btn-outline-danger">장바구니 전체 비우기</button>
                </div>
                <form action="/orders/cart" method="post" class="m-0">
                    <button type="submit" class="btn btn-primary">주문하기</button>
                </form>
            </c:if>
            <div>
                <button class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
        </div>
    </main>
</section>

<script>
// 전체 합계 다시 계산
function updateTotalPrice() {
    let sum = 0;
    $(".itemTotalPrice").each(function () {
        sum += parseInt($(this).text());
    });
    $("#totalPrice").text(sum.toLocaleString());
}

// 화면 로딩 완료시 총금액 계산
document.addEventListener("DOMContentLoaded", function () {
    updateTotalPrice();
});

// 수량 변경 이벤트 (업데이트)
$(document).on("change", ".quantityInput", function () {
    const row = $(this).closest("tr");
    const cartItemId = row.data("item-id");
    let quantity = parseInt($(this).val());

    // 수량 범위 체크
    if (isNaN(quantity) || quantity < 1) quantity = 1;
    if (quantity > 50) quantity = 50;
    $(this).val(quantity);

    const unitPrice = parseInt(row.find(".unitPrice").text());
    $.ajax({
        url: "/api/cart/items/update",
        method: "POST",
        contentType: "application/json",
        data: JSON.stringify({
            id: cartItemId,
            quantity: quantity
        }),
        success: function (res) {
            // 화면 단품 금액 갱신
            const newPrice = unitPrice * Number(res.quantity);
            row.find(".itemTotalPrice").text(newPrice);
            // 전체 합계 갱신
            updateTotalPrice();
        }
    });
});

// 단일 삭제
$(document).on("click", ".deleteBtn", function () {
    const row = $(this).closest("tr");
    const cartItemId = row.data("item-id");
    $.ajax({
        url: "/api/cart/items",
        method: "DELETE",
        contentType: "application/json",
        data: JSON.stringify({ id: cartItemId }),
        success: function (result) {
            alert(result.message);
            row.remove();

            // 삭제후 장바구니 비어있으면 새로고침
            if ($("#cartTable tbody tr").length === 0) {
                window.location.href = "/cart";
            }

            updateTotalPrice();
        }
    });
});

// 장바구니 전체 비우기
$("#clearCartBtn").click(function () {
    const cartId = $("#cartId").val();
    $.ajax({
        url: `/api/cart/${cartId}`,
        method: "DELETE",
        success: function (result) {
            alert(result.message);
            window.location.reload();
        }
    });
});
</script>
</body>
</html>