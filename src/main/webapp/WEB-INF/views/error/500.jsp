<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
    <title>500 Internal Server Error</title>
<body>
<section class="container d-flex align-items-center w-75 min-vh-100">
    <main class="container text-center">
        <div>
            <h2>서버 오류가 발생했습니다</h2>
            <p class="text-muted mb-3">
                요청을 처리하는 중 문제가 발생했습니다.<br>
                잠시 후 다시 시도해주세요.
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