package uta_parking.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.UserDAO;
import uta_parking.model.SearchUserAdmin;
import uta_parking.model.SearchUserErrorMsgs;
import uta_parking.model.SearchUserManager;
import uta_parking.model.SetViolations;
import uta_parking.model.SetViolationsErrorMsgs;

@WebServlet("/ViolationsController")
public class ViolationsController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ViolationsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//String update = request.getParameter("update");
		HttpSession session = request.getSession();
		String url = "";

		//if (update.equalsIgnoreCase("setViolations")) {

			SetViolations setviolations = new SetViolations();
			//SetViolationsErrorMsgs SetViolationsErrorMsg = new SetViolationsErrorMsgs();

			//ArrayList<SearchUserManager> parkinguserInDB = new ArrayList<SearchUserManager>();

			session.setAttribute("SetViolations", setviolations);

			//System.out.println("terminal1");
			String user_name = request.getParameter("user_name");
			int noshow = Integer.parseInt(request.getParameter("noshowid"));
			//int overstay = Integer.parseInt(request.getParameter("overstayid"));
			int overstay;
			
			if(request.getParameter("overstayid") == null)
				overstay = 0;
			else
				overstay = 1;
			
			System.out.println("User: "+ user_name);
			System.out.println("No Show: " + noshow);
			System.out.println("OverStays: " + overstay);

			setviolations.setUserName(user_name);
			setviolations.setNoShow(noshow);
			setviolations.setOverStay(overstay);

			// SearchErrorMsg.setSearch_usernameError(searchusermanager.validateSearchUser(parkingUsername));

			// System.out.println("Error msg is: " +
			// SearchErrorMsg.getSearch_usernameError());

			//if (SetViolationsErrorMsg.getSearch_usernameError().equals("")) {

				//System.out.println("terminal2");
				UserDAO.updateUser(setviolations);

				//System.out.println("back from dao");
				//session.setAttribute("ParkingUsers", parkinguserInDB);

				//session.removeAttribute("searchErrorMsg");
				//session.removeAttribute("SearchUserManager");

				System.out.println("Before url list:");
				url = "/managerHome.jsp";

//			}
//
//			else {
//				// session.setAttribute("searchErrorMsg", SearchErrorMsg);
//				// session.setAttribute("SearchUserManager", searchusermanager);
//
//				System.out.println("Before url search:");
//
//				// url = "/searchUserManager.jsp";
//			}
		//}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
