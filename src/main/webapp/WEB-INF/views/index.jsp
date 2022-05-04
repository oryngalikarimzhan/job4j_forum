<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="en">
    <head>
        <!-- Required meta tags -->
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

        <!-- Bootstrap CSS -->
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css"
              integrity="sha384-Vkoo8x4CGsO3+Hhxv8T/Q5PaXtkKtu6ug5TOeNV6gBiFeWPGFN9MuhOf23Q9Ifjh"
              crossorigin="anonymous">

        <title>Форум job4j</title>
    </head>
    <body>
        <div class="container">
            <div class="row" style="display: flex; justify-content: space-between">
                <div>
                    <h4>Форум job4j</h4>
                </div>

                <div style="width: 300px; display: flex; justify-content: space-around">
                    <c:if test="${not empty user}">
                        <c:out value="${user.username}"/>
                        <a href="<c:url value='/logout'/> ">Выйти</a>
                    </c:if>
                    <c:if test="${empty user}">
                        <a href="<c:url value='/login'/>">Авторизация</a>
                    </c:if>

                </div>
            </div>
            <div class="row">
                <div style="margin-top: 10px">
                    <button onclick="window.location.href='<c:url value='/new'/>'" style="width: 100px">
                        Create
                    </button>
                </div>
                <table class="table" style="margin-top: 10px">
                    <thead>
                    <tr>
                        <th scope="col">Тема</th>
                        <th scope="col">Создано</th>
                        <th scope="col">Пользователь</th>
                        <th scope="col">Действия</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${posts}" var="post">
                        <tr>
                            <td>
                                <a href="<c:url value="/post/${post.id}"/>">
                                    <c:out value="${post.name}"/>
                                </a>
                            </td>
                            <td><c:out value="${post.created}"/></td>
                            <td><c:out value="${post.user.username}"/></td>
                            <td>
                                <c:if test="${post.user.username == user.username}">
                                    <button onclick="window.location.href=
                                            '<c:url value='/edit/${post.id}'/>'"
                                            style="width: 50px">
                                        Edit
                                    </button>
                                    <button onclick="location.href='<c:url value='/delete/${post.id}'/>'"
                                            style="width: 70px">
                                        Delete
                                    </button>
                                </c:if>
                                <c:if test="${post.user.username != user.username}">
                                    <button style="width: 50px" disabled>
                                        Edit
                                    </button>
                                    <button style="width: 70px" disabled>
                                        Delete
                                    </button>
                                </c:if>

                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>

        <!-- Optional JavaScript -->
        <!-- jQuery first, then Popper.js, then Bootstrap JS -->
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js"
                integrity="sha384-J6qa4849blE2+poT4WnyKhv5vZF5SrPo0iEjwBvKU7imGFAV0wwj1yYfoRSJoZ+n"
                crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"
                integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo"
                crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"
                integrity="sha384-wfSDF2E50Y2D1uUdj0O3uMBJnjuUD4Ih7YwaYd1iqfktj0Uod8GCExl3Og8ifwB6"
                crossorigin="anonymous"></script>
    </body>
</html>