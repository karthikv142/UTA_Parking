package uta_parking.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import uta_parking.data.UserDAO;
import uta_parking.model.CurrentUser;
import uta_parking.model.Login;
//import javax.servlet.http.HttpSession;
import uta_parking.model.LoginErrorMsgs;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
 /**
  * @see HttpServlet#HttpServlet()
  */
 public LoginController() {
     super();
     // TODO Auto-generated constructor stub
 }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		HttpSession session = request.getSession();
		String url = "";
		
		if(action.equalsIgnoreCase("login")) {
			if (request.getParameter("loginButton")!=null) {
					
					CurrentUser currentUser = new CurrentUser();
					Login login = new Login();
					LoginErrorMsgs loginErrorMsg = new LoginErrorMsgs();
					
					//set credentials for login 
					login.setUsername(request.getParameter("username"));
					login.setPassword(request.getParameter("password"));
					
					session.setAttribute("login", login);
					session.setAttribute("uname", login.getUsername());

					//Validate credentials
					loginErrorMsg.setLoginError(login.validateLogin(login.getUsername(), login.getPassword()));
					
		
					if (loginErrorMsg.getLoginError().equals("")){
						
						//if login is successful, get user role to redirect to appropriate page
						
						session.setAttribute("currentUser", currentUser);
						
						//Store informations about current logged in user for future use. Username, UTA ID and role.
						currentUser.setCurrent_username(login.getUsername());
						currentUser.setCurrent_role(UserDAO.getLoginRole(login.getUsername()));
						currentUser.setCurrentUtaID(UserDAO.getUTAID(login.getUsername()));
						
						
						session.removeAttribute("login");
						
						System.out.println("Before url list:");
						url = "/"+ currentUser.getCurrent_role() +"Home.jsp";
						
					}
			
					else
					{
						session.setAttribute("loginErrorMsg",loginErrorMsg);
						session.setAttribute("login", login);
						
						//for dubugging
						System.out.println("Before url search:");
						
						url = "/index.jsp";
					}
									
			}
		}
			getServletContext().getRequestDispatcher(url).forward(request, response);	
	}
}