package uta_parking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.model.ModifyParkingAreaManager;

@WebServlet("/SetSpotAvailabilityManagerController")
public class SetSpotAvailabilityManagerController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(action.equalsIgnoreCase("modifySpots")) {
			String parking_area_name = request.getParameter("parking_area_name");			
			String type = request.getParameter("type");
			String floor = request.getParameter("floor");
			String spot = request.getParameter("spot");
			
			System.out.println(parking_area_name);

//			ArrayList<ModifyParkingAreaManager> spotsInDB = new ArrayList<ModifyParkingAreaManager>();
			
//			ModifyParkingAreaManager modifyparkingareamanager = new ModifyParkingAreaManager();   
//			modifyparkingareamanager.setParking_area_name(request.getParameter("parking_area_name"));
//			modifyparkingareamanager.setType(request.getParameter("type"));
//			modifyparkingareamanager.setFloor(Integer.parseInt(request.getParameter("floor")));
			
//			spotsInDB = ManagerDAO.listParkingSpotDetails(parking_area_name, type, Integer.parseInt(floor));
			
//			session.setAttribute("spotsInDB", spotsInDB);
			
//			String result = ManagerDAO.modifyParkingArea(parking_area_name, type, Integer.parseInt(floor), Integer.parseInt(capacity), Integer.parseInt(availability), Integer.parseInt(cart), Integer.parseInt(camera), Integer.parseInt(history));			

			url = "/index.jsp.";			
			}
			getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
		}
}