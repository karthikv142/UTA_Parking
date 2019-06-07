package uta_parking.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ParkingDAO;
import uta_parking.data.ReservationDAO;
import uta_parking.data.UserDAO;
import uta_parking.model.Reservation;
import uta_parking.model.ReservationErrorMsgs;

import uta_parking.model.*;

@WebServlet("/RequestReservationController")
public class RequestReservationController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		

		// Get informations about the current logged in user
		CurrentUser CurrentUser = (CurrentUser)session.getAttribute("currentUser");
		
		//For debugging : 
		/*
		System.out.println("Current user role" +CurrentUser.getCurrent_role());
		System.out.println("Current user UTA ID" +CurrentUser.getCurrent_utaID());
		System.out.println("Current user username" +CurrentUser.getCurrent_username());
		*/

		//List available spots using user filters
		if (action.equalsIgnoreCase("requestReservation") ) {
			if (request.getParameter("requestReservationButton")!=null) {
				Reservation reservation = new Reservation();
				
				ReservationErrorMsgs errorMsgs = new ReservationErrorMsgs();
				
				ArrayList<Parking> availableSpotsInDB = new ArrayList<Parking>();
				
				String user_name = CurrentUser.getCurrent_username();
				String parking_type = request.getParameter("parking_type");   
				String start_time = request.getParameter("start_time");
				String duration = request.getParameter("duration");
				String options[] = request.getParameterValues("checkedOptions");
				String camera_option ="0";
				String cart_option ="0";
				String history_option ="0";
				
				if (options != null)
					//Get the values from checkboxes
					for(int i=0; i<options.length; i++)
						{
							if (options[i].equals("camera"))
								camera_option="1";
							else if (options[i].equals("cart"))
								cart_option="1";
							else if (options[i].equals("history"))
								history_option="1";
						}

				/*
				//For debugging
				System.out.println("Request reservation fields from jsp: ");
				System.out.println(parking_type);
				System.out.println(start_time);
				System.out.println(duration);
				*/
				
				//Set the values for the reservation objects to values from jsp form
				reservation.setParking_type(parking_type);
				reservation.setStart_time(start_time);
				reservation.setDuration(duration);
				reservation.setCamera_option(camera_option);
				reservation.setCart_option(cart_option);
				reservation.setHistory_option(history_option);
				reservation.setUser_name(user_name);
				
				//GET LOCAL DATE
					
				ZoneId z = ZoneId.of( "US/Central" );
				ZonedDateTime zdt = ZonedDateTime.now( z );
				String getCurrentTime[] = zdt.toString().split("T",2);		
				//LocalDate today = LocalDate.now(z);  		
				String getCurrentHours[] = getCurrentTime[1].split(":",2);
		
				String getCurrentMinutes[] = getCurrentHours[1].split(":",2);
		
				
		
				 int currentHours = Integer.parseInt(getCurrentHours[0]);	
				 int currentMinutes = Integer.parseInt(getCurrentMinutes[0]);
				

				reservation.validateReservation(reservation,errorMsgs,CurrentUser.getCurrent_utaID(), currentHours, currentMinutes);
				
				//Debugging
				//System.out.println("Current username" + CurrentUser.getCurrent_username());
				//System.out.println("Current user id :" + CurrentUser.getCurrent_user_id());
				
				session.setAttribute("errorMsgs",errorMsgs);
				session.setAttribute("requestreservation", reservation);
				
				//System.out.println("errorMsgs: "+ errorMsgs.getErrorMsg());
			
			if (errorMsgs.getErrorMsg().equals("")) {
				
			
				Options optionsObject = new Options();
				
					
								ZoneId zone = ZoneId.of( "US/Central" );
				
								LocalDate today = LocalDate.now(zone); 		
				
								String dayName = today.getDayOfWeek().name();
				
				reservation.setCost(String.valueOf(optionsObject.calculateTotalCost(dayName,start_time, duration, camera_option,cart_option,history_option)));
				availableSpotsInDB=ParkingDAO.listAvailableSpots(parking_type);
				
				
			
			//but first check if user is active
				if (UserDAO.isActiveUser(CurrentUser.getCurrent_username()))
					{
						//System.out.println("reservation cost: "+ reservation.getCost());
						session.setAttribute("parking",availableSpotsInDB);
						session.removeAttribute("errorMsgs");
						
						url = "/listAvailableSpots.jsp";
					}	
				else
				{
					String sRevokedError = "It looks like you are not allowed to make reservations at the moment.";
					session.setAttribute("revokedError", sRevokedError);
					
					
					url = "/revokedUserPage.jsp";
							
					}	
			}
			
			else
				{
					session.setAttribute("errorMsgs",errorMsgs);
					session.setAttribute("requestreservation", reservation);
					url = "/requestReservationForm.jsp";
				}
			
			
		}

			// If confirm reservation button is clicked
			if (request.getParameter("confirmReservationButton")!=null) {
					if (request.getParameter("radioReservation")!= null) {
						
						Reservation confirmReservation = (Reservation)request.getSession().getAttribute("requestreservation");
						@SuppressWarnings("unchecked")
						ArrayList<Parking> availableSpotsInDB =(ArrayList<Parking>)request.getSession().getAttribute("parking");
						
						ZoneId z = ZoneId.of( "US/Central" );
						LocalDate today = LocalDate.now(z); 		
						String dayName = today.getDayOfWeek().name();
						confirmReservation.setDate(dayName + " " + today);
						
						//System.out.println("Date :" +dayName + " " + today);
						// add case where nothing is selected
						
						int selectedSpot = Integer.parseInt(request.getParameter("radioReservation")) - 1;
						confirmReservation.setParking_area_name(availableSpotsInDB.get(selectedSpot).getParking_area_name());
						confirmReservation.setParking_type(availableSpotsInDB.get(selectedSpot).getParking_type());
						confirmReservation.setFloor(availableSpotsInDB.get(selectedSpot).getFloor());
						
						
						
						/***** Validate max number of reservations and overlapping reservations ******/
						ReservationErrorMsgs confirmErrorMsg = new ReservationErrorMsgs();
						session.setAttribute("confirmErrorMsg",confirmErrorMsg);
						
						
						confirmReservation.validateConfirmedReservation (confirmReservation,confirmErrorMsg,CurrentUser);						
						
						if (!confirmErrorMsg.getOverlapError().equals("") || !confirmErrorMsg.getMaxForTheDayError().equals("") )
							{
							
							session.setAttribute("confirmReservation", confirmReservation);
							session.setAttribute("confirmErrorMsg",confirmErrorMsg);
							
							url = "/listAvailableSpots.jsp";
							}
						
						else 
						{
							//Check for availability and Assign spot number 
							confirmReservation.setSpot_number(assignSpotNumber(confirmReservation.getParking_area_name(),
									confirmReservation.getFloor()));
							
							session.setAttribute("confirmReservation", confirmReservation);
							
							url = "/paymentForm.jsp";
						}
					}
						
				}
		
		
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}

	//function to assign spot number
	public static String assignSpotNumber(String parking_area_name, String floor)
	{
		int assignedSpotNumber = 0 ;
		int capacity = Integer.parseInt(ParkingDAO.checkParkingCapacity(parking_area_name, floor));
		int availability = Integer.parseInt(ParkingDAO.checkParkingAvailability(parking_area_name, floor));
		
		int difference = capacity - availability; 
		
		
			assignedSpotNumber = difference + 1;
		
	
	return String.valueOf(assignedSpotNumber);	
	}
	
	}
