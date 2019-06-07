<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View my reservations</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>

<form name="editReservationForm" action="/UTAParking/ViewMyReservationsController " method="post">
     <div class="mainbar"><div class="submb"></div></div> 
      
      <h3> View my active reservations</h3>
      <h4> Today's date: <c:out value="${today}" /></h4>
      
      <input name="emptyList" value="<c:out value='${emptyList}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
      
      
      <c:forEach items="${userReservation}" var="item"  varStatus="status">

       <table  border ="1"  align="center" class="myTable" style="table-layout: fixed; width: 35%; word-wrap:break-word; "> 			
  		<tr>
  			
		    <td><input type="radio" id="radioReservation${status.count}"  name="radioReservation" value="${status.count}"> Start time:</td>
		    <td><c:out value="${item.start_time}" /></td>
  		</tr>
  		<tr>
		    <td>Duration:</td>
		    <td><c:out value="${item.duration}" /> minutes</td>
  		</tr>
  		<tr>
		    <td>Parking type:</td>
		    <td><c:out value="${item.parking_type}" /></td>
  		</tr>
  		<tr>
		    <td>Parking area name:</td>
		    <td><c:out value="${item.parking_area_name}" /></td>
  		</tr>
  		<tr>
		    <td>Floor no:</td>
		    <td><c:out value="${item.floor}" /></td>
  		</tr>
  		<tr>
		    <td>Assigned spot number:</td>
		    <td><c:out value="${item.spot_number}" /></td>
  		</tr>
		 <tr>
		    <td style="width: 180px; ">Selected options<br>(1 for yes, 0 for no):</td>
				<td>
				    Cart:<c:out value="${item.cart_option}" /> <br/>
				    Camera:<c:out value="${item.camera_option}" /><br/>
				    History:<c:out value="${item.history_option}" /><br/>
		   		 </td>
		
  		</tr>
</table>

<hr width="200" size="1" style="width: 1109px; height: 3px" color="green"/>

 </c:forEach>


<table align="center">
<tr>
	
</tr>
<tr>
<td>

     
	<input name="action" value="modifyReservation" type="hidden">
	<input name="modifyReservationButton" type="submit" value="Modify reservation" style="width: 237px; height: 39px; ">
	
	<input name="action" value="cancelReservation" type="hidden">
	<input name="cancelReservationButton" type="submit" value="cancel reservation" style="width: 237px; height: 39px; ">
</td>
</tr>



</table>
</form>
</body>

</html>