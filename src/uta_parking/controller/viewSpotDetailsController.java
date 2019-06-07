package uta_parking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ParkingDAO;
import uta_parking.data.ReservationDAO;
import uta_parking.model.AvailableSpaces;
import uta_parking.model.AvailableSpacesErrorMsgs;
import uta_parking.model.Parking;
import uta_parking.model.ParkingSpot;
import uta_parking.model.ParkingSpotErrorMsgs;
import uta_parking.model.Reservation;

/**
 * Servlet implementation class viewSpotDetailsController
 */
@WebServlet("/viewSpotDetailsController")
public class viewSpotDetailsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewSpotDetailsController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String url ="";
		ParkingSpot parkingSpot = new ParkingSpot();
		parkingSpot.setFloor(request.getParameter("floor"));
		parkingSpot.setParkingAreaName(request.getParameter("parkingAreaName"));
		parkingSpot.setSpot(request.getParameter("spot"));
		
		ParkingSpotErrorMsgs errorMsgs = new ParkingSpotErrorMsgs();
		errorMsgs = parkingSpot.validateParkingSpotDetails(parkingSpot);
		
		if(errorMsgs.getParkingSpotErrors()!="") {
			session.setAttribute("ParkingSpotErrorMsgs", errorMsgs);
			url = "/viewParkingSpotDetails.jsp";
		}else {
			ArrayList<Reservation> spots = ReservationDAO.getSpotDetails(parkingSpot);
			session.setAttribute("ParkingSpots", spots);
			url = "/listParkingSpotDetails.jsp";
		}		
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
