package uta_parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ManagerDAO;

@WebServlet("/ParkingSpotUnavailableManagerController")

public class ParkingSpotUnavailableManagerController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(request.getParameter("Submit") != null) {
			System.out.println("In Spot Unavailable Controller");			
		}
		
		String reserve_id = request.getParameter("reserve_id");
		System.out.println("Spot Unavailable Controller : " + reserve_id);
		
		ManagerDAO.makeSpotUnavailable(reserve_id);
		
//		if(action.equalsIgnoreCase("Submit")) {
			
//			String result = ManagerDAO.deletePastReservations();//					
//			}
		
		url = "/spotUnavailableConfirmation.jsp";
		getServletContext().getRequestDispatcher("/spotUnavailableConfirmation.jsp").forward(request, response);
		}
}