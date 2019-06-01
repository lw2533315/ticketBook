package com.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.Flight;
import com.bean.FlightLine;
import com.bean.Ticket;

@Component
@Scope("prototype")
//@Transactional
public class FlightDaoImply implements FlightDao {
	@Autowired
	SessionFactory sessionFactory;
	
	/*purpse: fight all flight info
	 * param: none
	 * return: flights list*/
	public List<Flight> getAllFlights() {
			   return sessionFactory.getCurrentSession().createCriteria(Flight.class).list();
	}
	
	
	
	/*purpose: create Flight table
	 * param: one Flight info*/
	public void saveOrUpdate(Flight flight) {
		sessionFactory.getCurrentSession().saveOrUpdate(flight);
		
	}


	/*purpose: find all cities connected by the flights*/
	public List<String> getCityName() {
		Query query = sessionFactory.getCurrentSession().createSQLQuery("select arriveCity from Flight group by arriveCity");
		List<String> ret = query.list();
		
		return ret;
	}


	/*find flights info by serialids*/
	public List<Flight> getFlightsByIds(long[] ary) {
		List<Flight> ret = new ArrayList<Flight>();
		for(long l: ary) {
			Flight flight = sessionFactory.getCurrentSession().get(Flight.class, l);
			ret.add(flight);
		}		
		return ret;
	}


	
	/*after sale ticket ,update seat info*/
	public void updateTicketByIds(int seatsNumber, String level, List<Long>serialIds) {
		Session session = sessionFactory.getCurrentSession();
		for(Long id: serialIds) {
			Flight temp = session.get(Flight.class, id);
			//update ecoseat, reservedecoseat, reservedseat
			if(level.equalsIgnoreCase("economy")) {
				temp.setEcoSeats(temp.getEcoSeats() - seatsNumber);
				temp.setReservedEcoSeats(temp.getReservedEcoSeats() + seatsNumber);
				temp.setReservedSeats(temp.getReservedSeats() + seatsNumber);
			}else {
				temp.setBusSeats(temp.getBusSeats() - seatsNumber);
				temp.setReservedBusSeats(temp.getReservedBusSeats() + seatsNumber);
				temp.setReservedSeats(temp.getReservedSeats() + seatsNumber);
			}
			session.saveOrUpdate(temp);
		}
		
	}



	/*purpose:  update seats by tickets return , the available seat increase
	 * 
	 * from: accountinfo.jsp -> ticketService -> flightdao*/
	public void updateFlightsByTickets(FlightLine fl) {
		System.out.println("dlete flight function");
		int seatsNumber = fl.getTickets().size();
		System.out.println("seat number " + seatsNumber);
		Session session = sessionFactory.getCurrentSession();
		for(Ticket ticket:fl.getTickets()) {
			System.out.println("ticket number " + ticket.getTicketId());
			String carbin = ticket.getCarbinLevel();
			long serialId = ticket.getFlight().getSerialId();
			Flight temp = session.get(Flight.class, serialId);
			if(carbin.equalsIgnoreCase("economy")) {
				temp.setEcoSeats(temp.getEcoSeats() + seatsNumber);
				temp.setReservedEcoSeats(temp.getReservedEcoSeats() - seatsNumber);
				temp.setReservedSeats(temp.getReservedSeats() - seatsNumber);
			}else {
				temp.setBusSeats(temp.getBusSeats() + seatsNumber);
				temp.setReservedBusSeats(temp.getReservedBusSeats() - seatsNumber);
				temp.setReservedSeats(temp.getReservedSeats() - seatsNumber);
			}
			session.saveOrUpdate(temp);
		}
	}


	//flight side set cascadetype.all, when delete ticket from other reference, need remove ticket
	//from flight.ticketlist first, otherwise will re save it back to ticket table 
	public void removeReturnedTicket(long lineId) {
		Session session = sessionFactory.getCurrentSession();
		FlightLine fl = session.get(FlightLine.class, lineId);
		System.out.println("fldao removereturned: " + fl.getTickets().get(0));
		for(Ticket ticket: fl.getTickets()) {
			Flight flight = session.get(Flight.class, ticket.getFlight().getSerialId());//find the flight
			flight.getTickets().remove(ticket); //remove from tiket list
			
		}
		
	}
	
}
