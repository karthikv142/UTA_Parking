<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>View Parking Manager Details</title>
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
                <th class="myTableHead" style="width: 180px; ">Select</th>
                <th class="myTableHead" style="width: 180px; ">Username</th>
                <th class="myTableHead" style="width: 180px; ">Role</th>
                <th class="myTableHead" style="width: 180px; ">UTA ID</th>
                <th class="myTableHead" style="width: 180px;  ">Phone</th>
                <th class="myTableHead" style="width: 180px;  ">Email</th>
                <th class="myTableHead" style="width: 180px;  ">License Number</th>
                <th class="myTableHead" style="width: 180px;  ">Status</th>
                <th class="myTableHead" style="width: 180px;  ">Reason</th>
            </tr>
            <c:forEach items="${ParkingUsers}" var="item">
            <tr class="myTableRow"> 
                <th class="myTableCell" style="width: 10px; "><input type="radio" name="radioButton"></th> 
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.userName}" /></th> 
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.role}" /></th>              
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.uta_id}" /></th>
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.phone}" /></th>             
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.email}" /></th>
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.licenseNo}" /></th>
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.status}" /></th>
                <th class="myTableCell" style="width: 180px; "><c:out value="${item.reason}" /></th>

           </tr>
           </c:forEach>
           
           <tr class="myTableCell" style="width: 180px; ">
           <td>
           <form action= "editManagerProfile.jsp">
           <input type = "submit" value = "Edit"/></form>
           </td>
           </tr>
</table>
</table>
</body>
</html>