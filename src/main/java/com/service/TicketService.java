package com.service;

import java.util.List;

import com.bean.Flight;
import com.bean.FlightLine;
import com.bean.Passenger;

public interface TicketService {
	public void saveOrUpdate(List<Passenger>passengers, List<Flight>line, long memberId, FlightLine fl ,String carbinLevel);
	public void returnTicket(long memberId, long lineId);
	
}
