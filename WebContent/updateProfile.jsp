<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
 pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update profile</title>
</head>
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>


<table>
 <tr>
 <td>
 <h3> Update profile:</h3>
 <form name="updateProfileForm" action="/UTAParking/UpdateProfileController" method="post">
 

 <table border="1" align="left" class="myTable" style="table-layout: fixed; width: 35%; word-wrap:break-word; "> 			
  		<tr>
  			<td>Username:</td>
		    <td><c:out value="${user.user_name}" /></td>
  		</tr>
  		<tr>
		    <td>UTA ID:</td>
		    <td><c:out value="${user.uta_id}" /> </td>
  		</tr>
  		<tr>
		    <td>Role:</td>
		    <td><c:out value="${user.role}" /></td>
  		</tr>
  		<tr>
		    <td>Phone:</td>
		    <td><c:out value="${user.phone}" /></td>
  		</tr>
  		<tr>
		    <td>Email:</td>
		    <td><c:out value="${user.email}" /></td>
  		</tr>
  		<tr>
		    <td>License plate no::</td>
		    <td><c:out value="${user.license_no}" /></td>
  		</tr>
  		</table><table style="table-layout: fixed; width: 100%">

 <tr>
 <td style="width: 281px; height: 48px; "> Select the field you want to modify: </td>
 <td style="width: 196px; height: 49px; ">
 
  <select name="fieldToModify">
  <option value="password" selected>password</option>
  <option value="phone" > phone</option>
  <option value="email">email</option>
  <option value="license_no">license plate no</option>
</select>

 <input name="fieldToModifyValue" value="<c:out value='${fieldToModifyValue}'/>" type="text" maxlength="45"> 
 
 	<td> <input name="modifyError" value="<c:out value='${modifyError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> </td>
 </tr>
 <tr>

 
 </tr>
 </table>
 <input name="action" value="updateProfile" type="hidden">
	<input name="updateProfileButton" type="submit" value="Update profile">
				</form>
</td>
</tr>
</table>

<h4><a href="/UTAParking/userHome.jsp">Go back to home page</a></h4>
</body>
</html>