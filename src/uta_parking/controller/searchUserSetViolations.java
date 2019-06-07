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
import uta_parking.model.SearchUserErrorMsgs;
import uta_parking.model.SearchUserManager;
import uta_parking.model.User;

@WebServlet("/searchUserSetViolations")
public class searchUserSetViolations extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public searchUserSetViolations() {
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

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";

		if (action.equalsIgnoreCase("setViolations")) {
			if (request.getParameter("searchUserAdminButton") != null) {

				SearchUserManager searchusermanager = new SearchUserManager();
				SearchUserErrorMsgs SearchErrorMsg = new SearchUserErrorMsgs();

				ArrayList<SearchUserManager> parkinguserInDB = new ArrayList<SearchUserManager>();

				//session.setAttribute("SearchUserManager", searchusermanager);

				String parkingUsername = request.getParameter("username");

				searchusermanager.setUser_name(parkingUsername);
				
				User violation_user = new  User();
				violation_user.setUsername(parkingUsername);

				SearchErrorMsg.setSearch_usernameError(searchusermanager.validateSearchUser(parkingUsername));

				System.out.println("Error msg is: " + SearchErrorMsg.getSearch_usernameError());

				if (SearchErrorMsg.getSearch_usernameError().equals("")) {

					parkinguserInDB = UserDAO.listParkingUserDetails(parkingUsername);
					
					System.out.print("BackfromDAO");
					System.out.println(parkinguserInDB.toString());
					session.setAttribute("ParkingUsers", parkinguserInDB);

					session.removeAttribute("searchErrorMsg");
					session.removeAttribute("SearchUserManager");

					System.out.println("Before url list:");
					url = "/SelectSetViolations.jsp";

				}

				else {
					session.setAttribute("searchErrorMsg", SearchErrorMsg);
					session.setAttribute("SearchUserManager", searchusermanager);

					System.out.println("Before url search:");

					url = "/setViolations.jsp";
				}

			}
		}

		getServletContext().getRequestDispatcher(url).forward(request, response);
	}

}
