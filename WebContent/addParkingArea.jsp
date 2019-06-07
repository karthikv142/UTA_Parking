<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Parking Aresa</title>
</head>
<body style="width: 450px;">
	<form name="logout" action="/UTAParking/LogoutController" method="get" style="width:300px;">
<input id = "logout" type="submit" value="LogOut"/>
    </form>
	<table>
		<tr>
			<td>

				<form name="addParkingArea"
					action="/UTAParking/AddParkingAreaController" method="post"
					style="width: 300px;">
					<h3>Adding a parking area:</h3>
					<table>
						<tr>
							<td>Parking Area Name: <input name="parkingAreaName"
								value='${parkingArea.parkingAreaName}' /></td>
						</tr>
						<tr>
							<td><input name="areaNameError"
								value="<c:out value='${parkingAreaErrors.parking_areanameError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>
						<tr>
							<td>Capacity: <input name="capacity"
								value='${parkingArea.capacity}' /></td>
						</tr>
						<tr>
							<td><input name="capacityError"
								value="<c:out value='${parkingAreaErrors.capacityError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>
						<tr>
							<td>Floor <input name="floors" value='${parkingArea.floor}' /></td>
						</tr>
						<tr>
							<td><input name="FloorError"
								value="<c:out value='${parkingAreaErrors.floorError}'/>"
								type="text"
								style="background-color: white; color: red; border: none; width: 800px"
								disabled="disabled" maxlength="60"></td>
						</tr>
						<tr>
							<td style="width: 160px; height: 20px;">Types Supported</td>
						</tr>
						<tr>
							<td style="width: 221px;"><input type="checkbox" id="basic"
								name="checkedTypeOptions" value="basic">Basic <input id="midRange"
								type="checkbox" name="checkedTypeOptions" value="midRange">Mid
								Range <input id="premium" type="checkbox" name="checkedTypeOptions"
								value="premium">Premium <input id="access" type="checkbox"
								name="checkedTypeOptions" value="access">Access</td>
						</tr>
						
						<tr><td><input name="typeError"
                                value="<c:out value='${parkingAreaErrors.typeError}'/>"
                                type="text"
                                style="background-color: white; color: red; border: none; width: 800px"
                                disabled="disabled" maxlength="60"></td></tr>

						<tr>
							<td style="width: 160px; height: 10px;">Options Supported</td>
						</tr>
							<tr>
							<td style="width: 221px;"><input type="checkbox"
								name="checkedCartOptions" value="camera">Camera <input
								type="checkbox" name="checkedCartOptions" value="cart">Cart
								<input type="checkbox" name="checkedCartOptions" value="history">History
						</tr>
						<tr>
							<td><input name="action" value="searchUserAdmin"
								type="hidden"> <input name="addParkingAreaButton"
								type="submit" value="Add ParkingArea"></td>
						</tr>
						<tr>
						</tr>
					</table>
				</form>
	</table>
</body>
</html>