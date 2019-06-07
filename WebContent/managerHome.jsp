<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
    <h1>Welcome ${username}</h1> 

<h3> <a href="/UTAParking/EditManagerProfileContoller">Update Profile</a></h3>
<h3> <a href="/UTAParking/viewAvailableSpaces.jsp">View numbers of available spaces</a></h3>
<h3> <a href="/UTAParking/viewParkingSpotDetails.jsp">View parking spot details</a></h3>
<h3> <a href="/UTAParking/manageParkingSpotManager.jsp">Manage parking spot availability</a></h3>
<h3> <a href="/UTAParking/addParkingArea.jsp">Manage parking area</a></h3>
<h3> <a href="/UTAParking/modifyParkingAreaManager.jsp">Modify a parking area</a></h3>
<h3> <a href="/UTAParking/searchUserManager.jsp">View parking user details</a></h3>
<h3> <a href="/UTAParking/setViolations.jsp">Set Violations</a></h3>
<!--<h3> <a href="/UTAParking/viewAllReservations.jsp">View all reservations</a></h3>-->
<!--<h3> <a href="DeletePastReservationsController?action=submit">Delete Past Reservations</a></h3>-->
<h3> <a href="DisplayReservationsManagerController?action=submit">Delete Reservations</a></h3>
<h3> <a href="ModifyReservationsManagerController?action=submit">Modify Reservations</a></h3>


</body>
</html>
