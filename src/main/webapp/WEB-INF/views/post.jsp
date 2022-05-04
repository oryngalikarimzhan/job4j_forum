<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <body>
        <div class="container">
            <a href="<c:url value='/'/>">
                Назад
            </a>
        <div>
        <h2>
            <c:out value="${post.name}"/>
        </h2>
    </div>
    <div>
        <h3>
            <c:out value="${post.description}"/>
        </h3>
    </div>
    <div>
        <c:out value="Создан пользователем: ${post.user.username}"/>
        <c:out value="в ${post.created}"/>
    </div>
    <br>
    <br>
    <form  action="<c:url value='/post/${post.id}/comment/new'/>" method='POST'>
        <div style="display: block; width: 300px">
            <label for="comment" style="font-weight: bold">
                Добавить комментарии:
            </label>
            <textarea id="comment" name="text" rows="3" style="width: 100%"></textarea>
        </div>
        <input name="submit" type="submit" value="Добавить" />
    </form>
    <div><h2>Комментарии:</h2></div>
    <div class="row">
        <c:forEach items="${post.comments}" var="comment">
            <div style="font-weight: bold">
                <c:out value="Пользователь: ${comment.user.username}"/>
            </div>
            <div>
                <c:out value="${comment.text}"/>
            </div>
        </c:forEach>
    </div>
    </div>
    </body>
</html>
