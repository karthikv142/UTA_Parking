package uta_parking.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import uta_parking.data.ParkingDAO;
import uta_parking.data.PaymentDAO;
import uta_parking.data.ReservationDAO;
import uta_parking.model.*;

@WebServlet("/MakePaymentController")
public class MakePaymentController extends HttpServlet {

	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		String action = request.getParameter("action"), url="";
		HttpSession session = request.getSession();
		
		
		
		//List available spots using user filters
		if (action.equalsIgnoreCase("makePayment") ) {
			if (request.getParameter("payButton")!=null) {
				
				Reservation confirmReservation = (Reservation)request.getSession().getAttribute("confirmReservation");
				
				Payment payment = new Payment();
				PaymentErrorMsgs paymentErrorMsg = new PaymentErrorMsgs();

				
				String credit_card_type = request.getParameter("credit_card_type");   
				String credit_card_number = request.getParameter("credit_card_number");
				String amount = confirmReservation.getCost();
				
				//Validate the payment infos if the amount is not $0 
				
				if (!amount.equals("0.0"))
					paymentErrorMsg.setPaymentError(payment.validatePayment(credit_card_number, credit_card_type));

				session.setAttribute("paymentErrorMsg",paymentErrorMsg);
				session.setAttribute("payment", payment);
				
			
				if (paymentErrorMsg.getPaymentError().equals("")) {
					
					//set values in Payment object
					payment.setCredit_card_type(credit_card_type);
					payment.setCredit_card_number(credit_card_number);
					payment.setAmount(amount);
					
					if (!amount.equals("0"))
					//insert payment in database
						{PaymentDAO.insertPayment(payment);}

					//insert new reservation in database
					ReservationDAO.insertReservation(confirmReservation);
					
					//reduce availability of parking area
					ParkingDAO.updateAvailability(confirmReservation.getParking_area_name(), 
										confirmReservation.getFloor(), confirmReservation.getParking_type(), "-", 1);
					
					
					url = "/userHome.jsp";
				
					}				
			
				else
					{
						session.setAttribute("paymentErrorMsg",paymentErrorMsg);
						session.setAttribute("payment", payment);
						url = "/paymentForm.jsp";
					}
			
		

						
				}
		
		
		}
		getServletContext().getRequestDispatcher(url).forward(request, response);		
	}
	
	
	
	}
