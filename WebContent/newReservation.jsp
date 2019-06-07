<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>EZPark</title>
</head>
<body style="width: 450px;">
	<table>
		<tr>
			<td>
				<form name="newReservationManagerDBController"
					action="NewReservationManagerDBController" method="post"
					style="width: 300px;">
					<table style="width: 100%">
						<tr>
							<td><h1>Modify Existing Reservation</h1></td>
						</tr>
						<tr>
							<td>Parking Area</td>
							<td><select name="parking_area_name">
									<option value="west garage">West Garage</option>
									<option value="maverick">Maverick</option>
									<option value="davis">Davis</option>
									<option value="neddermann">Neddermann</option>
							</select></td>
						</tr>

						<tr>
							<td>Type</td>
							<td><select name="type">
									<option value="basic">Basic</option>
									<option value="midrange">Midrange</option>
									<option value="premium">Premium</option>
									<option value="access">Access</option>
							</select></td>
						</tr>

						<tr>
							<td>Floor</td>
							<td><select name="floor">
									<option value="7">7</option>
									<option value="6">6</option>
									<option value="5">5</option>
									<option value="4">4</option>
									<option value="3">3</option>
									<option value="2">2</option>
									<option value="1">1</option>
							</select></td>
						</tr>
						
							<tr>
							<td>Duration</td>
							<td><input name="duration"
								value='${DeleteReservationManager.duration}' /></td>
							<td><input id = "durationError" name="deleteReservationError"
								value="<c:out value='${deleteReservationError.duration}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>

						<tr>
							<td>Spot number</td>
							<td><input name="spot_num"
								value='${DeleteReservationManager.spot_num}' /></td>
							<td><input id = "spotError" name="deleteReservationError"
								value="<c:out value='${deleteReservationError.spot_num}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>
						<tr>
							<td>Start time</td>
							<td><input name="start_time"
								value='${DeleteReservationManager.start_time}' /></td>
							<td><input id = "timeError" name="deleteReservationError"
								value="<c:out value='${deleteReservationError.start_time}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>
		
						<tr>
						</tr>

						<tr>
							<td>Cart</td>
							<td><select name="cart">
									<option value="1">1</option>
									<option value="0">0</option>
							</select></td>
						</tr>
						<tr>
							<td>Camera</td>
							<td><select name="camera">
									<option value="1">1</option>
									<option value="0">0</option>
							</select></td>
						</tr>
						<tr>
							<td>History</td>
							<td><select name="history">
									<option value="1">1</option>
									<option value="0">0</option>
							</select></td>
						</tr>
						<tr>
							<td><input type="submit" value="Submit" name="action"></td>
						</tr>
					</table>
				</form>
	</table>
	<input id = "error" name="deleteReservationError"
	value="<c:out value='${deleteReservationError.errorExist}'/>"
	type="text"
	style="background-color: white; color: red; border: none; width: 800px"
	disabled="disabled" maxlength="60">
</body>
</html>