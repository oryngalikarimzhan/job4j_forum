<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="<c:url value='/update'/>" method='POST'>
    <table>
        <tr>
            <td>id:</td>
            <td><input type='text' name='id' value="${post.id}" readonly></td>
            <td>Заголовок:</td>
            <td><input type='text' name='name'></td>
            <td>Текст:</td>
            <td><textarea rows="5" name="description"></textarea></td>
        </tr>
        <tr>
            <td colspan='2'><input name="submit" type="submit" value="Сохранить" /></td>
        </tr>
    </table>
</form>
</body>
</html>