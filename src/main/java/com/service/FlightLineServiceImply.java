
package com.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.FlightLine;
import com.dao.FlightLineDao;

@Component
@Scope("prototype")
@Transactional
public class FlightLineServiceImply implements FlightLineService{
	@Autowired
	FlightLineDao flDao;
	
	
	//pass a price insert one row to flightline and get the newest flightline obj
	public FlightLine getNewFlightLine(int price, int flightChanged, int roundTrip) {
		return flDao.getNewFlightLine(price, flightChanged, roundTrip);
	}


	
	public FlightLine getFlightLineById(long lineId) {
		return flDao.getFlightLineById(lineId);
	}
	

}
