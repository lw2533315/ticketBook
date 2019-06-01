package com.dao;

import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.FlightLine;
import com.bean.Ticket;

@Component
@Scope("prototype")
//@Transactional
public class TicketDaoImply implements TicketDao{
	@Autowired
	SessionFactory sessionFactory;
	
	
	public void saveOrUpdate(List<Ticket> tickets) {
		System.out.println("tickets number is " + tickets.size());
		Session session = sessionFactory.getCurrentSession();
		for(Ticket ticket: tickets) {
			 
			 session.save(ticket);
			 
		}
//		session.close();
		
	}

	/*delete tickets in one purchase
	 from:  accountinfo.jsp->ticketcontorller->ticket service*/
	public void deleteLineTickets(FlightLine fl) {
		for(Ticket ticket: fl.getTickets()) {
			sessionFactory.getCurrentSession().delete(ticket);
		}
		
	}

}
