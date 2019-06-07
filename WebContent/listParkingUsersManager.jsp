<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Parking User Details</title>
</head>
<body style="width: 450px; ">

<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id ="logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
<td>
<table>
<tr>
</tr>
<tr>
	<td>View Parking User Details:</td>
</tr>
</table>

<table style="table-layout: fixed; width: 100%" class="myTable"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 170px; ">Username</th>
				<th class="myTableHead" style="width: 170px; ">Role</th>
				<th class="myTableHead" style="width: 170px; ">UTA ID</th>
				<th class="myTableHead" style="width: 170px;  ">Phone</th>
				<th class="myTableHead" style="width: 170px;  ">Email</th>
				<th class="myTableHead" style="width: 170px;  ">License plate Number</th>
				<th class="myTableHead" style="width: 170px;  ">Parking Type</th>
				<th class="myTableHead" style="width: 170px;  ">No Shows</th>
				<th class="myTableHead" style="width: 170px;  ">Overstays</th>
			</tr>
			<c:forEach items="${ParkingUsers}" var="item">
			<tr class="myTableRow"> 
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.user_name}" /></th> 
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.role}" /></th>				
				<th class="myTableCell" style="width: 170px;  "><c:out value="${item.uta_id}" /></th>
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.phone}" /></th>				
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.email}" /></th>
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.license_no}" /></th>
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.type}" /></th>
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.noshow}" /></th>
				<th class="myTableCell" style="width: 170px; "><c:out value="${item.overstay}" /></th>
				
		   </tr>
		   </c:forEach>
		             <tr class="myTableCell" style="width: 180px; ">
		             <td>
		             <form action= "editUserAdmin.jsp"><input type = "submit" value = "Edit"/></form>
		             </td>
		             </tr>
		   
</table>
</table>
</body>
</html>
