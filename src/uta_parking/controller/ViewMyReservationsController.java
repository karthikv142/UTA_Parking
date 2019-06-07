package uta_parking.controller;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import uta_parking.data.ReservationDAO;
import uta_parking.model.*;

@WebServlet("/ViewMyReservationsController")
public class ViewMyReservationsController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		
		String url="";
		HttpSession session = request.getSession();
		
		// Get informations about the current logged in user
		CurrentUser CurrentUser = (CurrentUser)session.getAttribute("currentUser");
		
		//For debugging : 
		/*
		System.out.println("Current user role" +CurrentUser.getCurrent_role());
		System.out.println("Current user UTA ID" +CurrentUser.getCurrent_utaID());
		System.out.println("Current user username" +CurrentUser.getCurrent_username());
		*/

		/*	List active reservations made by user for the day by username
		 * Order by ascending start time
		 * 	Get selected reservation' ID
		 * 	Cancel it
		 * 
		 * 
		 * 
		 * 
		 * 
		 */
		
		ArrayList<Reservation> userActiveReservations = new ArrayList<Reservation>();
				
		String user_name = CurrentUser.getCurrent_username();
		
		String emptyList ="";
		
		int selectedReservation =0;
		
		String selectedReservationID="";
		session.removeAttribute("emptyList");
		
		ZoneId z = ZoneId.of( "US/Central" );
		LocalDate today = LocalDate.now(z); 		
		
		session.setAttribute("today", today);
		
		userActiveReservations = ReservationDAO.listUserSpots(user_name);
		
		if (userActiveReservations.isEmpty())
			{emptyList= "No active reservations were found for today.";
			
			session.removeAttribute("userReservation");
			session.setAttribute("emptyList", emptyList);
			url = "/viewMyReservedSpots.jsp";}

		else
			{session.setAttribute("userReservation", userActiveReservations);
			
			url = "/viewMyReservedSpots.jsp";
			
			if (request.getParameter("cancelReservationButton")!=null) 
			{if (request.getParameter("radioReservation")!= null)
					// add button action
			{
					selectedReservation = Integer.parseInt(request.getParameter("radioReservation")) - 1;
					selectedReservationID = userActiveReservations.get(selectedReservation).getReservationID();
					
					System.out.println("You selected reservation : "+ selectedReservationID);
					
					// cancel
						ReservationDAO.updateReservationStatus(selectedReservationID, "0");
					
					//display the new reservation list
					url = "/userHome.jsp";
			}	
			
			else 
			{emptyList= "To cancel a reservation. Please select one from the list.";
			session.setAttribute("emptyList", emptyList);
			url = "/viewMyReservedSpots.jsp";
			
			}
					
			}
			
			if (request.getParameter("modifyReservationButton")!=null) 
			{if (request.getParameter("radioReservation")!= null)
					// add button action
			{
					selectedReservation = Integer.parseInt(request.getParameter("radioReservation")) - 1;
					selectedReservationID = userActiveReservations.get(selectedReservation).getReservationID();
					
					System.out.println("You selected reservation : "+ selectedReservationID);
					session.setAttribute("reservationID_to_modify", selectedReservationID);
					

					{ReservationDAO.updateReservationStatus(userActiveReservations.get(selectedReservation).getReservationID(), "0");}
					
					session.setAttribute("requestreservation", userActiveReservations.get(selectedReservation));
					//display the new reservation list
					url = "/requestReservationForm.jsp";
			}	
			
			else 
			{emptyList= "To modify a reservation. Please select one from the list.";
			session.setAttribute("emptyList", emptyList);
			url = "/viewMyReservedSpots.jsp";
			
			}
					
			}
			
			
			
			}

		getServletContext().getRequestDispatcher(url).forward(request, response);	

	
	}
	}
