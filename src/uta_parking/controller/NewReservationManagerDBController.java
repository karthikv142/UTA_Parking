package uta_parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ManagerDAO;
import uta_parking.model.DeleteReservationsManager;
import uta_parking.model.DeleteReservationsManagerErrorMessages;
import uta_parking.model.ModifyParkingAreaManager;

@WebServlet("/NewReservationManagerDBController")
public class NewReservationManagerDBController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";

		if (action.equalsIgnoreCase("Submit")) {

			String reserve_id = request.getParameter("reserve_id");
			System.out.println("Reserve ID: " + reserve_id);

			String parking_area_name = request.getParameter("parking_area_name");
			System.out.println(parking_area_name);

			String type = request.getParameter("type");
			System.out.println(type);

			String floor = request.getParameter("floor");
			System.out.println(floor);

			String spot_num = request.getParameter("spot_num");
			System.out.println(spot_num);
			String start_time = request.getParameter("start_time");
			System.out.println(start_time);
			String duration = request.getParameter("duration");
			System.out.println(duration);

			String cart = request.getParameter("cart");
			System.out.println(cart);
			String camera = request.getParameter("camera");
			System.out.println(camera);
			String history = request.getParameter("history");
			System.out.println(history);

			DeleteReservationsManager deletereservationsmanager = new DeleteReservationsManager();
			DeleteReservationsManagerErrorMessages deleteReservationsErrMsgs = new DeleteReservationsManagerErrorMessages();

			deletereservationsmanager.setParking_area_name(request.getParameter("parking_area_name"));
			deletereservationsmanager.setFloor(request.getParameter("floor"));
			deletereservationsmanager.setSpot_num(request.getParameter("spot_num"));
			deletereservationsmanager.setStart_time(request.getParameter("start_time"));
			deletereservationsmanager.setDuration(request.getParameter("duration"));
			deletereservationsmanager.setCart(request.getParameter("cart"));
			deletereservationsmanager.setCamera(request.getParameter("camera"));
			deletereservationsmanager.setHistory(request.getParameter("history"));

			deleteReservationsErrMsgs = deletereservationsmanager.validateDeleteReservation();

			if (deleteReservationsErrMsgs.getErrorExist()=="") {
				ManagerDAO.newReservationInDB(reserve_id, parking_area_name, type, floor, spot_num, start_time,
						duration, cart, camera, history);

				// String result = ManagerDAO.modifyParkingArea(parking_area_name, type, floor,
				// capacity, availability, cart, camera, history);

//				url = "/modifyReservationsManagerConfirmation.jsp";
				url = "/newReservation.jsp";
				System.out.println("In If Block- URL: " + url);
			}
			else
			{
				url = "/newReservation.jsp";
				System.out.println("In Else Block -URL :" + url);
				session.setAttribute("deleteReservationError", deleteReservationsErrMsgs);
			}
		}
		System.out.println("After If- Else Block -URL :" + url);
		getServletContext().getRequestDispatcher(url).forward(request,
				response);
	}
}