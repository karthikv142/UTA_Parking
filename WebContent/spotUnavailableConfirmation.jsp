<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EZPark</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
	<td><h3>The Parking Spot has been made unavailable!</h3></td>
</tr>
<tr>
<td>
<form name="modifyParkingAreaManagerConfirmation" method="post" style="width: 300px; ">
<table>
<tr>
<td><a href="managerHome.jsp?action=submit"  target="_top"><span>Go back to Home!</span></a></td>
</tr>
</table>
</form>
</table>
</body>
</html>