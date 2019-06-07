package uta_parking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ReservationDAO;
import uta_parking.data.UserDAO;
import uta_parking.model.Reservation;
import uta_parking.model.SearchUserManager;

/**
 * Servlet implementation class viewAllReservationController
 */
@WebServlet("/viewAllReservationController")
public class viewAllReservationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public viewAllReservationController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		System.out.println("getting here");
		if(action.equalsIgnoreCase("getReservations")) {
			
			System.out.println("stop 1");
			ArrayList<Reservation> reservedSpotsInDB = new ArrayList<Reservation>();
			
			System.out.println("stop 2");
			reservedSpotsInDB = ReservationDAO.getReservationDetails();
			session.setAttribute("reserve", reservedSpotsInDB);
			
			url = "/viewAllReservations.jsp";			
		}
		getServletContext().getRequestDispatcher("/viewAllReservations.jsp").forward(request, response);
	}
}
