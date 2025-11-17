<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
    <title>Shopping</title>
<body class="pt-5">
    <%@ include file="/WEB-INF/views/header.jsp" %>
    <main class="container w-75 min-vh-100 pt-5">
        <div class="row row-cols-2 row-cols-md-4 g-4">
          <c:forEach var="product" items="${products}">
              <div class="col">
                <a href="/products/${product.id}" class="text-decoration-none text-dark">
                  <div class="card text-center">
                      <img src="/resources/images/default-image.jpg"class="card-img-top"alt="${product.name}">
                      <div class="card-body">
                          <h5 class="card-title">${product.name}</h5>
                          <span class="card-text fs-6">
                              <fmt:formatNumber value="${product.price}" type="number"/>원
                          </span>
                      </div>
                  </div>
                </a>
              </div>
          </c:forEach>
        </div>
    </main>
</body>
</html>