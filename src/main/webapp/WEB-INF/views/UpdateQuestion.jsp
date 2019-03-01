<%--
  Created by IntelliJ IDEA.
  User: Sitx
  Date: 01.03.2019
  Time: 12:03
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>UpdateQuestion</title>
</head>
    <body>
    <div class="picture">
        <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
    </div>
    <div class="UpdateQuestionPage" align="center">
        <form method="post" action="">
            <label>
                <select>
                    <c:forEach var="question" items="${questions}">
                        <option>${question.description}</option>
                    </c:forEach>
                </select>

            </label><br>
            <input type="text" placeholder = "1. Ответ"><br>
            <input type="text" placeholder = "2. Ответ"><br>
            <input type="text" placeholder = "3. Ответ"><br>
            <input type="text" placeholder = "4. Ответ"><br>

            <input type="submit" name="btn" value="Сохранить">
        </form>
        <form method="post" action="http://localhost:8080/CreateQuestion">
            <input type="submit" name="btn" value="Добавить вопрос"><br>
        </form>

    </div>
    </body>
</html>
