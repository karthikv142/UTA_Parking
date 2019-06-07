<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>UTA Parking System</title>
</head>
<body>
<h1 align="center" style="margin-bottom: 35px;">EZPark</h1><input name="usernameError" value="<c:out value='${loginErrorMsg.loginError}'/>" type="text" style="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
<form name="logincontroller" action="/UTAParking/LoginController" method="post" style="width:300px;">
<table>

<tr>
    <td><h3 style="margin-top: 1px;margin-bottom: 1px;">Username:</h3>
    <input id ="LoginUserName" name="username" value="<c:out value='${login.username}'/>" type="text" maxlength="16"> 
 	
</tr>
<tr>
    <td><h3 style="margin-top: 1px;margin-bottom: 1px;">Password:</h3>
    <input id ="LoginPassword" name="password" value="<c:out value='${login.password}'/>" type="password" maxlength="45"> 
 	
</tr>

</table>
<input name="action" value="login" type="hidden">
	<input id="loginButton" name="loginButton" type="submit" value="Login">
</form>
 <h4><a href="/UTAParking/registerForm.jsp">No account? Register here</a></h4>
</body>
</html>