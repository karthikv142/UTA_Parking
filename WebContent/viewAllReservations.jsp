<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>View All Reservations</title>
</head>
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<form name="viewAllReservationForm" action="/UTAParking/viewAllReservationController" method="post">
     <div class="mainbar"><div class="submb"></div></div>
     
      
      <h3> View All Reservations: </h3>
   <table border="1" class="myTable" style="table-layout: fixed; width: 100%"> 
    <tr class="myTableRow">
        <th class="myTableHead" style="width: 129px; height: 73px">Parking area name</th>
        <th class="myTableHead" style="width: 114px; height: 63px">Floor no</th>
        <th class="myTableHead" style="width: 114px; height: 63px">Spot Number</th>
        <th class="myTableHead" style="width: 114px; height: 63px">Start Time</th>
        <th class="myTableHead" style="width: 114px; height: 63px">Duration</th>
        <th class="myTableHead" style="width: 114px; height: 63px">Camera Option</th>
        <th class="myTableHead" style="width: 114px; height: 63px">Cart Option</th>
        <th class="myTableHead" style="width: 114px; height: 63px">History Option</th>

    </tr>
    <c:forEach items="${reserve}" var="item"  varStatus="status">
            <tr class="myTableRow"> 
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.parking_area_name}" /></th> 
                <th class="myTableCell" style="width: 11px; "><c:out value="${item.floor}" /></th>
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.spot_number}" /></th> 
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.start_time}" /></th> 
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.duration}" /></th> 
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.camera_option}" /></th> 
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.cart_option}" /></th> 
                <th class="myTableCell" style="width: 90px; "><c:out value="${item.history_option}" /></th> 
           
           
           </tr>
     </c:forEach>
    </table>
<input name="action" type="submit" value="getReservations"/>
</form>
</body>
</html>