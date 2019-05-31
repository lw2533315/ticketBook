package com.bean;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope("prototype")
@Embeddable
public class PaymentCard {
	//if when regist a membership do not apply card info 
	@Column(nullable = true)
	String cardNumber;
	@Override
	public String toString() {
		return "PaymentCard [cardNumber=" + cardNumber + ", expireMonth=" + expireMonth + ", expireYear=" + expireYear
				+ ", cvv=" + cvv + "]";
	}
	@Column(nullable = true)
	int expireMonth;
	@Column(nullable = true)
	int expireYear;
	@Column(nullable = true)
	int cvv;
	
	@Column(nullable = true)
	String nameOnTheCard;
	
	public String getNameOnTheCard() {
		return nameOnTheCard;
	}
	public void setNameOnTheCard(String nameOnTheCard) {
		this.nameOnTheCard = nameOnTheCard;
	}
	public PaymentCard() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getExpireMonth() {
		return expireMonth;
	}
	public void setExpireMonth(int expireMonth) {
		this.expireMonth = expireMonth;
	}
	public int getExpireYear() {
		return expireYear;
	}
	public void setExpireYear(int expireYear) {
		this.expireYear = expireYear;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	
	
	

}
