package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.Flight;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.service.FlightService;


@Controller
public class FlightController {

	@Autowired
	FlightService fltService;

	/*
	 * purpose: find all possible connect flines(under 2 stops) param: citi and time
	 * info return: sorted list
	 */
	@RequestMapping(value = "/findbook", method = RequestMethod.POST)
	public ModelAndView traverseFlights(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("test");
		String fromCity = req.getParameter("flightFrom");
		String toCity = req.getParameter("flightTo");
		String dptTime = req.getParameter("dptTime");
		String rtnTime = req.getParameter("rtnTime");

		ModelAndView mav = new ModelAndView("booking");
		List<Map.Entry<List<Flight>, Integer>> flightsList = fltService.getAllFlight(fromCity, toCity, dptTime, rtnTime);
		mav.addObject("lines", flightsList);
		mav.addObject("flightFrom", fromCity);
		mav.addObject("flightTo", toCity);
		mav.addObject("dptTime", dptTime);
		mav.addObject("rtnTime", rtnTime);
		return mav;

	}

	/*
	 * purpose: autoaddress mapping
	 */
	@RequestMapping(value = "/autoAddress", method = RequestMethod.GET)
	public void getCityName(HttpServletRequest req, HttpServletResponse resp) {
		List<String> cities = fltService.getCityName();
		String json = new Gson().toJson(cities);
		try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/*
	 * purpose: helper finish filter all condition, city, time, airline company,
	 * stops time, carbin info
	 */
	@RequestMapping(value = "/filterBooking", method = RequestMethod.POST)
	public void filterBooking(HttpServletRequest req, HttpServletResponse resp) {
		String flightFrom = req.getParameter("flightFrom");
		
		String flightTo = req.getParameter("flightTo");
		String dptTime = req.getParameter("dptTime");
		String rtnTime = req.getParameter("rtnTime");
		String airline = req.getParameter("airline");
		String stops = req.getParameter("stops");
		String carbinLevel = req.getParameter("carbinlevel");
		

		
		List<List<Flight>> flightsList = fltService.filterBookingForAjax( flightFrom,  flightTo, dptTime,
				rtnTime, airline, stops,  carbinLevel);
		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
		String json = gson.toJson(flightsList);
		try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	
	
	@RequestMapping(value ="/jumpToTicket", method = RequestMethod.GET)
	public ModelAndView prepareTicket (HttpServletRequest req, HttpServletResponse resp) {
		//get all the flight's serial id
		int  size = Integer.parseInt(req.getParameter("param0"));
		long [] serialIdAry = new long[size];
		System.out.println("in controller");
		for(int i=0; i< size; i++) {
			serialIdAry[i] = Long.parseLong(req.getParameter("param"+ (i+1)));
		}
		
		//get the cabin level
		String carbinLevel = req.getParameter("param"+(size + 1));
		
		//find one trip or round tip
		String roundtrip = req.getParameter("param" + (size + 2));
		
		//get dptcity
		String dptCity = req.getParameter("param" + (size + 3));
		
		//get rtn city
		String rtnCity = req.getParameter("param" + (size + 4));
		
		//get dpttime
		String dptTime = req.getParameter("param" + (size + 5));
		
		//get rtntime if one trip rtntime = ""
		String rtnTime = roundtrip.equals("1")?req.getParameter("param" + (size + 6)): "";
		
		
		
		
		//get flights info by serial id
		List<Flight>line =  fltService.getFlightsByIds(serialIdAry);
		
		//find seatavalibale and price for child and adult depend on economy or business
		int seatAvailable = 1000;
		int adultPrice = 0;
		int childPrice = 0;
		for(Flight flight:line) {
			if(carbinLevel.equalsIgnoreCase("economy")) {  //economy
				seatAvailable = Math.min(seatAvailable, flight.getEcoSeats() - flight.getReservedEcoSeats());
				
					adultPrice += (int)(flight.getEcoPrice()*flight.getEcoDiscount());
					childPrice += (int)(flight.getEcoPrice()*flight.getChildDiscount());
			}else {
				seatAvailable = Math.min(seatAvailable, flight.getBusSeats() - flight.getReservedBusSeats());
				adultPrice = childPrice += (int)(flight.getBusPrice()*flight.getBusDiscount());
			}
		}
		
		
		if(roundtrip.equals("1")) {
			if( carbinLevel.equalsIgnoreCase("economy")) {
				adultPrice = (int)(adultPrice * 0.8);
				childPrice = (int)(childPrice * 0.8);
				
			}else {
				adultPrice = (int)(adultPrice * 0.8);
				childPrice = (int)(childPrice * 0.8);
			}
			
		}
		
		
		//save the line and carbinevel info for the following pages
		HttpSession  session = req.getSession(false);
		session.setAttribute("line", line);
		session.setAttribute("carbinLevel", carbinLevel);
		session.setAttribute("dptDay", dptTime);
		session.setAttribute("rtnDay", rtnTime);
		session.setAttribute("dptCity", dptCity);
		session.setAttribute("rtnCity", rtnCity);
		session.setAttribute("roundTrip", roundtrip);
		
				
		ModelAndView mav = new ModelAndView("ticketconfirm");
		mav.addObject("line", line);
		mav.addObject("seat", seatAvailable);
		mav.addObject("carbinLevel", carbinLevel);
		mav.addObject("childPrice", childPrice);
		mav.addObject("adultPrice", adultPrice);
		return mav;
	}
}
