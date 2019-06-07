<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
	<h1>Welcome</h1>
	<h3>
		<a href="/UTAParking/UpdateProfileController">
			Update profile
		</a>
	</h3>
	<h3>
		<a href="/UTAParking/requestReservationForm.jsp">
			Request reservation
		</a>
	</h3>
	<h3>
		<a href="/UTAParking/ViewMyReservationsController">
			View my reserved spots
		</a>
	</h3>
	<h3>
		<a href="/UTAParking/ViewMyReservationStatusController">
			View my reservation status and violations
		</a>
	</h3>



</body>
</html>