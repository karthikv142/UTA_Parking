<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Search Parking User</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
<td>
<form name="searchUseAdminForm" action="/UTAParking/SearchUserManagerController" method="post" style="width: 300px; ">
<h3>Please enter a username:</h3>
<table>

<tr>
    <td><input name="username" value='${SearchUserAdmin.userName}'/></td>
    <td> <input id = "searchUserError" name="searchUserError" value="<c:out value='${searchErrorMsg.search_usernameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
<td><input name="action" value="searchUserManager" type="hidden">
<input name="searchUserManagerButton" type="submit" value="Search"></td>
</tr>
<tr>

</tr>
</table>
</form>
</table>
</body>
</html>
