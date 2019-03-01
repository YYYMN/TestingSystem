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
     <form method="post" action="">
         <label>
             <select>
                <c:forEach var="role" items="${roles}">
                    <option>${role.role}</option>
                </c:forEach>
             </select>
         </label><br>
         <input type="text" placeholder = "Фамилия" size="20"><br>
         <input type="text" placeholder = "Имя" size="20"><br>
         <input type="text" placeholder = "Пароль" size="20"><br>
         <input type="text" placeholder = "Логин" size="20"><br>
         <input type="text" placeholder = "Почта" size="20"><br>
     </form>
 </div>

</body>
</html>
