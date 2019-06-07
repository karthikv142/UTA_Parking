package uta_parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ManagerDAO;
//import uta_parking.data.UserDAO;
import uta_parking.model.ModifyParkingAreaManager;

@WebServlet("/DeleteReservationManagerController")
public class DeleteReservationManagerController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(request.getParameter("Submit") != null) {
			System.out.println("In Delete Reservation Controller");			
		}
		
		String reserve_id = request.getParameter("reserve_id");
		System.out.println(reserve_id);
		
		ManagerDAO.deletePastReservations(reserve_id);
		
//		if(action.equalsIgnoreCase("Submit")) {
			
//			String result = ManagerDAO.deletePastReservations();//					
//			}
		
		url = "/deleteReservationsManagerConfirmation.jsp";
		getServletContext().getRequestDispatcher("/deleteReservationsManagerConfirmation.jsp").forward(request, response);
		}
}