<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Request reservation</title>
</head>
<body>

<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<input name="errMsg" value="<c:out value='${errorMsgs.errorMsg}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<table style="table-layout: fixed; width: 100%">	
 <tr>
 <td>
 <form name="requestReservationForm" action="/UTAParking/RequestReservationController" method="post">
 <h3> Please select your search criteria:</h3>
 <table>
 <tr>
 </tr>
 <tr>
 <td> Parking type (*): </td>
 <td>
 <select name="parking_type">
  <option value="access" selected>access</option>
  <option value="basic">basic</option>
  <option value="midrange">midrange</option>
  
  <option value="premium">premium</option>
</select>
<td style="width: 196px; height: 49px; "> 

 <input name="parking_typeError" value="<c:out value='${errorMsgs.parking_typeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>

 </tr>
 <tr>
 <td> Start time (*): </td>
 
 <td>
 <input type="time" name="start_time" step="900" value="<c:out value='${requestreservation.start_time}'/>"maxlength="45" >
 	<td> <input name="start_timeError" value="<c:out value='${errorMsgs.start_timeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 179px; "> Duration (in minutes) (*): </td>
 <td style="width: 88px; ">
 <input name="duration" value="<c:out value='${requestreservation.duration}'/>" type="text" maxlength="5"> 
 	<td> <input name="durationError" value="<c:out value='${errorMsgs.durationError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>
 <td style="width: 160px; height: 47px; "> Options: </td>
 <td style="width: 221px; ">
 <input type="checkbox" id="cameraOption" name="checkedOptions" value="camera">Camera
<input type="checkbox" id ="cartOption" name="checkedOptions" value="cart" >Cart
<input type="checkbox" id ="historyOption" name="checkedOptions" value="history">History
 </tr>
 <tr>
 <td colspan="2">(*) Mandatory field</td>
 </tr>
 </table>
 <input name="action" value="requestReservation" type="hidden">
	<input name="requestReservationButton" type="submit" value="Request reservation">
</form>
</td>
</tr>
</table>
</body>
</html>