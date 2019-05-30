package com.dao;

import com.bean.FlightLine;

public interface FlightLineDao {
	public FlightLine getNewFlightLine(int price, int flightChanged, int roundTrip);
	public FlightLine getFlightLineById(long lineId);
	public void deleteFlightLineByObj(FlightLine obj);
	public void saveOrUpdate(FlightLine fl);

}
