<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Edit Parking Manager Details</title>
</head>
<body style="width: 450px; ">
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<table>
<tr>
<td>
<form action= "/UTAParking/EditManagerProfileContoller" name ="userDetails">
    <c:forEach items="${managerAccount}" var="item">
                <label for="uname">User Name</label>
                <input type="text" name="userName" id="uname" value= "<c:out value="${item.user_name}" />"readonly>
                <input name="usernameError" value="<c:out value='${searchErrors.search_usernameError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
                          
                <label for="uID">UTA ID </label>
                <input type="text" name="utaID" id="uID" value= "<c:out value="${item.uta_id}" />"readonly>
                <input name="UTAIDError" value="<c:out value='${searchErrors.utaIDError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
                
                <label for="phno">Phone(*) </label>
                <input type="text" name="phone" id="phno" value= "<c:out value="${item.phone}" />">
                <input name="phoneError" value="<c:out value='${searchErrors.phoneError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
                
                <label for="uemail">Email(*) </label>
                <input type="text" name="userEmail" id="uemail" value= "<c:out value="${item.email}" />">
                <input name="emailError" value="<c:out value='${searchErrors.userEmailError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
                
                 <label for="Password">Password(*) </label>
                <input type="text" name="password" id="reason" value= "<c:out value="${password}" />">
                 <input name="passwordError" value="<c:out value='${passwordError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"><br>
               
     </c:forEach>   
      <input name="action" value="updateManagerProfile" type="hidden">
     <input type="submit" name ="submit" value = "submit">
</form>
</table>
</body>
</html>