package com.dao;

import java.util.List;

import com.bean.FlightLine;
import com.bean.Ticket;

public interface TicketDao {
	public void saveOrUpdate(List<Ticket> tickets);
	public void deleteLineTickets(FlightLine fl);
	

}
