package com.dao;

import java.util.List;

import javax.persistence.criteria.From;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


import com.bean.Member;
import com.bean.PaymentCard;

@Component
@Scope("prototype")
//@Transactional
public class MemberDaoImply implements MemberDao {
	@Autowired
	SessionFactory sessionFactory;
	
	
	/*purpose: save the new accout to database 
	 * return: true if save success
	 * */
	public boolean saveOrUpdate(Member obj) {
		try { 
			sessionFactory.getCurrentSession().saveOrUpdate(obj);
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	
	/*purpose: delete one obj from table
	 * param: member obj
	 * return: true if delete success
	 * */
	public boolean delete(Member obj) {
		try {
			sessionFactory.getCurrentSession().delete(obj);
			return true;
		}catch(Exception e) {
			System.out.println(e);
			return false;
		}
			
		
	}
	
	
	       
	/*purpose: traverse all member table 
	 * return: list of members
	 * */
	public List<Member> getAllData() {
		   return sessionFactory.getCurrentSession().createCriteria(Member.class).list();
		
	}

	
	/*purpse: find id by member obj
	 * param: member obj
	 * return id if the obj in table otherwise return 0
	 * */
	public long getId(Member obj) {
		List<Member>list = sessionFactory.getCurrentSession().createCriteria(Member.class).list();
		for(Member m: list) {
			if (obj.getEmail().equals(m.getEmail())) {
				return m.getMemberId();
			}
		}
		return 0;
	}

	/*purpose: get member info by id*/
	public Member getMemberById(long memberId) {
		Member member = new Member();
		member = sessionFactory.getCurrentSession().get(Member.class, memberId);
		return member;
	}

	/*purpose: update bank info for id*/
	public void updateBankCard(long id, PaymentCard pCard) {
		
		Member member = sessionFactory.getCurrentSession().get(Member.class, id);
		member.setCard(pCard);
		sessionFactory.getCurrentSession().saveOrUpdate(member);
	}

	
	/*update email*/
	public void updateEmailById(String email, long memberId) {
		Session session = sessionFactory.getCurrentSession();
		Member member = session.get(Member.class, memberId);
		member.setEmail(email);
		session.saveOrUpdate(member);
		
		
	}

	/*update phone*/
	public void updatePhoneById(String phone, long memberId) {
		Session session = sessionFactory.getCurrentSession();
		Member member = session.get(Member.class, memberId);
		member.setPhone(phone);
		session.saveOrUpdate(member);
		
	}


	/*update password*/
	public void updatePasswordById(String password, long memberId) {
		Session session = sessionFactory.getCurrentSession();
		Member member = session.get(Member.class, memberId);
		member.setPassword(password);
		session.saveOrUpdate(member);
		
	}
	
	

}
