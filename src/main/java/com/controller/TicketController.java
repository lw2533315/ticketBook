package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Flight;
import com.bean.FlightLine;
import com.bean.Member;
import com.bean.Passenger;
import com.bean.PaymentCard;
import com.service.FlightLineService;
import com.service.FlightService;
import com.service.MemberService;
import com.service.PassengerService;
import com.service.TicketService;

@Controller
public class TicketController {
	
	@Autowired
	MemberService ms;
	
	@Autowired
	PassengerService psgService;
	
	@Autowired
	TicketService tctService;
	
	@Autowired
	FlightService fltService;
	
	@Autowired
	FlightLineService fLineService;
	
	
	
	/*purpse:  create a list of passenger by the info form ticketconfirm 
	 * 		   store pasengers info and childseat price, adultseat price, childseat number, and adultseat number to session
	 *         for confirmation page 
	 *param: adult passengers number, adult seat price, child passengers number, child seat price, and all passenger data
	 *		for passenger talbe
	 *
	 *from: ticketconfirm.jsp
	 **/
	@RequestMapping(value="payment", method = RequestMethod.POST)
	public ModelAndView checkOut(HttpServletRequest req, HttpServletRequest resp) {
		int adultSize = Integer.parseInt(req.getParameter("getAdultSize"));   //find the new list size;
		int childSize = Integer.parseInt(req.getParameter("getChildSize"));   //find the new list size;
		int size = adultSize + childSize;

		int adultPrice = Integer.parseInt(req.getParameter("adultPrice").substring(1)); //get price cut the "$"
		int childPrice = Integer.parseInt(req.getParameter("childPrice").substring(1)); //get price cut the "$"
		
		int price = (int)(adultSize * adultPrice + childSize * childPrice);
		
		List<Passenger> passengers = new ArrayList<Passenger>(size);
		String [] firstNames = req.getParameterValues("firstName");
		String [] lastNames = req.getParameterValues("lastName");
		String [] emails = req.getParameterValues("email");
		String [] phones = req.getParameterValues("phone");
		String [] genders = req.getParameterValues("gender");
		String [] ages = req.getParameterValues("age");
		
		//save passenger info
		for(int i = 0; i < size; i ++) {
			Passenger temp = new Passenger();
			temp.setAge(Integer.parseInt(ages[i]));
			temp.setEmail(emails[i]);
			temp.setFirstName(firstNames[i]);
			temp.setLastName(lastNames[i]);
			temp.setGender(genders[i]);
			temp.setPhone(phones[i]);
			passengers.add(temp);
		
		}
		HttpSession session = req.getSession(false);
		if(session == null) {
			return new ModelAndView("index");
		}
		if(session.getAttribute("id") == null|| session.getAttribute("name") == null) {
			return new ModelAndView("index");
		}
		
		//save for confirmation page and flight table update
		session.setAttribute("passengers", passengers);
		session.setAttribute("childSize", childSize);
		session.setAttribute("adultSize", adultSize);
		session.setAttribute("childPrice", childPrice);
		session.setAttribute("adultPrice", adultPrice);
		session.setAttribute("totalPrice", price);
		
		
		ModelAndView mav = new ModelAndView("checkout");
		mav.addObject("price", price);
//		return new ModelAndView("checkout");
		return mav;
	}
	
	
	
	/*purpose: 1.after checkout,  create passenger table
	 * 			2. ticket table 
	 * 			3. flight line table    use to help in account.jsp delete flight, since the flight price 
	 * 				are different when order round trip or one trip
	 * 			4. update flight table
	 *          5 all tables with ticket are one to many
	 * 		 ticket-flight   many-one,    ticket-passenger    many-one
	 *         6. depend on user choose to update payment card infor or not 
	 *return : jump to confirmation jsp
	 *from:  checkout.jsp
	 *
	 * */
	@RequestMapping(value="confirm", method = RequestMethod.POST)
	public ModelAndView ticketConfirm(HttpServletRequest req, HttpServletResponse resp) {
		String updateBankCard = req.getParameter("saveDefault");
		String cardNumber = req.getParameter("cardNumber");
		String cardName = req.getParameter("cardName");
		String expireYear = req.getParameter("year");
		String expireMonth = req.getParameter("month");
		String cvv = req.getParameter("cvv");
		HttpSession session = req.getSession(false);
		
		if(session == null)
			return new ModelAndView("index");
		if(session.getAttribute("id") == null || session.getAttribute("name") == null)
			return new ModelAndView("index");
		long id =(Long) session.getAttribute("id");
		
		//update paymentcard info in member table
		if(updateBankCard != null && updateBankCard.equals("on")) {
			PaymentCard pCard = new PaymentCard();
			pCard.setNameOnTheCard(cardName);
			pCard.setCardNumber(cardNumber);
			pCard.setCvv(Integer.parseInt(cvv));
			pCard.setExpireMonth(Integer.parseInt(expireMonth));
			pCard.setExpireYear(Integer.parseInt(expireYear));
			ms.updateBankCard(id, pCard);
		}
		
		//update passenger table since the ticket has been bought
		List<Passenger> passengers = (List<Passenger>)session.getAttribute("passengers");
		psgService.saveOrUpdate(passengers);
		
		//update flightLine table 
		int price = (Integer)session.getAttribute("totalPrice");
		List<Flight> line = (List<Flight>)session.getAttribute("line");
		int flightChanged = line.size();
		String  roundTrip = (String)session.getAttribute("roundTrip");
	    FlightLine fLine =	fLineService.getNewFlightLine(price, flightChanged, Integer.parseInt(roundTrip) );
		
	    
		
		//update ticket table since the ticket has been bought
		long memberId = (Long)session.getAttribute("id");
		String carbinLevel = (String)session.getAttribute("carbinLevel");
		tctService.saveOrUpdate(passengers, line, id, fLine, carbinLevel);
		
		
		//update flight table 
		int childNumber = (Integer)session.getAttribute("childSize");
		int adultNumber = (Integer)session.getAttribute("adultSize");
		fltService.updateSeatById(childNumber, adultNumber, carbinLevel,line );
		
		
		
		return new ModelAndView("confirmation");
	}
	
	
	/*purpose: return ticket get refund
	 * 		   1. update flight seat info
	 *         2. deltet tickets from ticket table
	 * from: account.jsp delte ticket
	 * */
	@RequestMapping(value="returnTicket", method=RequestMethod.GET)
	public void returnTicket(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession(false);
		long memberId = (Long)session.getAttribute("id");   //member table id
		long lineId = Long.parseLong(req.getParameter("id"));  //line table id;
		long price = fLineService.getFlightLineById(lineId).getPrice(); 
		System.out.println("line id " + lineId);
		tctService.returnTicket(memberId, lineId);
		System.out.println("ticketc  rturnticket  test2");
		//find cardNumber for account   lazy initialize cannot get it in jsp directorly
		try {
			resp.getWriter().write(price+"");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
