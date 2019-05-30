package com.dao;

import java.util.List;

import com.bean.Flight;
import com.bean.FlightLine;

public interface FlightDao {
	public List<Flight> getAllFlights();
	public void saveOrUpdate(Flight flight);
	public List<String> getCityName();
	public List<Flight> getFlightsByIds(long [] ary);
	public void updateTicketByIds(int seatsNumber, String level, List<Long> serialId);
	public void updateFlightsByTickets(FlightLine fl);
	public void removeReturnedTicket(long lineId);

}
