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

@WebServlet("/UpdateProfileController")
public class UpdateProfileController extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String url="";
		HttpSession session = request.getSession();
		
		CurrentUser CurrentUser = (CurrentUser)session.getAttribute("currentUser");
		
		String username = CurrentUser.getCurrent_username();
		
		SearchUserManager user = UserDAO.listParkingUserDetails(username).get(0);
		
		session.setAttribute("user", user);

		String modifyError ="";
		
		String fieldToModify = "";
		
		url = "/updateProfile.jsp";

		
			if (request.getParameter("updateProfileButton")!=null) { 

			
				fieldToModify = request.getParameter("fieldToModify");
				if (fieldToModify.equals("password"))
					{modifyError = User.validatePassword(request.getParameter("fieldToModifyValue"));}
				
				else if (fieldToModify.equals("phone"))
				{modifyError = User.validatePhone(request.getParameter("fieldToModifyValue"));}
				
				else if (fieldToModify.equals("email"))
				{modifyError = User.validateEmail(request.getParameter("fieldToModifyValue"));}
				
				else if (fieldToModify.equals("license_no"))
				{modifyError = User.validateLicense_plate(request.getParameter("fieldToModifyValue"), CurrentUser.getCurrent_role());}
	
				//Check that there are no error messages before inserting into database
				if (modifyError.equals("")) {
					
					//update
					UserDAO.updateProfileField(fieldToModify, request.getParameter("fieldToModifyValue"), username);

					session.removeAttribute("modifyError");
					url = "/updateProfile.jsp";
					
				}
				
				else {
					
				session.setAttribute("modifyError", modifyError);
				url = "/updateProfile.jsp"; 
				}
			}
			
	
		
		
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
}
