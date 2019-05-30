package com.service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.taglibs.standard.lang.jstl.AndOperator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.dao.MemberDao;
import com.bean.FlightLine;
import com.bean.Member;
import com.bean.PaymentCard;
import com.bean.Ticket;

@Component
@Transactional
public class MemberServiceImply implements MemberService {

	@Autowired
	MemberDao memberDao;

	/*
	 * purspose: check the email & password match or not by call DAO method
	 * getAllData param: email & password return: firstName or ""
	 */
	public Member loginCheck(String email, String password) {
		List<Member> list = memberDao.getAllData();

		for (int i = 0; i < list.size(); i++) {
			if (email.equals(list.get(i).getEmail()) && password.equals(list.get(i).getPassword())) {
				return list.get(i);
			}
		}
		return null;
	}

	/*
	 * purpose: save infor to member table param: member obj return: true if member
	 * is new acount
	 */
	public boolean signup(Member member) {
		List<Member> list = memberDao.getAllData();
		for (int i = 0; i < list.size(); i++) {
			if (member.getEmail().equals(list.get(i).getEmail())) {
				return false;
			}
		}
		return memberDao.saveOrUpdate(member);
	}

	/* purpose call getId from DAO */
	public long getId(Member member) {
		return memberDao.getId(member);
	}

	/* purpose: call getBankCardInfo (Dao) get member info */
	public Member getMemberById(long serialId) {
		return memberDao.getMemberById(serialId);
	}

	/* purpose: call dao update method to update embeddable field */
	public void updateBankCard(long id, PaymentCard pCard) {
		memberDao.updateBankCard(id, pCard);

	}

	/* purpose: update email */
	public void updateEmailById(String id, long memberId) {
		memberDao.updateEmailById(id, memberId);
	}

	/* purpose: update phone */
	public void updatePhoneById(String phone, long memberId) {
		memberDao.updatePhoneById(phone, memberId);

	}

	/* purpose: update password */
	public void updatePasswordById(String password, long memberId) {
		memberDao.updatePasswordById(password, memberId);
	}

	/*
	 * purpose: 1.find all ticket 
	 * 			2. category ticket by FlightLine(map)
	 * 			3. sorted map by  departureTime
	 * from account button->member controller ->sortflightlinebytime
	 */
	public Map<FlightLine, List<Ticket>> sortFlightLineByTime(long memberId) {
		System.out.println("memberS sortFlightLinebytime test point 1");
		
		
		// cannot use  member obj stored in session it is lazy init not include tickets info, so need get data from db directly
		List<Ticket> tickets = memberDao.getMemberById(memberId).getTickets(); 
		
		System.out.println("memberS sortFlightLinebytime test point 2 tickets.size is " + tickets.size());
        
		//find flightLine vs tickets paire   flights not promise by the increase order(dpt time)
		Map<FlightLine, List<Ticket>> lineToTicket = new HashMap<FlightLine, List<Ticket>>();
		for (Ticket ticket : tickets) {
			if (lineToTicket.containsKey(ticket.getFlightLine())) {
				int size = lineToTicket.get(ticket.getFlightLine()).size();

				// help over 1 person buy the same flight ticket, need check how to create
				// ticket to the ticket table
				lineToTicket.get(ticket.getFlightLine()).add(ticket);
			} else {
				List<Ticket> tempTicket = new ArrayList<Ticket>();
				tempTicket.add(ticket);
				lineToTicket.put(ticket.getFlightLine(), tempTicket);

			}
		}
		
		//sorted ticket dpttime and save it to a List
		Map<FlightLine, List<Ticket>> sortedLineToTicket = new HashMap<FlightLine, List<Ticket>>();
		for(Map.Entry<FlightLine, List<Ticket>> map: lineToTicket.entrySet()) {
			List<Ticket> line = map.getValue().stream().sorted((v1, v2)->compareTwoTime(v1.getFlight().getDepartureTime(),v2.getFlight().getDepartureTime())).collect(Collectors.toList()); 
			sortedLineToTicket.put(map.getKey(), line);
		}
		
		
		//sorted map by ticket's flight's departtime
		Map<FlightLine, List<Ticket>> m = sortedLineToTicket.entrySet().stream()
				.sorted((v1,v2)->compareTwoTime(v1.getValue().get(0).getFlight().getDepartureTime(),v2.getValue().get(0).getFlight().getDepartureTime()))
				.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (oldValue, newValue) -> oldValue,
						LinkedHashMap::new));
//		List<Map.Entry<FlightLine, LinkedHash>>

		return m;
	}
	
	
	/*purpose:  compare two string of date, time*/
	public int compareTwoTime(String v1, String v2) {
		if(v1.equals(v2)) {
			return 0;
		}
		 DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    	 LocalDate ld1 = LocalDate.parse(v1, fmt);
    	 LocalTime lt1 =  LocalTime.parse(v1, fmt);
    	 
    	 LocalDate ld2 = LocalDate.parse(v2, fmt);
    	 LocalTime lt2 =  LocalTime.parse(v2, fmt);
    	 if(ld1.isBefore(ld2)) {
    		 return -1;
    	 }else if(ld1.isEqual(ld2) && lt1.isBefore(lt2)) {
    		 return -1;
    	 }else {
    		 return 1;
    	 }
	}

}
