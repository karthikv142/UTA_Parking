package uta_parking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.UserDAO;
import uta_parking.model.SearchUserAdmin;
import uta_parking.model.SearchUserErrorMsgs;

/**
 * Servlet implementation class EditUserAdminController
 */
@WebServlet(description = "Edit user details by Admin", urlPatterns = { "/EditUserAdminController" })
public class EditUserAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditUserAdminController() {
        super();
        // TODO Auto-generated constructor stub
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
		SearchUserErrorMsgs searchUserErrorMsgs = new SearchUserErrorMsgs();
		String url="";
		
		String userName 	= request.getParameter("userName");
		String role 		= request.getParameter("role");
		String utaID 		= request.getParameter("utaID");
		String phone 		= request.getParameter("phone");
		String userEmail 	= request.getParameter("userEmail");
		String license 		= request.getParameter("license");
		String userStatus 	= request.getParameter("status");
		String reason 		= request.getParameter("reason");
		
		SearchUserAdmin editedUser = new SearchUserAdmin(userName,
														license,phone,utaID,role,
														userEmail,userStatus,reason);
		
		searchUserErrorMsgs = editedUser.validateUser(editedUser,searchUserErrorMsgs);
		
		System.out.println(searchUserErrorMsgs.getLicenseError());
		System.out.println(searchUserErrorMsgs.getError());
		if(!searchUserErrorMsgs.getError().equals("")) {
			session.setAttribute("searchErrors", searchUserErrorMsgs);
			System.out.println(searchUserErrorMsgs.getLicenseError());
			url = "/editUserAdmin.jsp";		
			}
		else {
//			session.removeAttribute("searchErrors");
			UserDAO.UpdateUserDetails(editedUser);
			url = "/searchUserAdmin.jsp";
		}
		
		getServletContext().getRequestDispatcher(url).forward(request, response);	
		
	}

}
