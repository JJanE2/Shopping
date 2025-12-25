<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
    <title>403 Forbidden</title>
<body>
<section class="container d-flex align-items-center w-75 min-vh-100">
    <main class="container text-center">
        <div>
            <h2>권한이 없습니다</h2>
            <p class="text-muted mb-3">
                <c:choose>
                    <c:when test="${not empty errorMessage}">
                        ${errorMessage}
                    </c:when>
                    <c:otherwise>
                        계정을 확인해 주세요.
                    </c:otherwise>
                </c:choose>
            </p>
            <div>
                <a href="/" class="btn btn-primary">메인화면</a>
                <button type="button" class="btn btn-secondary" onclick="history.back()">돌아가기</button>
            </div>
        </div>
    </main>
</section>
</body>
</html>