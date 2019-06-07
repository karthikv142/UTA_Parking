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
import uta_parking.model.Login;
import uta_parking.model.SearchManager;
import uta_parking.model.SearchManagerErrorMsgs;
import uta_parking.model.SearchUserAdmin;
import uta_parking.model.SearchUserErrorMsgs;
import uta_parking.model.SearchUserManager;
/**
 * Servlet implementation class EditManagerProfileContoller
 */
@WebServlet("/EditManagerProfileContoller")
public class EditManagerProfileContoller extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EditManagerProfileContoller() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();

		String action = request.getParameter("action");
		SearchManagerErrorMsgs searchManagerErrorMsgs = new SearchManagerErrorMsgs();
		String url="";

		if(action !=null ) {
			if(action.equalsIgnoreCase("updateManagerProfile")) {
				//update the manager profile 
				System.out.println("updating manager profile");
				url = "/UTAParking/managerHome.jsp";
				String utaID 		= request.getParameter("utaID");
				String phone 		= request.getParameter("phone");
				String userEmail 	= request.getParameter("userEmail");
				String password 	= request.getParameter("password");
				String userName 	= session.getAttribute("uname").toString();
				//validate user
				SearchManager editedUser = new SearchManager(userName,phone,utaID,userEmail,password);

				searchManagerErrorMsgs = editedUser.validateUser(editedUser,searchManagerErrorMsgs);

				if(!searchManagerErrorMsgs.getError().equals("")) {
					session.setAttribute("searchErrors", searchManagerErrorMsgs);
					session.setAttribute("passwordError", searchManagerErrorMsgs.getPasswordError());
					url = "/editManagerProfile.jsp";		
				}
				else {
					session.removeAttribute("searchErrors");
					//UserDAO.UpdateUserDetails(editedUser);
					UserDAO.updateManagerProfile(userName, utaID, phone, userEmail, password);
					url = "/managerHome.jsp";
				}

				//			call DAO update
				//			UserDAO.updateManagerProfile(userName,utaID,phone,userEmail,password);

			}
		}else {
			ArrayList<SearchUserManager> userList = new ArrayList<>();
			userList = UserDAO.listParkingUserDetails(session.getAttribute("uname").toString());
			
			System.out.println("uname: " + session.getAttribute("uname"));
			
			url = "/editManagerProfile.jsp";
			SearchUserManager user = userList.get(0);
			String userName 	= user.getUser_name();
			String role 		= user.getRole();
			String utaID 		= user.getUta_id();
			String phone 		= user.getPhone();
			String userEmail 	= user.getEmail();
			String license 		= user.getLicense_no();
			String userStatus 	= user.getStatus();
			String reason 		= user.getReason();
			String password 	= (String) session.getAttribute("password");

			System.out.println(password);
			SearchUserAdmin editedUser = new SearchUserAdmin(userName,
					license,phone,utaID,role,
					userEmail,userStatus,reason);
			session.setAttribute("managerAccount", userList);
			session.setAttribute("password", password);
		}
		System.out.println(url);
		getServletContext().getRequestDispatcher(url).forward(request, response);	

	}

}
