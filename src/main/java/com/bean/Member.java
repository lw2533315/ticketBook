package com.bean;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Entity
@Transactional
public class Member {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long memberId;
	String firstName;
	String lastName;
	
	
	@Autowired(required = false)
	PaymentCard card;
	String phone;
	String email;
	String password;
	
	
	@OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
	@JsonIgnore
	List<Ticket>tickets = new ArrayList<Ticket>(); 
	
	
	
	
	
	
	public List<Ticket> getTickets() {
		return tickets;
	}






	public void setTickets(List<Ticket> tickets) {
		this.tickets = tickets;
	}






	public Member() {
		super();
		// TODO Auto-generated constructor stub
	}






	public long getMemberId() {
		return memberId;
	}






	public void setMemberId(long memberId) {
		this.memberId = memberId;
	}






	public String getFirstName() {
		return firstName;
	}






	@Override
	public String toString() {
		return "Member [memberId=" + memberId + ", firstName=" + firstName + ", lastName=" + lastName + ", card=" + card
				+ ", phone=" + phone + ", email=" + email + ", password=" + password + "]";
	}






	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}






	public String getLastName() {
		return lastName;
	}






	public void setLastName(String lastName) {
		this.lastName = lastName;
	}






	






	public PaymentCard getCard() {
		return card;
	}






	public void setCard(PaymentCard card) {
		this.card = card;
	}






	public String getPhone() {
		return phone;
	}






	public void setPhone(String phone) {
		this.phone = phone;
	}






	public String getEmail() {
		return email;
	}






	public void setEmail(String email) {
		this.email = email;
	}






	public String getPassword() {
		return password;
	}






	public void setPassword(String password) {
		this.password = password;
	}






	
	

}
