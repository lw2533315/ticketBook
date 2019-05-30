package com.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.Passenger;
@Component
@Scope("prototype")
//@Transactional
public class PassengerDaoImply implements PassengerDao{
	@Autowired
	SessionFactory sessionFactory;
	
	/*group passengers insert into table */
	public void saveOrUpdate(List<Passenger> passengers) {
		Session session = sessionFactory.getCurrentSession();
		for(Passenger passenger:passengers) {
			session.saveOrUpdate(passenger);
		}
		
	}

	/*insert one passenger into table*/
	public void saveOrUpdateOne(Passenger passenger) {
		sessionFactory.getCurrentSession().saveOrUpdate(passenger);
	}

}
