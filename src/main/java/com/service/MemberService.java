package com.service;

import java.util.List;
import java.util.Map;

import com.bean.FlightLine;
import com.bean.Member;
import com.bean.PaymentCard;
import com.bean.Ticket;

public interface MemberService {
	public Member loginCheck(String email, String password);
	public boolean    signup(Member member);
	public long  getId(Member member);
	public Member getMemberById(long serialId);
	public void updateBankCard(long id, PaymentCard pCard);
	public void updateEmailById(String id, long memberId);
	public void updatePhoneById(String phone, long memberId);
	public void updatePasswordById(String password, long memberId);
	public Map<FlightLine, List<Ticket>> sortFlightLineByTime(long memberId);

}
