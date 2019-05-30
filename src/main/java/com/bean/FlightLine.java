package com.bean;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;

@Entity

public class FlightLine {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long lineId;
	
	@OneToMany(mappedBy = "flightLine", cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<Ticket>tickets = new ArrayList<Ticket>();
	
	
	//how many flight need to take
	int flightChange;
	
	
	//1 means round trip, 0 means one trip
	int roundTrip;   
	
	public int getFlightChange() {
		return flightChange;
	}

	public void setFlightChange(int flightChange) {
		this.flightChange = flightChange;
	}

	public int getRoundTrip() {
		return roundTrip;
	}

	public void setRoundTrip(int roundTrip) {
		this.roundTrip = roundTrip;
	}

	int price;

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public long getLineId() {
		return lineId;
	}

	public void setLineId(long lineId) {
		this.lineId = lineId;
	}

	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}
	  

}
