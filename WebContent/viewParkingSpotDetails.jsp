<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Parking Spot Details</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
 <td>
 <form name="requestReservationForm" action="/UTAParking/viewSpotDetailsController" method="post">
 <h3> Please select your search criteria:</h3>
 <table>
 <tr>
 </tr>
 <tr>
 <td> Parking Area Name (*): </td>
	<td><input id="areaName" name="parkingAreaName" value='${ParkingSpot.areaName}'/></td>
	<td> <input id="areaNameError" name="areaNameError" value="<c:out value='${ParkingSpotErrorMsgs.areaNameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td> Spot Number (*): </td>
	<td><input id="spotNumber" name="spot" value='${ParkingSpot.spot}'/></td>
	<td> <input id="spotError" name="spotError" value="<c:out value='${ParkingSpotErrorMsgs.spotError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td> Floor (*): </td>
	<td><input id="floorNumber" name="floor" value='${ParkingSpot.floor}'/></td>
	<td> <input id="floorError" name="floorError" value="<c:out value='${ParkingSpotErrorMsgs.floorError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 </tr>
<tr>
<td>
<input name="viewParkingSpotDetails" type="submit" value="Search"></td>
</tr>
<tr>
</tr>
</table>
</form>
</table>
</body>
</html>