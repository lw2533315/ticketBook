package com.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.Passenger;
import com.dao.PassengerDao;

@Component
@Scope("prototype")
@Transactional
public class PassengerServiceImply  implements PassengerService{
	@Autowired
	PassengerDao psgDao;
	public void saveOrUpdate(List<Passenger> passengers) {
		psgDao.saveOrUpdate(passengers);
		
	}

}
