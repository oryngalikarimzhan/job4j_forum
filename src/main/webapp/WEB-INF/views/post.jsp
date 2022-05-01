<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<div class="container">
    <a href="<c:url value='/'/>">Назад</a>
    <div>
        <h2><c:out value="${post.name}"/></h2>
    </div>
    <div>
        <h3><c:out value="${post.description}"/></h3>
    </div>
    <div>
        <c:out value="Создан пользователем: ${post.user.username}"/>
        <c:out value="в ${post.created}"/>
    </div>
    <br><br>
    <form  action="<c:url value='/post'/>" method='POST'>
        <table>
            <tr>
                <td><input type='hidden' name='post-id' value="${post.id}"></td>
                <td>
                    <div><h3>Добавить комментарии:</h3></div>
                    <textarea rows="3" name="text" style="width: 300px"></textarea></td>
            </tr>
            <tr>
                <td colspan='2'><input name="submit" type="submit" value="Добавить" /></td>
            </tr>
        </table>
    </form>
    <div><h2>Комментарии:</h2></div>
    <div class="row">
        <table class="table">
            <c:forEach items="${post.comments}" var="comment">
            <thead>
            <tr>
                <th scope="col"><c:out value="Пользователь: ${comment.user.username}"/></th>
            </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${comment.text}"/></td>
                </tr>
            </tbody>
            </c:forEach>
        </table>
    </div>
</div>
</body>
</html>
