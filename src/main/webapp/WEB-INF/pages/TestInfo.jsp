<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Владислав
  Date: 17.02.2019
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Test Info</title>
</head>
<body>
    <c:forEach items="${list}" var="element">
        ${element} <br/>
    </c:forEach>
</body>
</html>
