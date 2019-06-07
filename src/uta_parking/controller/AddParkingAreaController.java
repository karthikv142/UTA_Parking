package uta_parking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ParkingDAO;
import uta_parking.data.UserDAO;
import uta_parking.model.ParkingArea;
import uta_parking.model.ParkingAreaErrorMsgs;
import uta_parking.model.SearchUserAdmin;

/**
 * Servlet implementation class ManageParkingAreaController
 */
@WebServlet("/AddParkingAreaController")
public class AddParkingAreaController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddParkingAreaController() {
        super();
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
			String url ="";
			ParkingArea newParkingArea = new ParkingArea();
			ParkingAreaErrorMsgs areaErrorMsgs = new ParkingAreaErrorMsgs();
			String parkingAreaName 	= request.getParameter("parkingAreaName");
			String capacity 		= request.getParameter("capacity");
			String floors 			= request.getParameter("floors");

			String options[] = request.getParameterValues("checkedCartOptions");
			String camera_option ="0";
			String cart_option ="0";
			String history_option ="0";
			
			if (options != null)
				//Get the values from checkboxes
				for(int i=0; i<options.length; i++)
					{
						if (options[i].equals("camera"))
							camera_option="1";
						else if (options[i].equals("cart"))
							cart_option="1";
						else if (options[i].equals("history"))
							history_option="1";
					}

			String types[] = request.getParameterValues("checkedTypeOptions");
			String basic ="0";
			String midRange="0";
			String premium="0";
			String access="0";
			if (types != null)
				//Get the values from checkboxes
				for(int i=0; i<types.length; i++)
					{
						if (types[i].equals("basic"))
							basic="1";
						else if (types[i].equals("midRange"))
							midRange="1";
						else if (types[i].equals("premium"))
							premium="1";
						else if (types[i].equals("access"))
							access = "1";
					}
			newParkingArea.setParkingAreaName(parkingAreaName);
			newParkingArea.setCapacity(capacity);
			newParkingArea.setAccess(access);
			newParkingArea.setBasic(basic);
			newParkingArea.setMidRange(midRange);
			newParkingArea.setPremium(premium);
			newParkingArea.setCart(cart_option);
			newParkingArea.setCamera(camera_option);
			newParkingArea.setHistory(history_option);
			newParkingArea.setFloor(floors);
			areaErrorMsgs = newParkingArea.validateParkingArea(newParkingArea);
			
			if(areaErrorMsgs.getErrorMsg()=="") {
				ParkingDAO.addParkingArea(newParkingArea);
				url = "/viewAvailableSpaces.jsp";
			}else {
				url = "/addParkingArea.jsp";
				session.setAttribute("parkingAreaErrors", areaErrorMsgs);
			}
			
			getServletContext().getRequestDispatcher(url).forward(request, response);	
	}

}
