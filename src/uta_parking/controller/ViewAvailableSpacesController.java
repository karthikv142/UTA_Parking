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
import uta_parking.data.UserDAO;
import uta_parking.model.AvailableSpaces;
import uta_parking.model.AvailableSpacesErrorMsgs;
import uta_parking.model.Parking;
import uta_parking.model.SearchUserAdmin;

/**
 * Servlet implementation class ViewAvailableSpacesController
 */
@WebServlet(description = "View the number of spaces available in a parking lot", urlPatterns = { "/ViewAvailableSpacesController" })
public class ViewAvailableSpacesController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAvailableSpacesController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession();
		
		String parkingType 	= request.getParameter("parking_type");
		
		AvailableSpaces availableSpaces = new AvailableSpaces(parkingType);
		AvailableSpacesErrorMsgs errorMsgs = new AvailableSpacesErrorMsgs();
		
		ArrayList<Parking> spots = ParkingDAO.listAvailableSpots(parkingType);
		
		
		session.setAttribute("ParkingSpaces", spots);
		getServletContext().getRequestDispatcher("/listParkingSpaces.jsp").forward(request, response);
		
//		UserDAO.UpdateUserDetails(editedUser);
	}

}
