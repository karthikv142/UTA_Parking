<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Register</title>
</head>
<body>

<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table>
 <tr>
 <td>
 <h3> Please fill in the registration form below :</h3>
 <form name="registerForm" action="/UTAParking/RegisterController" method="post">
 <table style="table-layout: fixed; width: 100%">
 <tr>
 <td style="width: 140px; height: 48px; "> Username (*): </td>
 <td style="width: 196px; height: 49px; ">
 <input name="username" value="<c:out value='${user.username}'/>" type="text" maxlength="16"> 
 	<td> <input name="usernameError" value="<c:out value='${errorMsgs.usernameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 140px; height: 48px; "> UTA ID (*): </td>
 <td style="width: 196px; height: 49px; ">
 <input name="utaID" value="<c:out value='${user.utaID}'/>" type="text" maxlength="45"> 
 	<td> <input name="utaIDError" value="<c:out value='${errorMsgs.utaIDError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 140px; height: 48px; "> Role (*): </td>
 <td style="width: 196px; height: 49px; ">
 
 <select name="role">
  <option value="admin" selected>Admin</option>
  <option value="user" > Parking user</option>
  <option value="manager">Parking manager</option>
</select>

 	<td> <input name="roleError" value="<c:out value='${errorMsgs.roleError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 140px; height: 48px; "> Password (*): </td>
 <td style="width: 196px; height: 49px; ">
 <input name="password" value="<c:out value='${user.password}'/>" type="text" maxlength="16"> 
 	<td> <input name="passwordError" value="<c:out value='${errorMsgs.passwordError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 140px; height: 48px; "> Phone (*): </td>
 <td style="width: 196px; height: 49px; ">
 <input name="phone" value="<c:out value='${user.phone}'/>" type="text" maxlength="45"> 
 	<td> <input name="phoneError" value="<c:out value='${errorMsgs.phoneError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 140px; height: 48px; "> Email (*): </td>
 <td style="width: 196px; height: 49px; ">
 <input name="email" value="<c:out value='${user.email}'/>" type="text" maxlength="45"> 
 	<td> <input name="emailError" value="<c:out value='${errorMsgs.emailError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 140px; height: 48px; "> License plate (*): </td>
 <td style="width: 196px; height: 49px; ">
 <input name="license_plate" value="<c:out value='${user.license_plate}'/>" type="text" maxlength="45"> 
 	<td> <input name="license_plateError" value="<c:out value='${errorMsgs.license_plateError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>

 
 <td colspan="2" style="width: 527px  height: 55px; ">(*) Mandatory field</td>
 </tr>
 </table>
 <input name="action" value="register" type="hidden">
	<input name="registerButton" type="submit" value="Register">
	<input name="resetButton" type="reset" value="Reset">
				</form>
</td>
</tr>
</table>

<h4><a href="/UTAParking/index.jsp">Go back to login page</a></h4>
</body>
</html>