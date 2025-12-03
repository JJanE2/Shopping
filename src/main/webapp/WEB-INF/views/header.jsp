<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!-- Header Fragment -->
<header>
    <style>
      .nav-text-color {
        color: #ff6600;
      }

      .nav-text-color:hover {
        color: #d35400;
      }
    </style>
</header>

<nav class="navbar navbar-expand-lg bg-light fixed-top">
  <div class="container-fluid">

    <a class="navbar-brand" href="/">Shopping</a>

    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
          <li class="nav-item">
            <a class="nav-link" href="#">인기상품</a>
          </li>
        </ul>
        <ul class="navbar-nav ms-auto gap-3">
        <c:choose>
            <c:when test="${not empty sessionScope.member}">
                <!-- 회원 역할이 OWNER일 때만 상품추가 버튼 표시 -->
                <c:if test="${sessionScope.member.role eq 'OWNER'}">
                    <li class="nav-item">
                        <a class="btn btn-outline-success" href="/products/new">상품추가</a>
                    </li>
                    <li class="nav-item">
                        <a class="btn btn-outline-warning" href="/owner/orders">고객 주문관리</a>
                    </li>
                </c:if>

                <li class="nav-item">
                    <a class="btn btn-outline-primary" href="/members/${sessionScope.memberId}">마이페이지</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-primary" href="/logout">로그아웃</a>
                </li>
            </c:when>
            <c:otherwise>
                <li class="nav-item">
                    <a class="btn btn-outline-success" href="/members/new">회원가입</a>
                </li>
                <li class="nav-item">
                    <a class="btn btn-outline-primary" href="/login">로그인</a>
                </li>
            </c:otherwise>
        </c:choose>
        </ul>
    </div>
  </div>
</nav>