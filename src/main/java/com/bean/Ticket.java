package com.bean;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.springframework.stereotype.Component;

@Entity
public  class Ticket {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long ticketId;
	
	@ManyToOne
	@JoinColumn(name = "flight_serialId")
	@JsonIgnore
	Flight flight;
	
	
	@ManyToOne
	@JoinColumn(name = "passenger_id")
	@JsonIgnore
	Passenger passenger;
	
	@ManyToOne
	@JoinColumn(name = "member_id")
	@JsonIgnore
	Member member;
	
	@ManyToOne
	@JoinColumn(name = "line_id")
	@JsonIgnore
	FlightLine flightLine;
	
	
	String carbinLevel;


	public String getCarbinLevel() {
		return carbinLevel;
	}


	public void setCarbinLevel(String carbinLevel) {
		this.carbinLevel = carbinLevel;
	}


	public FlightLine getFlightLine() {
		return flightLine;
	}


	public void setFlightLine(FlightLine flightLine) {
		this.flightLine = flightLine;
	}


	public Member getMember() {
		return member;
	}


	public void setMember(Member member) {
		this.member = member;
	}


	public Ticket() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Long getTicketId() {
		return ticketId;
	}


	public void setTicketId(Long ticketId) {
		this.ticketId = ticketId;
	}


	public Flight getFlight() {
		return flight;
	}


	public void setFlight(Flight flight) {
		this.flight = flight;
	}


	public Passenger getPassenger() {
		return passenger;
	}


	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
	
	
	
	
	
	

	
	

}
