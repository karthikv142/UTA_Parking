<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Confirm reservation</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>

<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<form name="paymentForm" action="/UTAParking/MakePaymentController " method="post">
     <div class="mainbar"><div class="submb"></div></div>
     
      
      <h3> Reservation summary:</h3>
      <h4> Today's date: <c:out value="${confirmReservation.date}" /></h4>
      
       <table  border ="1" class="myTable" style="table-layout: fixed; width: 35%; word-wrap:break-word;"> 			
  		<tr>
		    <td>Start time:</td>
		    <td><c:out value="${confirmReservation.start_time}" /></td>
  		</tr>
  		<tr>
		    <td>Duration:</td>
		    <td><c:out value="${confirmReservation.duration}" /> minutes</td>
  		</tr>
  		<tr>
		    <td>Parking type:</td>
		    <td><c:out value="${confirmReservation.parking_type}" /></td>
  		</tr>
  		<tr>
		    <td>Parking area name:</td>
		    <td><c:out value="${confirmReservation.parking_area_name}" /></td>
  		</tr>
  		<tr>
		    <td>Floor no:</td>
		    <td><c:out value="${confirmReservation.floor}" /></td>
  		</tr>
  		<tr>
		    <td>Assigned spot number:</td>
		    <td><c:out value="${confirmReservation.spot_number}" /></td>
  		</tr>
  		<tr>
		    <td>Total cost (Tax included):</td>
		    <td>$<c:out value="${confirmReservation.cost}" /></td>
  		</tr>
  		<tr>
		    <td>Credit card type:</td>
		    <td> <select name="credit_card_type">
					  <option value="amex" selected>American Express</option>
					  <option value="visa">Visa</option>
					  <option value="mastercard">MasterCard</option>
				</select></td>
  		</tr>
  		<tr>
		    <td>Credit card number:</td>
		    <td>
		    <input name="credit_card_number" value="<c:out value='${payment.credit_card_number}'/>" type="text" maxlength="45"> 		    
		    </td>
  		</tr>
</table>
<table>
<tr>
	<td><input name="paymentError" value="<c:out value='${paymentErrorMsg.paymentError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"></td> 
	<td></td>
</tr>
<tr>
<td>
	<input name="action" value="makePayment" type="hidden">
	<input name="payButton" type="submit" value="Confirm/Make a payment" style="width: 237px; height: 22px; ">
</td>
</tr>

</table>
</form>
</body>

</html>