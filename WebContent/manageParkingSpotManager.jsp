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
<td>
<form name="manageParkingSpotManager" action="ManageParkingSpotManagerController" method="post" style="width: 300px; ">
<table>
<tr>
<td><h1>Modify Parking Spot</h1></td>
</tr>
<tr>
	<td>Select Parking Area</td>
	<td>
	<select name = "parking_area_name">
	<option value = "west garage">West Garage</option>
	<option value = "maverick">Maverick</option>
	<option value = "davis">Davis</option>
	<option value = "neddermann">Neddermann</option>	
	</select>
	</td>
</tr>
<tr>
	<td>Select Parking Type</td>
	<td>
	<select name = "type">
	<option value = "basic">Basic</option>
	<option value = "midrange">Midrange</option>
	<option value = "premium">Premium</option>
	<option value = "access">Access</option>	
	<option value = "basic">Basic</option>
	</select>
	</td>
</tr>
<tr>
	<td>Floor</td>
	<td>
	<select name = "floor">
	<option value="7">7</option>
	<option value="6">6</option>
	<option value="5">5</option>
	<option value="4">4</option>
	<option value="3">3</option>
	<option value="2">2</option>	
	<option value="1">1</option>
	</select>
	</td>
</tr>
<tr>
<td><input type="submit" value="Submit" name = "action"></td>
</tr>
</table>
</form>
</table>
</body>
</html>