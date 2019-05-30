package com.service;

import com.bean.FlightLine;

public interface FlightLineService {
	public FlightLine getNewFlightLine(int price, int flightChanged, int roundTrip);
	public FlightLine getFlightLineById(long lineId);
	

}
