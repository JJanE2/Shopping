<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
    <style>
        section {
          scroll-margin-top: 100px;
        }
    </style>
</head>
    <title>Shopping</title>
<body data-bs-spy="scroll" data-bs-target="#floatingTab" data-bs-offset="0" tabindex="0" style="position:relative">
<%@ include file="/WEB-INF/views/header.jsp" %>
<section class="container align-items-center w-75 min-vh-100">
    <main class="py-5">
    <section id="product-main" class="container border rounded shadow py-3 my-5">
        <h2 class="text-center mb-3">Shopping</h2>
        <p class="text-center text-muted border-bottom pb-3">상품상세 페이지</p>
        <div class="container row mb-2">
            <div id="imageDiv" class="col text-center">
                <img src="/resources/images/default-image.jpg" class="img-fluid" alt="product-default-image">
            </div>
            <div id="descDiv" class="col">
                <h3 class="text-start">상품명:${product.name}</h3>
                <p class="text-end fs-4 text-danger fw-bold">${product.price}원</p>
                <div class="d-flex align-items-center justify-content-between">
                  <span>수량</span>
                  <input type="number" class="form-control text-center w-25" id="quantityInput" value="1" min="1" max="50">
                </div>
                <hr>
                <form action="/orders/new" method="post">

                <div class="d-flex justify-content-between align-items-center mb-3">
                    <div class="text-start"><span class="">총 상품 금액</span></div>
                    <div class="d-flex align-items-center">
                        <span class="text-muted me-2">총 수량 <span id="totalCount">1</span>개</span>
                        <span class="fs-4 text-danger fw-bold"><span id="totalPrice">${product.price}</span>원</span>
                    </div>
                </div>
                <input type="hidden" name="totalPrice" id="totalPriceInput" value="${product.price}">
                <input type="hidden" name="products[0].productId" value="${product.id}">
                <input type="hidden" name="products[0].productName" value="${product.name}">
                <input type="hidden" name="products[0].price" value="${product.price}">
                <input type="hidden" name="products[0].quantity" id="quantity" value="1">
                <button id="order-confirm-btn" class="btn btn-primary w-100" type="submit">주문하기</button>

                </form>

                <form id="cart-form" class="mt-2">
                    <input type="hidden" name="productId" value="${product.id}">
                    <input type="hidden" name="quantity" id="cartQuantity" value="1">
                    <button class="btn btn-success w-100" type="submit">장바구니 담기</button>
                </form>
            </div>
        </div>
    </section>

<ul id="floatingTab" class="nav nav-pills nav-fill w-100 bg-body-tertiary sticky-top" style="top:56px;">
  <li class="nav-item">
    <a class="nav-link" href="#product-detail">상품 정보</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#reviews">리뷰</a>
  </li>
  <li class="nav-item">
    <a class="nav-link" href="#shipping">배송 안내</a>
  </li>
</ul>
</div>

    <section id="product-detail" class="container border rounded shadow py-3 my-5">
        <h4 class="fw-bold text-start">상품 정보</h4><hr>
        <p class="fw-bold text-start">${product.description}</p>
    </section>

    <section id="reviews" class="container border rounded shadow py-3 my-5">
        <h4 class="fw-bold text-start">리뷰</h4><hr>
        <c:choose>
            <%-- 리뷰가 있을 때 --%>
            <c:when test="${not empty reviews}">
                <c:forEach var="review" items="${reviews}">
                    <div class="review-item mb-3">
                        <p> <span class="fw-bold">${review.memberNickname}</span> <span class="badge text-bg-success">${review.rating}점</span></p>
                        <p>${review.content}</p>
                    </div><hr>
                </c:forEach>
            </c:when>
            <%-- 리뷰가 없을 때 --%>
            <c:otherwise>
                <p class="text-muted text-center">
                    아직 작성된 리뷰가 없습니다.
                </p>
            </c:otherwise>
        </c:choose>
    </section>

    <section id="shipping" class="container border rounded shadow py-3 my-5">
        <h4 class="fw-bold text-start">배송 안내</h4><hr>
        <ul>
            <li class="text-muted">전 상품 무료배송입니다. (일부 상품 및 도서산간지역 제외)</li>
            <li class="text-muted">결제 확인 후 평균 1-3일 소요 됩니다. (예약 상품, 해외배송 상품은 배송기간이 다를 수 있습니다.)</li>
        </ul>
    </section>
    </main>
</section>
<script>

    // 로그인 여부 체크
    const isLoggedIn = ${sessionScope.member != null ? 'true' : 'false'};

    const productPrice = ${product.price};                          // 상품 단가
    const quantityInput = document.getElementById("quantityInput"); // 개수 입력창
    const maxQuantity = parseInt(quantityInput.max);                // max 속성 가져오기
    const totalCount = document.getElementById("totalCount");       // 총 주문 개수 span
    const totalPrice = document.getElementById("totalPrice");       // 총 주문 금액
    const totalPriceInput = document.getElementById('totalPriceInput');
    const quantityHidden = document.getElementById('quantity');
    const cartQuantityHidden = document.getElementById('cartQuantity');


    quantityInput.addEventListener("input", () => {
      let quantity = parseInt(quantityInput.value);
      if (isNaN(quantity) || quantity < 1) quantity = 1;
      if (quantity > maxQuantity) quantity = maxQuantity;
      quantityInput.value = quantity;
      totalCount.textContent = quantity;

      const price = productPrice * quantity;
      totalPrice.textContent = price.toLocaleString("ko-KR");

      // form hidden input에 숫자로 값 넣기
      totalPriceInput.value = price;
      quantityHidden.value = quantity;
      cartQuantityHidden.value = quantity;

    });

    document.getElementById("cart-form").addEventListener("submit", function(e) {
        e.preventDefault(); // 기본 submit 막기

        // 비로그인 사용자 처리
        if (!isLoggedIn) {
            const goLoginPage =  confirm("로그인페이지로 이동하시겠습니까?");
            if (goLoginPage) {
                window.location.href = "/login";
                return;
            } else {
                return;
            }
        }

        const data = {
            productId: document.querySelector("input[name='productId']").value,
            quantity: document.querySelector("input[name='quantity']").value
        };

        fetch("/api/cart/items", {
            method: "POST",
            headers: {
                "Content-Type": "application/json"
            },
            body: JSON.stringify(data)
        })
        .then(res => res.json())
        .then(result => {
            // 메시지 출력 (선택)
            alert(result.message);
            // 이동 여부 묻기
            const goCart = confirm("장바구니로 이동하시겠습니까?");
            if (goCart) {
                // 예(확인) → 장바구니 페이지로 이동
                window.location.href = "/cart";
            }
            // 아니오(취소) → 아무것도 안 함
        })
        .catch(err => console.error(err));
    });

</script>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>