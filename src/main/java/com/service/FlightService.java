package com.service;

import java.util.List;
import java.util.Map;

import com.bean.Flight;

public interface FlightService {
	public List<Map.Entry<List<Flight>, Integer>> getAllFlight(String dptCity, String arvCity, String dptTime, String rtnTime);
	public List<String> getCityName();
	public List<Map.Entry<List<Flight>, Integer>> filterBooking(String flightFrom, String flightTo, String dptTime, String rtnTime, String airline, String 
			stops, String carbinLevel);
	public List<List<Flight>> filterBookingForAjax(String dptCity, String toCity, String dptTime,
			String rtnTime, String airline, String stops, String carbinLevel);
	public List<Flight> getFlightsByIds(long [] ary); 
	public void updateSeatById(int childNo, int adultNo, String level, List<Flight>line );

}
