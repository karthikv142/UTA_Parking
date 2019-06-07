package uta_parking.controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import uta_parking.data.UserDAO;
import uta_parking.model.*;

@WebServlet("/ViewMyReservationStatusController")
public class ViewMyReservationStatusController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		
		String  url="";
		HttpSession session = request.getSession();
		
		// Get informations about the current logged in user
		CurrentUser CurrentUser = (CurrentUser)session.getAttribute("currentUser");
		
		String user_name = CurrentUser.getCurrent_username();
		
		ZoneId z = ZoneId.of( "US/Central" );
		LocalDate today = LocalDate.now(z); 		
		
		session.setAttribute("today", today);

		SearchUserManager user = UserDAO.listParkingUserDetails(user_name).get(0);
		
		session.setAttribute("user", user);
		
		url = "/viewReservationStatusUser.jsp";
	
			

		getServletContext().getRequestDispatcher(url).forward(request, response);	

	
	}
	}
