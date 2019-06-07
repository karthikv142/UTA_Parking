package uta_parking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ManagerDAO;
import uta_parking.model.ModifyParkingAreaManager;

@WebServlet("/DisplayReservationsManagerController")
public class DisplayReservationsManagerController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(action.equalsIgnoreCase("Submit")) {
			
			ArrayList<ModifyParkingAreaManager> reservationsInDB = new ArrayList<ModifyParkingAreaManager>();
			
			ModifyParkingAreaManager modifyparkingareamanager = new ModifyParkingAreaManager();
			
			reservationsInDB = ManagerDAO.listReservationsInDB();
			
			session.setAttribute("Reservations", reservationsInDB);			

			url = "/deleteReservationManager.jsp.";			
			}
			getServletContext().getRequestDispatcher("/deleteReservationManager.jsp").forward(request, response);
		}
}