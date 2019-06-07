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
import uta_parking.model.ModifyParkingSpotsManager;

@WebServlet("/ManageParkingSpotManagerController")
public class ManageParkingSpotManagerController extends HttpServlet{
	
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
//			String spot_num = request.getParameter("spot_num");

			ArrayList<ModifyParkingSpotsManager> spotsInDB = new ArrayList<ModifyParkingSpotsManager>();
			
			ModifyParkingAreaManager modifyparkingareamanager = new ModifyParkingAreaManager();   
//			modifyparkingareamanager.setParking_area_name(request.getParameter("parking_area_name"));
//			modifyparkingareamanager.setType(request.getParameter("type"));
//			modifyparkingareamanager.setFloor(request.getParameter("floor"));
//			modifyparkingareamanager.setSpot_num(request.getParameter("spot_num"));
			
			spotsInDB = ManagerDAO.listParkingSpotDetails(parking_area_name, type, floor);
			
			System.out.println("Control Returned from DAO");
			
			session.setAttribute("spotsInDB", spotsInDB);			

			url = "/viewParkingSpotsManager.jsp.";			
			}
			getServletContext().getRequestDispatcher("/viewParkingSpotsManager.jsp").forward(request, response);
		}
}