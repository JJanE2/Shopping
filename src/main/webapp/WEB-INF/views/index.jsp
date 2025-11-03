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
        <main class="container border rounded shadow py-4">
            <h2 class="my-4 text-center">Shopping</h2>
            <p class="text-center text-muted">상품을 검색해보세요</p>
            <form action="/" method="get" class="mx-auto mb-3 px-5 d-flex justify-content-center">
                <input type="text" name="keyword" class="form-control me-2"
                       placeholder="검색어 입력" value="">
                <!-- 제출 버튼 -->
                <button type="submit" class="btn btn-outline-primary w-25">검색</button>
            </form>
        </main>
    </section>
</body>
</html>