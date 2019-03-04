<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>CreateUser</title>
    <link rel="stylesheet" href="<c:url value="/css/style.css"/>">
</head>
<body>
 <div class="picture">
    <p><img src="<c:url value="/images/picture.png"/>" width="230" alt="Тут должен быть рисунок"></p>
 </div>
 <div class="CreateUserPage" align="center">
     <form method="post" action="/SaveUser">
         <label>
             <select name="role">
                <c:forEach var="role" items="${rolesList}">
                    <option>${role}</option>
                </c:forEach>
             </select>
         </label><br>
         <input type="text" placeholder = "Фамилия" name="lastName" size="20"><br>
         <input type="text" placeholder = "Имя" name="firstName" size="20"><br>
         <input type="password" placeholder = "Пароль" name="password" size="20"><br>
         <input type="text" placeholder = "Логин" name="login" size="20"><br>
         <input type="text" placeholder = "Почта" name="email" size="20"><br>
         <input type="submit" value="Сохранить пользователя" size="20"><br>
     </form>
 </div>
<div>${success}<div>
</body>
</html>
