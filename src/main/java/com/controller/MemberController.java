package com.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.bean.FlightLine;
import com.bean.Member;
import com.bean.PaymentCard;
import com.bean.Ticket;
import com.google.gson.Gson;
import com.service.MemberService;
import com.service.TicketService;



@Controller
public class MemberController {
	
	
	@Autowired
	MemberService ms;
	
	@Autowired
	TicketService ticketService;
	
	
	
	
	@RequestMapping(value="/loginmatch", method = RequestMethod.GET)
	public void loginMatch(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Member member = null;
		String match = "";
		if((member = ms.loginCheck(email, password))!= null) {
			ms.getId(member);
			HttpSession session = req.getSession();
			session.setAttribute("name", member.getFirstName());
			session.setAttribute("id", member.getMemberId());
			session.setAttribute("lastName", member.getLastName());
			match = "yes";			
		}
		try {
			resp.getWriter().write(match);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	/* purpose: check the email and password match the databas or not 
	 * param: from login get parameter
	 * return: jump back the login page and set session
	 * */
	@RequestMapping(value ="/login", method=RequestMethod.GET)
	public ModelAndView loginCheck(HttpServletRequest req, HttpServletResponse resp) {
		String backPageString = req.getParameter("url");
		ModelAndView mav = new ModelAndView(backPageString);
		return new ModelAndView(backPageString);
		
	}
	
	
	/*jump back index.jsp directly*/
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String backToHome() {
		return "index";
	}
	
	
	/*purpose: send back a object for save member info
	 * param: no
	 * return: jump back signup page
	 * */
	@RequestMapping(value="/findsignup", method=RequestMethod.GET)
	public ModelAndView signUp() {
		ModelAndView mav = new ModelAndView("signup") ;
		
		mav.addObject("member", new Member());
		
		return mav;
	}
	
	
	/*purpose: gather member info and call memberService to store
	 * param: req & resp & Member obj
	 * return: back to home page*/
	@RequestMapping(value="/savesignup", method=RequestMethod.POST)
	public ModelAndView createAccount(@ModelAttribute("member") Member member, HttpServletRequest req, HttpServletResponse resp) {
		String firstName = member.getFirstName();
		
		if(ms.signup(member)) {
			HttpSession session = req.getSession();
			long id = ms.getId(member);
			session.setAttribute("name", firstName);
			session.setAttribute("id", id);
			
			return new ModelAndView("index");
		}else {
			HttpSession session = req.getSession();
			session.setAttribute("signup", false);
			return new ModelAndView("signup");
		}
		
		
	}
	
	
	/*purpose: clear session and sign out
	 * return: jump back to home page*/
	@RequestMapping(value="/signOut", method=RequestMethod.GET)
	public ModelAndView signOut(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("clear session");
		HttpSession session = req.getSession(false);
		if(session != null)
			session.invalidate(); 
		return new ModelAndView("index");
	}
	
	
	
	/*purpose: find payment card info pass to checkout.jsp*/
	@RequestMapping(value="/bankInfo", method=RequestMethod.GET)
	public void getBankCardInfo(HttpServletRequest req, HttpServletResponse resp) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			System.out.println("session is empty");
		}
		long serialId = (Long)session.getAttribute("id");
		Member member = ms.getMemberById(serialId);
		PaymentCard pCard = member.getCard();
		
		String json = new Gson().toJson(pCard);
		
		try {
			resp.getWriter().write(json);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/*purpose: 1. jump to accountinfo page
	 *         2. pass member obj 
	 *         3. callservice calculate flights, line, and price and sort for history 
	 *from: each page account button         */
	@RequestMapping(value="/account", method = RequestMethod.GET)
	public ModelAndView toAccount(HttpServletRequest req) {
		HttpSession session = req.getSession(false);
		if(session == null) {
			new ModelAndView();
		}
		if(session == null) {
			return new ModelAndView("home");
		}
		long serialId = (Long)session.getAttribute("id");
		Member member = ms.getMemberById(serialId);
		ModelAndView mav = new ModelAndView("accountinfo");
		mav.addObject("member", member);
		
		//get a linkedHashMap
		Map<FlightLine, List<Ticket>> recorder = ms.sortFlightLineByTime(member.getMemberId());
		mav.addObject("lineInfo", recorder);
		
		return mav;
	}
	
	
	/*purpose: 1. change email*/
	@RequestMapping(value="/updateEmail", method = RequestMethod.GET)
	public void updateEmail(HttpServletRequest req, HttpServletResponse resp) {
		String email = req.getParameter("email");
		long memberId = Integer.parseInt(req.getParameter("id"));
		ms.updateEmailById(email, memberId);
		try {
			resp.getWriter().write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	/*change default payment card*/
	@RequestMapping(value="updateBankCard", method = RequestMethod.GET)
	public String updateBankCard(HttpServletRequest req) {
		String memberId = req.getParameter("memberId");
		String cardNumber = req.getParameter("cardNumber");
		String cardName = req.getParameter("cardName");
		String month = req.getParameter("month");
		String year = req.getParameter("year");
		String cvv = req.getParameter("cvv");
		PaymentCard pCard = new PaymentCard();
		pCard.setCardNumber(cardNumber);
		pCard.setCvv(Integer.parseInt(cvv));
		pCard.setExpireMonth(Integer.parseInt(month));
		pCard.setExpireYear(Integer.parseInt(year));
		pCard.setNameOnTheCard(cardName);
		pCard.setCardNumber(cardNumber);
		ms.updateBankCard(Long.parseLong(memberId), pCard);
		return "accountinfo";
	}
	
	
	@RequestMapping(value= "bankCard", method= RequestMethod.GET)
	public ModelAndView toBankCard(HttpServletRequest req) {
		String memberId = req.getParameter("memberId");
		System.out.println("memberc: tobankcard memberId " + memberId );
		ModelAndView mav = new ModelAndView("updateBankCard");
		mav.addObject("memberId", memberId);
		return mav;
		
	}
	
	
	/*purpose: 1. change phone*/
	@RequestMapping(value="/updatePhone", method = RequestMethod.GET)
	public void updatePhone(HttpServletRequest req, HttpServletResponse resp) {
		String phone = req.getParameter("phone");
		long memberId = Integer.parseInt(req.getParameter("id"));
		ms.updatePhoneById(phone, memberId);
		try {
			resp.getWriter().write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	/*purpose: 1. change password*/
	@RequestMapping(value="/updatePassword", method = RequestMethod.GET)
	public void updatePassword(HttpServletRequest req, HttpServletResponse resp) {
		String password = req.getParameter("password");
		long memberId = Integer.parseInt(req.getParameter("id"));
		ms.updatePasswordById(password, memberId);
		try {
			resp.getWriter().write("");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
}
