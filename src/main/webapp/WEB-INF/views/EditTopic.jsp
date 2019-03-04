<%--
  Created by IntelliJ IDEA.
  User: Sitx
  Date: 02.03.2019
  Time: 12:19
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>EditTopic</title>
</head>
    <body>
        <div class="picture">
            <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
        </div>
        <div class="EditTopicPage" align="center">
            <form method="post" action="">
                <label>
                    <select>
                        <c:forEach var="topic" items="${topics}">
                            <option>${topic.description}</option>
                        </c:forEach>
                          </select>
                </label><br>

                <label>
                    <select>
                        <c:forEach var="test" items="${test}">
                            <option>${test.description}</option>
                        </c:forEach>
                    </select>
                </label><br>
            </form>
        </div>
    </body>
</html>
