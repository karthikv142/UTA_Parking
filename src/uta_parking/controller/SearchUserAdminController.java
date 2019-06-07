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

@WebServlet("/SearchUserAdminController")
public class SearchUserAdminController extends HttpServlet{
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		SearchUserAdmin searchUserAdmin = new SearchUserAdmin();
		SearchUserErrorMsgs SearchErrorMsg = new SearchUserErrorMsgs();
		
		searchUserAdmin.setUserName(request.getParameter("userName"));
		session.setAttribute("nameForm",searchUserAdmin);
		ArrayList<SearchUserAdmin> parkinguserInDB = new ArrayList<SearchUserAdmin>();
		
		String parkingUsername = request.getParameter("username");
		String url="";
		
		System.out.println(parkingUsername);
//		SearchErrorMsg.setSearch_usernameError(searchUserAdmin.validateSearchUser(parkingUsername));
		
//		if (SearchErrorMsg.getSearch_usernameError().equals("")){
			
			parkinguserInDB = UserDAO.parkingUserDetails(parkingUsername);
			
			if(parkinguserInDB.size()>0) {
				System.out.println("not empty");
			}
			session.setAttribute("ParkingUsers", parkinguserInDB);
			
//			session.removeAttribute("searchErrorMsg");
//			session.removeAttribute("SearchUserManager");
			
//			System.out.println("Before url list:");
			url = "/listParkingUsersAdmin.jsp";
			
//		}
		
//		else
//		{
			session.setAttribute("searchErrorMsg",SearchErrorMsg);
			session.setAttribute("searchUserAdmin",parkingUsername );
			
			
			System.out.println("Before url search:");
			
			url = "/searchUserAdmin.jsp";
//		}
		url = "/listParkingUsersAdmin.jsp";
		getServletContext().getRequestDispatcher(url).forward(request, response);
	}
}