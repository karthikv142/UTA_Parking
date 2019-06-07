<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
	<form name="setViolations" action="/UTAParking/ViolationsController" method="post">
		<table style="table-layout: fixed; width: 100%" class="myTable">
			<tr class="myTableRow">
				<th class="myTableHead" style="width: 180px;">Username</th>
				<th class="myTableHead" style="width: 180px;">UTA ID</th>
				<th class="myTableHead" style="width: 180px;">No.of No-Shows</th>
				<th class="myTableHead" style="width: 180px;">Overstay</th>
			</tr>
			<c:forEach items="${ParkingUsers}" var="item">
				<tr class="myTableRow">
					<td class="myTableCell" style="width: 180px;"><c:out
							value="${item.user_name}"/>
							<input type="hidden" name="user_name" value="${item.user_name}"></input></td>
					<td class="myTableCell" style="width: 180px;"><c:out
							value="${item.uta_id}" /></td>
					<td class="myTableCell" style="width: 180px;"><select
						name=noshowid>
							<option value="${item.noshow}"></option>
							<option value="1">1</option>
							<option value="2">2</option>
							<option value="3">3</option>
					</select></td>
					<td class="myTableCell" style="width: 10px;"><input
						name=overstayid type="radio" name="radioButton" value=1></td>
				</tr>
			</c:forEach>
		</table>	
			<input id ="Update" type="submit" name='{item.user_name}' value="update" /> 
	</form>
	<form action="index.jsp">
	</form>
</body>
</html>