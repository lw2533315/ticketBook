package com.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.Flight;
import com.bean.FlightLine;
import com.bean.Member;
import com.bean.Passenger;
import com.bean.Ticket;
import com.dao.FlightDao;
import com.dao.FlightLineDao;
import com.dao.MemberDao;
import com.dao.PassengerDao;
import com.dao.TicketDao;

@Component
@Scope("prototype")
@Transactional
public class TicketServiceImply implements TicketService{
	@Autowired
	TicketDao ticketDao;
	
	@Autowired
	MemberService mService;
	
	@Autowired
	FlightLineService flService;
	
	@Autowired
	MemberDao memberDao;
	
	@Autowired
	FlightDao  flDao;
	
	@Autowired
	FlightLineDao  fLineDao;
	
	@Autowired
	PassengerDao  psgDao;
	
	/*purpse:  1.call dao saveOrUpdate method update ticket table (set bimapping) 
	 *         dao.saveorupdate need ticket list so here we convert list of passenger and list
	 *         of  flights to list of tickets
	 *         2.cal the tickets number and carbin info to update flight info
	 *         
	 *from: checkout.jsp->ticketController->here
	 * */
	public void saveOrUpdate(List<Passenger>passengers, List<Flight>line , long memberId, FlightLine flightLine, String carbinLevel){
		//find the memberId's obj
		Member member = mService.getMemberById(memberId);
		
			
		
		//amend data for ticket table 
		List<Ticket> tickets = new ArrayList<Ticket>();
		
		for(Flight flight: line) {
			for(Passenger passenger: passengers) {
				Ticket ticket = new Ticket();
				//flight was delete , ticket will delete
				ticket.setFlight(flight);
				ticket.getFlight().getTickets().add(ticket);
				
				
				ticket.setPassenger(passenger);
				ticket.getPassenger().getTickets().add(ticket);
				
				ticket.setMember(member);
				ticket.getMember().getTickets().add(ticket);
				
				
//				flight line delete, ticket will delete
				ticket.setFlightLine(flightLine);
				ticket.getFlightLine().getTickets().add(ticket);
				
				ticket.setCarbinLevel(carbinLevel);
				tickets.add(ticket);
//				psgDao.saveOrUpdateOne(passenger);
			}
//			flDao.saveOrUpdate(flight);
		}
		
//		fLineDao.saveOrUpdate(flightLine);
//		System.out.println("~~~~~~~~~~~~~~sizeof flightLIne is " + flightLine.getTickets().size());
//		memberDao.saveOrUpdate(member);
		ticketDao.saveOrUpdate(tickets);
		
		
		
		
		 
		
	}

	
	/*purpose  1. find tickets number
	 *         2. ticket carbin level
	 *         3. call flightline delete it will delete tickets together
	 *         4. call flight update
	 * from:  accountInfo.jsp ask refund tickets;
	 * */
	public void returnTicket(long memberId, long lineId) {
		System.out.println("in ticket service");
		//get FlightLine of the line id
		FlightLine fl = flService.getFlightLineById(lineId);
		System.out.println("in ticket service 1  "   + fl.getLineId());
//		long ticketid = fl.getTickets().get(0).getTicketId();
		int ticketNumber = fl.getTickets().size();  //get tickets number
		System.out.println("in ticket service " + ticketNumber);
		
		
		flDao.removeReturnedTicket(fl.getLineId());
		
		//delete tickets from delete flightline table
		fLineDao.deleteFlightLineByObj(fl);
		
		//update flight table
		flDao.updateFlightsByTickets(fl);
		
	}
	

}
