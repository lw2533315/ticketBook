package com.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.Flight;
import com.dao.FlightDao;

@Component
@Scope("prototype")
@Transactional
public class FlightServiceImply implements FlightService {

	@Autowired
	FlightDao fltDao;

	/*
	 * purpse: use getAllFlight info to find all lines(line = flights) and sort
	 * return: list of lines lines = from one city to another, probablity many
	 * flights * city to another city notice: dptcity like "chicago" but the city in
	 * databse like"chicago, il" we need substring to catch then compare
	 * 
	 * from: index.jsp->flightController->here
	 */
	public List<Map.Entry<List<Flight>, Integer>> getAllFlight(String dptCity, String toCity, String dptTime,
			String rtnTime) {
		int index_test;
		if ((index_test = getIndex(dptCity, ",")) != -1) {
			dptCity = dptCity.substring(0, index_test);
		}
		if ((index_test = getIndex(toCity, ",")) != -1) {
			toCity = toCity.substring(0, index_test);
		}

		List<Flight> list = fltDao.getAllFlights(); // get info from db
		
		Map<Long, Flight> map = listToMap(list);  //convert from list-> map

		Map<List<Flight>, Integer> linesWithPrice = new HashMap<List<Flight>, Integer>(); // save one trip suitable
																							// <Flights, price> pair
		Map<List<Flight>, Integer> linesWithPrice2 = new HashMap<List<Flight>, Integer>(); // save back trip suitable
																							// pair

		// all the lines (one line could consisted of many fligt)
		//param 4 means at most stop 2 times line which is acceptable
		List<LinkedHashSet<Long>> oneTrip = getAllFilghtId(dptCity, toCity, dptTime, map, 4);
		// convert ordered id to <Flight, price> pair
		for (LinkedHashSet<Long> line : oneTrip) {
			int tempPrice = 0;
			List<Flight> tempLine = new ArrayList<Flight>();
			for (Long id : line) {
				tempLine.add(map.get(id));
				tempPrice += (map.get(id).getEcoPrice() * map.get(id).getEcoDiscount());
			}
			linesWithPrice.put(tempLine, tempPrice);
		}

		// round trip
		if (rtnTime != "") {
			// back trip all possible lines
			List<LinkedHashSet<Long>> backTrip = getAllFilghtId(toCity, dptCity, rtnTime, map, 4);
			for (LinkedHashSet<Long> line : backTrip) {
				int tempPrice = 0;
				List<Flight> tempLine = new ArrayList<Flight>();
				for (Long id : line) {
					tempLine.add(map.get(id));  //get flight info by flight id as key from map of flights
					tempPrice += (map.get(id).getEcoPrice() * map.get(id).getEcoDiscount());
				}
				linesWithPrice2.put(tempLine, tempPrice);
			}

			// combine two tipes together and give a 80% discount
			Map<List<Flight>, Integer> twoLinesWithPrice = new HashMap<List<Flight>, Integer>();
			for (Map.Entry<List<Flight>, Integer> m1 : linesWithPrice.entrySet()) { //go
				for (Map.Entry<List<Flight>, Integer> m2 : linesWithPrice2.entrySet()) {  //back
					List<Flight> temp = new ArrayList<Flight>(m1.getKey().size() + m2.getKey().size());
					if(compareTwoTime(m1.getKey().get(m1.getKey().size() - 1).getArriveTime(),m2.getKey().get(0).getDepartureTime())) {
						System.out.println("go" + m1.getKey().get(m1.getKey().size() - 1).getArriveTime());
						System.out.println("back" + m2.getKey().get(0).getDepartureTime());
						temp.addAll(m1.getKey());
						temp.addAll(m2.getKey());
						twoLinesWithPrice.put(temp, (int) ((m1.getValue() + m2.getValue()) * .8));
					}
				}
			}
			// need sort form cheapest to most expensive
			List<Map.Entry<List<Flight>, Integer>> sortMap = new ArrayList<Map.Entry<List<Flight>, Integer>>(
					twoLinesWithPrice.entrySet());
			Collections.sort(sortMap, new Comparator<Map.Entry<List<Flight>, Integer>>() {

				public int compare(Map.Entry<List<Flight>, Integer> mapping1,
						Map.Entry<List<Flight>, Integer> mapping2) {
					return mapping1.getValue() - mapping2.getValue();
				}
			});

			return sortMap;

		}
		// need sort form cheapest to most expensive
		List<Map.Entry<List<Flight>, Integer>> sortMap = new ArrayList<Map.Entry<List<Flight>, Integer>>(
				linesWithPrice.entrySet());
		Collections.sort(sortMap, new Comparator<Map.Entry<List<Flight>, Integer>>() {

			public int compare(Map.Entry<List<Flight>, Integer> mapping1, Map.Entry<List<Flight>, Integer> mapping2) {
				return mapping1.getValue() - mapping2.getValue();
			}
		});
		return sortMap;

	}

