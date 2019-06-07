<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<form name="newReservationManagerController" action="NewReservationManagerController" method="post" style="width: 300px; ">
<table>
<tr>
</tr>
<tr>
	<td>View Reservations</td>
</tr>
</table>
</form>

<table class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 180px; ">Reserve ID</th>
				<th class="myTableHead" style="width: 180px; ">Parking Area Name</th>
				<th class="myTableHead" style="width: 180px; ">Type</th>
				<th class="myTableHead" style="width: 180px;  ">Floor</th>
				<th class="myTableHead" style="width: 180px;  ">Spot Num</th>
				<th class="myTableHead" style="width: 180px;  ">User ID</th>				
				<th class="myTableHead" style="width: 180px;  ">Duration</th>				
				<th class="myTableHead" style="width: 180px;  ">Cost</th>
				<th class="myTableHead" style="width: 180px;  ">Status</th>
				<th class="myTableHead" style="width: 180px;  ">Cart</th>			
				<th class="myTableHead" style="width: 180px;  ">Camera</th>
				<th class="myTableHead" style="width: 180px;  ">History</th>
				<th class="myTableHead" style="width: 180px;  ">Availability</th>
				
			</tr>
			<c:forEach items="${Reservations}" var="item">
			<tr class="myTableRow"> 
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.reserve_id}" /></th> 
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.parking_area_name}" /></th>				
				<th class="myTableCell" style="width: 180px;  "><c:out value="${item.type}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.floor}" /></th>				
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.spot_num}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.user_id}" /></th>		
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.duration}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.cost}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.status}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.cart}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.camera}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.history}" /></th>				
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.availability}" /></th>
				<th class="myTableCell" style="width: 180px; ">
					<form action="NewReservationManagerController">
					<input type="Submit" name = '{item.reserve_id}' value = 'Select'>
					<input type = "hidden" name = "reserve_id" value = "<c:out value='${item.reserve_id}'/>">				
					</form>				
				</th>
			</tr>			
		   </c:forEach>		   
		   
</table>
</table>
</body>
</html>