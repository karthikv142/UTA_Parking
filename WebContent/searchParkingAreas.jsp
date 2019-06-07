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

<form name="searchUseAdminForm" action="/UTAParking/ViewAvailableSpacesController" method="post" style="width: 300px; ">
<h3>Parking area Search</h3>
<table>
<tr>
	<td>Parking Area Name<input name="parkingAreaName" value='${parkingArea.areaName}'/></td>
	<td> <input name="searchUserError" value="<c:out value='${parkingAreaErrors.areaNameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
	<td>Capacity <input name="capacity" value='${parkingArea.capacity}'/></td>
	<td> <input name="searchUserError" value="<c:out value='${parkingAreaErrors.capacityError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
	<td>Floor<input name="floors" value='${parkingArea.floor}'/></td>
	<td> <input name="searchUserError" value="<c:out value='${parkingAreaErrors.floorError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
</tr>
<tr>
 <td style="width: 160px; height: 47px; "> Options Supported: </td>
 <td style="width: 221px; ">
 <input type="checkbox" name="checkedOptions" value="camera">Camera
<input type="checkbox" name="checkedOptions" value="cart" >Cart
<input type="checkbox" name="checkedOptions" value="history">History
 </tr>
<tr>
<td><input name="action" value="searchUserAdmin" type="hidden">
<input name="searchUserAdminButton" type="submit" value="Search"></td>
</tr>
<tr>
</tr>
</table>
</form>
</table>
</body>
</html>