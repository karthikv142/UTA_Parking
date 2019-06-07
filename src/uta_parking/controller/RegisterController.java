package uta_parking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uta_parking.data.UserDAO;
import uta_parking.model.*;

@WebServlet("/RegisterController")
public class RegisterController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action=request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		
		//insert new user
		if (action.equals("register")) {
			if (request.getParameter("registerButton")!=null) {  //register user button pressed
				
				User user = new User();
				user.setUser(request.getParameter("username"), 
								request.getParameter("utaID"), 
								request.getParameter("role"),
								request.getParameter("password"), 
								request.getParameter("phone"),
								request.getParameter("email"),
								request.getParameter("license_plate"));
				
				
				/* For debugging
				System.out.println("When getting infos from form");
				System.out.println("Username : "+ request.getParameter("username"));
				System.out.println("UTAID: " + request.getParameter("utaID"));
				System.out.println("Role : "+ request.getParameter("role"));
				System.out.println("password: "+request.getParameter("password"));
				System.out.println("Phone: "+ request.getParameter("phone"));
				System.out.println("Email: "+request.getParameter("email"));
				System.out.println("License plate: "+request.getParameter("license_plate"));
				
				
				*/
				
				UserErrorMsgs errorMsgs = new UserErrorMsgs();
				
				user.validateUser(user, errorMsgs);
				//user.setFk_company(company.getIdcompany());
				
				//The user.X inside <cout>
				session.setAttribute("user", user);
				session.setAttribute("errorMsgs",errorMsgs);
				
				//Check that there are no error messages before inserting into database
				if (errorMsgs.getErrorMsg().equals("")) {
					UserDAO.insertUser(user); //save user if no errors
					
					session.removeAttribute("user");
					session.removeAttribute("errorMsgs");

					
					url = "/registerForm.jsp";
					
				}
				
				else {
				url = "/registerForm.jsp"; 
				}
			}
			else { // reset button pressed
				
				
				/*
				session.removeAttribute("company");
				session.removeAttribute("employee");
				session.removeAttribute("COMPANIES");
				session.removeAttribute("errorMsgs");
				url="/index.jsp";
				
				*/
			}
		}	
		
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}
