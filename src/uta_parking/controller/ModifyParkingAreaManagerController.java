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

@WebServlet("/ModifyParkingAreaManagerController")
public class ModifyParkingAreaManagerController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(action.equalsIgnoreCase("Submit")) {
			String parking_area_name = request.getParameter("parking_area_name");			
			String type = request.getParameter("type");
			String floor = request.getParameter("floor");
			String capacity = request.getParameter("capacity");
			String availability = request.getParameter("availability");
			String cart = request.getParameter("cart");			
			String camera = request.getParameter("camera");
			String history = request.getParameter("history");
			
			ModifyParkingAreaManager modifyparkingareamanager = new ModifyParkingAreaManager();   
			
			modifyparkingareamanager.setParking_area_name(request.getParameter("parking_area_name"));
			modifyparkingareamanager.setType(request.getParameter("type"));
			modifyparkingareamanager.setFloor(request.getParameter("floor"));
//			modifyparkingareamanager.setCapacity(request.getParameter("capacity"));
			modifyparkingareamanager.setAvailability(request.getParameter("availability"));
			modifyparkingareamanager.setCart(request.getParameter("cart"));
			modifyparkingareamanager.setCamera(request.getParameter("camera"));
			modifyparkingareamanager.setHistory(request.getParameter("history"));
			
			String result = ManagerDAO.modifyParkingArea(parking_area_name, type, floor, capacity, availability, cart, camera, history);			

			url = "/modifyParkingAreaManagerConfirmation.jsp";			
			}
			getServletContext().getRequestDispatcher("/modifyParkingAreaManagerConfirmation.jsp").forward(request, response);
		}
}