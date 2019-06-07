<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sorry</title>
</head>
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
<h2 style ="background-color: white; color: black; border: none; ">
<input name="errMsg" value="<c:out value='${revokedError}'/>" type="text" style ="background-color: white; color: red; border: none; width:800px" disabled="disabled">
<br>
Sorry. You cannot make any reservations on our system at the moment. This is due to you reservation abilities being revoked on account of previous violations. 

 </h2>
<h4 style ="background-color: white; border: none; ">
 
 Please contact an administrator at admin_utaParking@uta.edu
  <input name="parking_typeError" value="<c:out value='${errorMsgs.parking_typeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> 
  <input name="start_timeError" value="<c:out value='${errorMsgs.start_timeError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60"> 
  <input name="durationError" value="<c:out value='${errorMsgs.durationError}'/>" type="text" style ="background-color: white; color: red; border: none; width: 800px" disabled="disabled" maxlength="60">
 
 </h4>
</body>
</html>