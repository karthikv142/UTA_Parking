<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit User Details</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
<td>


<form action= "/UTAParking/EditUserAdminController" name ="userDetails">
	<c:forEach items="${ParkingUsers}" var="item">
				<label for="uname">User Name</label>
 				<input type="text" name="userName" id="uname" value= "<c:out value="${item.userName}" />"readonly>
 				<input name="usernameError" value="<c:out value='${searchErrors.search_usernameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
				
				<label for="role">role</label>
                <input type="text" name="role" id="role" value= "<c:out value="${item.role}" />">
                <input name="roleError" value="<c:out value='${searchErrors.roleError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
                                		
				<label for="uID">UTA ID </label>
				<input type="text" name="utaID" id="uID" value= "<c:out value="${item.uta_id}" />"readonly>
				<input name="UTAIDError" value="<c:out value='${searchErrors.utaIDError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
				
				<label for="phno">Phone </label>
				<input type="text" name="phone" id="phno" value= "<c:out value="${item.phone}" />">
				<input name="phoneError" value="<c:out value='${searchErrors.phoneError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
				
				<label for="uemail">Email </label>
				<input type="text" name="userEmail" id="uemail" value= "<c:out value="${item.email}" />">
				<input name="emailError" value="<c:out value='${searchErrors.userEmailError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
				
				<label for="ulicense">License Number:  </label>
				<input type="text" name="license" id="ulicense" value= "<c:out value="${item.licenseNo}" />">
				<input name="licenseError" value="<c:out value='${searchErrors.licenseError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
				
				<label for="ustatus">User Status </label>
				<input type="text" name="status" id="ustatus" value= "<c:out value="${item.status}" />">
				<input name="statusError" value="<c:out value='${searchErrors.userStatusError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
				
				<label for="reason">Reason </label>
				<input type="text" name="reason" id="reason" value= "<c:out value="${item.reason}" />">
				<input name="reasonError" value="<c:out value='${searchErrors.reasonError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
	 </c:forEach>	
	 
	 <input type="submit" name ="submit" value = "editUserAdmin">
</form>
</table>
</body>
</html>