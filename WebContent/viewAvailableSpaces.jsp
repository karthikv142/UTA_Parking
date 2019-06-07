<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Available Spaces</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
 <td>
 <form name="requestReservationForm" action="/UTAParking/ViewAvailableSpacesController" method="post">
 <h3> Please select your search criteria:</h3>
 <table>
 <tr>
 </tr>
 <tr>
 <td> Parking type (*): </td>
 <td>
 <select name="parking_type">
  <option value="access" selected>Access</option>
  <option value="basic">Basic</option>
  <option value="midrange">Midrange</option>
  <option value="premium">Premium</option>
</select><td style="width: 196px; height: 49px; "> 
 </tr>
 <tr>
 </tr>
<tr>
<td><input name="search" value="viewAvailableSpaces" type="hidden">
<input name="searchUserAdminButton" type="submit" value="Search"></td>
</tr>
<tr>
</tr>
</table>
</form>
</table>
</body>
</html>