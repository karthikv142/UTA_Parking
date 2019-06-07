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

@WebServlet("/DeletePastReservationsController")
public class DeletePastReservationsController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(action.equalsIgnoreCase("Submit")) {
			
			String result = ManagerDAO.deletePastReservations();
			
			url = "/deletePastReservationsConfirmation.jsp";			
			}
			getServletContext().getRequestDispatcher("/deletePastReservationsConfirmation.jsp").forward(request, response);
		}
}