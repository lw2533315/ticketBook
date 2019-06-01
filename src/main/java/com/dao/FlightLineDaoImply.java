package com.dao;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.bean.FlightLine;

@Component
@Scope("prototype")
//@Transactional
public class FlightLineDaoImply  implements FlightLineDao{
	@Autowired
	SessionFactory sessionFactory;
	
	
	/*purpse:  1 creat a new flightLine
	 *         2 return the newest flightLine;
	 *from:   checkout.jsp->ticketController->flightline service->here
	 * */
	public FlightLine getNewFlightLine(int price, int flightChanged, int roundTrip) {
		FlightLine fl = new FlightLine();
		fl.setPrice(price);
		fl.setFlightChange(flightChanged);
		fl.setRoundTrip(roundTrip);
		Session session = sessionFactory.getCurrentSession();
		session.saveOrUpdate(fl);
		List<FlightLine> fls = new ArrayList<FlightLine>();
		Query query = session.createQuery("from FlightLine order by lineId DESC");
		query.setMaxResults(1);
		return  (FlightLine) query.uniqueResult();
	}
	
	
	//get flightline byid
	public FlightLine getFlightLineById(long lineId) {
		return sessionFactory.getCurrentSession().get(FlightLine.class, lineId);
	}

	
	/*purpose: delete flight line and auto delete tickets
	 * from   accountInfo.jsp->ticketservice -> flightlinedao*/
	public void deleteFlightLineByObj(FlightLine obj) {
		sessionFactory.getCurrentSession().delete(obj);
		
	}


	/*purpose:  update table by obj */
	public void saveOrUpdate(FlightLine fl) {
		sessionFactory.getCurrentSession().update(fl);
//		System.out.println("test>>>>>>>>>" + sessionFactory.getCurrentSession().get(FlightLine.class,22L).getTickets().size());
	}
	
	

}
