package uta_parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ManagerDAO;

@WebServlet("/NewReservationManagerController")
public class NewReservationManagerController extends HttpServlet{
	
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
		System.out.println("New RSV Manager Controller: " + reserve_id);
		
		ManagerDAO.deletePastReservations(reserve_id);
		
//		if(action.equalsIgnoreCase("Submit")) {
			
//			String result = ManagerDAO.deletePastReservations();//					
//			}
		
		url = "/newReservation.jsp";
		getServletContext().getRequestDispatcher("/newReservation.jsp").forward(request, response);
		}
}