	/*
	 * find all lines (return id) by from and target city and dpt
	 * time***************************begin
	 **********************/
	
	
	/*input time is latter than now date() 2 hours return true*/
	public boolean  compareTime(String s) {
	    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date feature = null;
	    try {
	        feature =simpleFormat.parse(s);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	     Date date = new Date();
	    long diff = feature.getTime() - date.getTime();
	     return (diff/(1000 * 60 * 60.0)>2)? true: false;
	}
	
	
	/*back time - go time > 2 hours return true*/
	public boolean  compareTwoTime(String go, String back ) {
	    SimpleDateFormat simpleFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date featureGo = null;
	    Date featureBack = null;
	    try {
	        featureGo =simpleFormat.parse(go);
	        featureBack = simpleFormat.parse(back);
	    } catch (Exception e) {
	        e.printStackTrace();
	    } 
	     Date date = new Date();
	    long diff = featureBack.getTime() - featureGo.getTime();
	     return (diff/(1000 * 60 * 60.0)>2)? true: false;
	}
	
	
	
	/*
	 * purpose:filter data by departure city and arriveCity, dpt time and has empty
	 * seat return; List<list<long>> flight id collection
	 */
	public List<LinkedHashSet<Long>> getAllFilghtId(String dptCity, String toCity, String dptTime,
			Map<Long, Flight> map, int n) {
		
		
		 //from dptcity -> toCity
		//save possible flight
		List<Long> go = new ArrayList<Long>(); 
		
		
		/*filter condition: 1. dpt day need match the flight dpt day
		 *                  2. destinate city cannot be the dpt city of the flight
		 *                  3. the flight still has empty seat
		 *                  4. if book day is dpt day ,need compare now() with dpt time, at lease 2 hours for checking and boading           
		 *           */
		for (Map.Entry<Long, Flight> m : map.entrySet()) {
			if (m.getValue().getDepartureTime().substring(0, 10).equals(dptTime)
					&& !m.getValue().getDepartureCity().substring(0, getIndex(m.getValue().getDepartureCity(), ","))
							.equalsIgnoreCase(toCity) 
					&& m.getValue().getTotalSeats() > m.getValue().getReservedSeats() && compareTime(m.getValue().getDepartureTime())) {

				go.add(m.getKey());
			}
		}
		List<LinkedHashSet<Long>> oneTrip = oneTripeFlights(dptCity, toCity, dptTime, go, map, n);

		return oneTrip;
	}

	// find part of string need to cut like "chicago, ill"
	public int getIndex(String str, String pattern) {
		return str.indexOf(pattern);
	}

	/*
	 * purpose: find all the possible flights combinateion oparam: dptcity,
	 * targetcity, dpttime, flights collection return: lines collection
	 */
	public List<LinkedHashSet<Long>> oneTripeFlights(String dptCity, String toCity, String dptTime, List<Long> flights,
			Map<Long, Flight> map, int n) {
		List<LinkedHashSet<Long>> ret = new ArrayList<LinkedHashSet<Long>>();

		// the dptcity and tocity are both like ("los angles") in database is
		// like("los angles, ca")
		flightsHelper(ret, new LinkedHashSet<Long>(), 0, -3, dptCity, toCity, flights, map, n);
		return ret;
	}

	// backTrack possible Lines combination
	public void flightsHelper(List<LinkedHashSet<Long>> ret, LinkedHashSet<Long> cur, int stop, int curTime,
			String curCity, String target, List<Long> flights, Map<Long, Flight> map, int n) {
		if (stop == n) { //
			return;
		}
		if (curCity.equalsIgnoreCase(target)) {
			ret.add(new LinkedHashSet<Long>(cur));
		}
		for (int i = 0; i < flights.size(); i++) {
			if (!cur.contains(flights.get(i))) {
				if (map.get(flights.get(i)).getDepartureCity()
						.substring(0, getIndex(map.get(flights.get(i)).getDepartureCity(), ","))
						.equalsIgnoreCase(curCity)) {
					if (Integer.parseInt(map.get(flights.get(i)).getDepartureTime().substring(11, 13)) - curTime >= 3) {

						cur.add(flights.get(i));
						// update stop, update time by arrivetime, update curcity by arrive city
						flightsHelper(ret, cur, stop + 1,
								Integer.parseInt(map.get(flights.get(i)).getArriveTime().substring(11, 13)),
								map.get(flights.get(i)).getArriveCity().substring(0,
										getIndex(map.get(flights.get(i)).getArriveCity(), ",")),
								target, flights, map, n);
						cur.remove(flights.get(i));
					}
				}
			} 
		}

	}

	/* purpse: call dao method return city name */
	public List<String> getCityName() {
		return fltDao.getCityName();
	}

	/* purpose: convert list<Flight> to Map<serialid, Flight> */
	public Map<Long, Flight> listToMap(List<Flight> list) {
		Map<Long, Flight> map = new HashMap<Long, Flight>();
		for (Flight flight : list) {
			map.put(flight.getSerialId(), flight);
		}
		return map;
	}

	
	//filter data by company name
	public List<Flight> filterAirline(List<Flight> list, String airline) {
		List<Flight> ret = new ArrayList<Flight>();
		if (airline.equalsIgnoreCase("Alaska Airline")) {
			for (Flight flight : list) {
				if (flight.getFlightCompany().equalsIgnoreCase("alaska airline")) {
					ret.add(flight);
				}
			}
			return ret;
		} else if (airline.equalsIgnoreCase("American Airline")) {
			for (Flight flight : list) {
				if (flight.getFlightCompany().equalsIgnoreCase("American Airline")) {
					ret.add(flight);
				}
			}
			return ret;
		} else if (airline.equalsIgnoreCase("United Airline")) {
			for (Flight flight : list) {
				if (flight.getFlightCompany().equalsIgnoreCase("United Airline")) {
					ret.add(flight);
				}
			}
			return ret;
		} else if (airline.equalsIgnoreCase("Delta Airline")) {
			for (Flight flight : list) {
				if (flight.getFlightCompany().equalsIgnoreCase("Delta Airline")) {
					ret.add(flight);
				}
			}
			return ret;
		} else {
			return list;
		}

	}
	
	
	
	/*purpose: for ajax update the booking , jquery cannot read too complicated Collection have to simply it and uniform it 
	 * this function just do data convert */
	public List<List<Flight>> filterBookingForAjax(String dptCity, String toCity, String dptTime,
		String rtnTime, String airline, String stops, String carbinLevel){
		List<List<Flight>>ret = new ArrayList<List<Flight>>();
		List<Map.Entry<List<Flight>, Integer>> m = filterBooking(dptCity, toCity, dptTime,
				 rtnTime,  airline, stops,carbinLevel);
		for(Map.Entry<List<Flight>, Integer> line: m) {
		
			List<Flight>temp = new ArrayList<Flight>();
			for(Flight flight: line.getKey()) {
				temp.add(flight);
			}
			ret.add(temp);
		}
		
		
		return ret;
	}
	
	
	

	/*
	 * pupose: filter all lines by city, time and airline company, stops time,
	 * carbinlevel return: a list <flight, price>
	 */
	public List<Map.Entry<List<Flight>, Integer>> filterBooking(String dptCity, String toCity, String dptTime,
			String rtnTime, String airline, String stops, String carbinLevel) {
		int stopTime = stops.equalsIgnoreCase("any") ? 4 : (Integer.parseInt(stops) + 2); // set the most stops is 2
		int index_test;
		if ((index_test = getIndex(dptCity, ",")) != -1) {
			dptCity = dptCity.substring(0, index_test);
		}
		if ((index_test = getIndex(toCity, ",")) != -1) {
			toCity = toCity.substring(0, index_test);
		}

		List<Flight> list = fltDao.getAllFlights(); // get info from db

		// first filter airline company
		List<Flight> filtCompany = filterAirline(list, airline);

		// data from list to a map use flight.serial as key flight obj as value
		Map<Long, Flight> map = listToMap(filtCompany);

		Map<List<Flight>, Integer> linesWithPrice = new HashMap<List<Flight>, Integer>(); // save one trip suitable
																							// <Flights, price> pair
		List<LinkedHashSet<Long>> oneTrip = getAllFilghtId(dptCity, toCity, dptTime, map, stopTime); // find all lines
		// need convert data to map and filter carbin level
		for (LinkedHashSet<Long> line : oneTrip) {
			int tempPrice = 0;
			List<Flight> tempLine = new ArrayList<Flight>();
			for (Long id : line) {
				tempLine.add(map.get(id));
				if (carbinLevel.equalsIgnoreCase("economy")) {
					tempPrice += (map.get(id).getEcoPrice() * map.get(id).getEcoDiscount());
				} else {
					tempPrice += (map.get(id).getBusPrice() * map.get(id).getBusDiscount());
				}
			}
			linesWithPrice.put(tempLine, tempPrice);
		}

		Map<List<Flight>, Integer> linesWithPrice2 = new HashMap<List<Flight>, Integer>(); // save one trip suitable
																							// <Flights, price> pair
		if (rtnTime != "") {
			// back trip
			List<LinkedHashSet<Long>> backTrip = getAllFilghtId(toCity, dptCity, rtnTime, map, stopTime);
			for (LinkedHashSet<Long> line : backTrip) {
				int tempPrice = 0;
				List<Flight> tempLine = new ArrayList<Flight>();
				for (Long id : line) {
					tempLine.add(map.get(id));
					if (carbinLevel.equalsIgnoreCase("economy")) {
						tempPrice += (map.get(id).getEcoPrice() * map.get(id).getEcoDiscount());
					} else {
						tempPrice += (map.get(id).getBusPrice() * map.get(id).getBusDiscount());
					}
				}
				linesWithPrice2.put(tempLine, tempPrice);
			}

			// combine two tipes together and give a 90% discount
			Map<List<Flight>, Integer> twoLinesWithPrice = new HashMap<List<Flight>, Integer>();
			for (Map.Entry<List<Flight>, Integer> m1 : linesWithPrice.entrySet()) {
				for (Map.Entry<List<Flight>, Integer> m2 : linesWithPrice2.entrySet()) {
					List<Flight> temp = new ArrayList<Flight>(m1.getKey().size() + m2.getKey().size());
					temp.addAll(m1.getKey());
					temp.addAll(m2.getKey());
					twoLinesWithPrice.put(temp, (int) ((m1.getValue() + m2.getValue()) * .9));

				}
			}
			// need sort form cheapest to most expensive
			List<Map.Entry<List<Flight>, Integer>> sortMap = new ArrayList<Map.Entry<List<Flight>, Integer>>(
					twoLinesWithPrice.entrySet());
			Collections.sort(sortMap, new Comparator<Map.Entry<List<Flight>, Integer>>() {

				public int compare(Map.Entry<List<Flight>, Integer> mapping1,
						Map.Entry<List<Flight>, Integer> mapping2) {
					return mapping1.getValue() - mapping2.getValue();
				}
			});

			return sortMap;

		}
		// need sort form cheapest to most expensive
		List<Map.Entry<List<Flight>, Integer>> sortMap = new ArrayList<Map.Entry<List<Flight>, Integer>>(
				linesWithPrice.entrySet());
		Collections.sort(sortMap, new Comparator<Map.Entry<List<Flight>, Integer>>() {

			public int compare(Map.Entry<List<Flight>, Integer> mapping1, Map.Entry<List<Flight>, Integer> mapping2) {
				return mapping1.getValue() - mapping2.getValue();
			}
		});
		return sortMap;

	}

	/*call getFlightsbyids get flights info*/
	public List<Flight> getFlightsByIds(long[] ary) {
		return fltDao.getFlightsByIds(ary);
	}

	public void updateSeatById(int childNo, int adultNo, String level, List<Flight> line) {
		int seatNumber = childNo + adultNo;
		List<Long> serialId = new ArrayList<Long>();
		for(Flight flight: line) {
			serialId.add(flight.getSerialId());
		}
		
		fltDao.updateTicketByIds(seatNumber, level, serialId);
		
	}
	
	


}
