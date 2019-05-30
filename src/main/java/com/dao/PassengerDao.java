package com.dao;

import java.util.List;

import com.bean.Passenger;

public interface PassengerDao {
	

	public void saveOrUpdate(List<Passenger> passengers);
	public void saveOrUpdateOne(Passenger passenger);
}
