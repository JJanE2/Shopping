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
          <li class="nav-item">
           <a class="btn btn-outline-primary" href="#">회원가입</a>
          </li>
          <li class="nav-item">
           <a class="btn btn-outline-primary" href="#">로그인</a>
          </li>
        </ul>
    </div>
  </div>
</nav>