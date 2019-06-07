<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>View my reservation status and violations</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="style.css" rel="stylesheet" type="text/css" />
<body>

<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>

<form name="viewReservationStatus" action="/UTAParking/ViewMyReservationStatusController " method="post">
     <div class="mainbar"><div class="submb"></div></div> 
      
      <h3> View my active reservation status and violations</h3>
      <h4> Today's date: <c:out value="${today}" /></h4>

       <table  border ="1" class="myTable" style="table-layout: fixed; width: 35%; word-wrap:break-word;"> 			
  		
  		<tr>
		    <td>Reservation status(1 for active, 0 for revoked):</td>
		    <td> <input type="text" id="userStatus" readonly="readonly" value ="<c:out value="${user.status}"/>"> </td>
  		</tr>
  		<tr>
		    <td>Number of no shows:</td>
		    <td><input type="text" id= "userNoshow" readonly="readonly" value ="<c:out value="${user.noshow}"/>" ></td>
  		</tr>
  		<tr>
		    <td>Number of overstays:</td>
		    <td><input type="text" id= "userOverstay" readonly="readonly" value = "<c:out value="${user.overstay}"/>" ></td>

  		</tr>
  
</table>

<table>
<tr>
	
</tr>
<tr>

</tr>


</table>
</form>
</body>

</html>