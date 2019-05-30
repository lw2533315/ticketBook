package com.bean;

import java.io.Serializable;
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
import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import com.google.gson.annotations.Expose;

@Entity
public class Flight implements Serializable  {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY )
	@Expose
	long serialId;
	@Expose   //gson annotation
	String flightId;
	
	@Expose
	String flightCompany;
	
	@Expose
	String departureCityBrief;
	
	@Expose
	String departureCity;
	
	@Expose
	String arriveCityBrief;
	
	@Expose
	String arriveCity;
	
	@Expose
	String departureTime;
	
	@Expose
	String arriveTime;
	
	@Expose
	int totalSeats;
	
	@Expose
	int ecoSeats;
	
	@Expose
	int busSeats;
	
	@Expose
	int reservedEcoSeats;
	
	@Expose
	int reservedBusSeats;
	
	@Expose
	int reservedSeats;
	
	@Expose
	int ecoPrice;
	
	@Expose
	int busPrice;
	
	@Expose
	double busDiscount;
	
	@Expose
	double ecoDiscount;
	
	@Expose
	double childDiscount;
	
	
	//here need set fetch to eager, since the flight info is load before, not in the cache, 
	//we set bimapping by ticket, the list is second level data cannot be initialize if 
	//is lazy model
	@OneToMany(mappedBy = "flight", cascade = CascadeType.ALL ,fetch = FetchType.EAGER)
	@JsonIgnore
	List<Ticket> tickets = new ArrayList<Ticket>();
	
	public List<Ticket> getTickets() {
		return tickets;
	}

	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}

	

	public Flight() {
		super();
		// TODO Auto-generated constructor stub
	}

	public long getSerialId() {
		return serialId;
	}

	public void setSerialId(long serialId) {
		this.serialId = serialId;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getFlightCompany() {
		return flightCompany;
	}

	public void setFlightCompany(String flightCompany) {
		this.flightCompany = flightCompany;
	}

	public String getDepartureCityBrief() {
		return departureCityBrief;
	}

	public void setDepartureCityBrief(String departureCityBrief) {
		this.departureCityBrief = departureCityBrief;
	}

	public String getDepartureCity() {
		return departureCity;
	}

	public void setDepartureCity(String departureCity) {
		this.departureCity = departureCity;
	}

	public String getArriveCityBrief() {
		return arriveCityBrief;
	}

	public void setArriveCityBrief(String arriveCityBrief) {
		this.arriveCityBrief = arriveCityBrief;
	}

	public String getArriveCity() {
		return arriveCity;
	}

	public void setArriveCity(String arriveCity) {
		this.arriveCity = arriveCity;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		this.arriveTime = arriveTime;
	}

	public int getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(int totalSeats) {
		this.totalSeats = totalSeats;
	}

	public int getEcoSeats() {
		return ecoSeats;
	}

	public void setEcoSeats(int ecoSeats) {
		this.ecoSeats = ecoSeats;
	}

	public int getBusSeats() {
		return busSeats;
	}

	public void setBusSeats(int busSeats) {
		this.busSeats = busSeats;
	}

	public int getReservedEcoSeats() {
		return reservedEcoSeats;
	}

	public void setReservedEcoSeats(int reservedEcoSeats) {
		this.reservedEcoSeats = reservedEcoSeats;
	}

	public int getReservedBusSeats() {
		return reservedBusSeats;
	}

	public void setReservedBusSeats(int reservedBusSeats) {
		this.reservedBusSeats = reservedBusSeats;
	}

	public int getReservedSeats() {
		return reservedSeats;
	}

	public void setReservedSeats(int reservedSeats) {
		this.reservedSeats = reservedSeats;
	}

	public int getEcoPrice() {
		return ecoPrice;
	}

	public void setEcoPrice(int ecoPrice) {
		this.ecoPrice = ecoPrice;
	}

	public int getBusPrice() {
		return busPrice;
	}

	public void setBusPrice(int busPrice) {
		this.busPrice = busPrice;
	}

	public double getBusDiscount() {
		return busDiscount;
	}

	public void setBusDiscount(double busDiscount) {
		this.busDiscount = busDiscount;
	}

	public double getEcoDiscount() {
		return ecoDiscount;
	}

	public void setEcoDiscount(double ecoDiscount) {
		this.ecoDiscount = ecoDiscount;
	}

	public double getChildDiscount() {
		return childDiscount;
	}

	public void setChildDiscount(double childDiscount) {
		this.childDiscount = childDiscount;
	}
	
	public void printInfo() {
		System.out.println(departureCity + " -> " + arriveCity);
		System.out.println(departureTime + " -> " + arriveTime);
		
	}
	

}
