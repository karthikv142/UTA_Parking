<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Available parking list</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>

<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<form name="requestReservationForm" action="/UTAParking/RequestReservationController" method="post">
     <div class="mainbar"><div class="submb"></div></div>
     
      
      <h3> Available parking list:</h3>
       <table border="1" class="myTable" style="table-layout: fixed; width: 100%"> 
			<tr class="myTableRow"> 
				<th class="myTableHead" style="width: 129px; height: 73px">Select</th>
				<th class="myTableHead" style="width: 129px; height: 73px">Parking area name</th>
				<th class="myTableHead" style="width: 105px; height: 55px">Parking type</th>
				<th class="myTableHead" style="width: 114px; height: 63px">Floor no</th>
				<th class="myTableHead" style="width: 129px; height: 66px">Total available spots</th>
				<th class="myTableHead" style="width: 115px; height: 67px">Options fees(tax included)</th> 
			</tr>
			<c:forEach items="${parking}" var="item"  varStatus="status">
			<tr class="myTableRow"> 
				<th class="myTableCell" style="width: 10px; "><input type="radio" id="radioReservation${status.count}" name="radioReservation" value="${status.count}"></th> 
				<th class="myTableCell" style="width: 90px; "><c:out value="${item.parking_area_name}" /></th> 
				<th class="myTableCell" style="width: 50px; "><c:out value="${item.parking_type}" /></th>
				<th class="myTableCell" style="width: 11px; "><c:out value="${item.floor}" /></th>
				<th class="myTableCell" style="width: 20x;  "><c:out value="${item.availability}" /></th>
				<th class="myTableCell" style="width: 20px;  ">$<c:out value="${requestreservation.cost}"/></th>
			
		   </tr>
		   </c:forEach>
	
</table>
<input id= "overlapError" name="errMsg" value="<c:out value='${confirmErrorMsg.overlapError}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<input id= "maxForTheDayError" name="errMsg" value="<c:out value='${confirmErrorMsg.maxForTheDayError}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<br/>
<input name="action" value="requestReservation" type="hidden">
<input name="confirmReservationButton" type="submit" value="Confirm and make a payment" style="width: 237px; height: 22px; ">
</form>
</body>

</html>