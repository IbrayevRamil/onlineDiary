<%@ page contentType="text/html; charset=utf-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Online diary</title>
</head>

<body>
<form action="<c:url value="/auth"/>" method="POST">
    <table>
        <tr>
            <td>Login:</td><td><input type="text" name="login" value="${user.login}"/></td>
        </tr>
        <tr>
            <td>Password:</td><td><input type="text" name="password" value="${user.password}"/></td>
        </tr>
    </table>
    <table>
        <tr>
            <td><input type="submit" value="Login" name="Login"/></td>
            <td><input type="submit" value="Sign up" name="Sign up"/></td>
        </tr>
    </table>
</form>
</body>
</html>