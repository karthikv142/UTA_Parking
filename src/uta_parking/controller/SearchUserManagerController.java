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
import uta_parking.model.*;

@WebServlet("/SearchUserManagerController")
public class SearchUserManagerController extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		List the User and user details present in DB
		
		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(action.equalsIgnoreCase("searchUserManager")) {
			if (request.getParameter("searchUserManagerButton")!=null) {
				
					SearchUserManager searchusermanager = new SearchUserManager();
					SearchUserErrorMsgs SearchErrorMsg = new SearchUserErrorMsgs();
					
					ArrayList<SearchUserManager> parkinguserInDB = new ArrayList<SearchUserManager>();
					

					session.setAttribute("SearchUserManager",  searchusermanager);
					
					String parkingUsername = request.getParameter("username");	
					
					
					
					searchusermanager.setUser_name(request.getParameter("username"));
					
					SearchErrorMsg.setSearch_usernameError(searchusermanager.validateSearchUser(parkingUsername));
					
					System.out.println("Error msg is: "+ SearchErrorMsg.getSearch_usernameError());
		
					if (SearchErrorMsg.getSearch_usernameError().equals("")){
						
						
						parkinguserInDB = UserDAO.listParkingUserDetails(parkingUsername);
						session.setAttribute("ParkingUsers", parkinguserInDB);
						
						session.removeAttribute("searchErrorMsg");
						session.removeAttribute("SearchUserManager");
						
						System.out.println("Before url list:");
						url = "/listParkingUsersManager.jsp";
						System.out.println("if block");
					}
					
					else
					{
						session.setAttribute("searchErrorMsg",SearchErrorMsg);
						session.setAttribute("SearchUserManager",  searchusermanager);
						
						
						System.out.println("Before url search:");
						
						url = "/searchUserManager.jsp";
						System.out.println("else block");
					}
									
					
						
						
					
					
			}	
					}
		
			System.out.println("URL :" + url);
					
			getServletContext().getRequestDispatcher(url).forward(request, response);	
	}
}