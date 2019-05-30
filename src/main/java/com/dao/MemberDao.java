package com.dao;

import java.util.List;

import com.bean.Member;
import com.bean.PaymentCard;
import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

public interface MemberDao {
	public boolean saveOrUpdate(Member obj);
	public boolean delete(Member obj);
	public List<Member> getAllData() ;
	public long getId (Member obj);
	public Member getMemberById(long memberId);
	public void updateBankCard(long id, PaymentCard pCard);
	public void updateEmailById(String email, long memberId);
	public void updatePhoneById(String phone, long memberId);
	public void updatePasswordById(String password, long memberId);

}
