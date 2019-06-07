<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Parking Space Details</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
<td>
<table style="table-layout: fixed; width: 100%" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 180px; ">parking area name</th>
				<th class="myTableHead" style="width: 180px; ">Parking Type</th>
				<th class="myTableHead" style="width: 180px; ">Floor</th>
				<th class="myTableHead" style="width: 180px; ">capacity</th>
				<th class="myTableHead" style="width: 180px;  ">Availability</th>
				<th class="myTableHead" style="width: 180px;  ">cart</th>
				<th class="myTableHead" style="width: 180px;  ">camera</th>
				<th class="myTableHead" style="width: 180px;  ">history</th>
			</tr>
			<c:forEach items="${ParkingSpaces}" var="item">
			<tr class="myTableRow"> 
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.parking_area_name}" /></th> 
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.parking_type}" /></th>				
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.floor}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.capacity}" /></th>				
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.availability}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.cart}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.camera}" /></th>
				<th class="myTableCell" style="width: 180px; "><c:out value="${item.history}" /></th>
		   </tr>
		   </c:forEach>		   
</table>
</table>
</body>
</html>