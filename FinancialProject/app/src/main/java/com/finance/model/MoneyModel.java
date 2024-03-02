package com.finance.model;

import java.io.Serializable;

public class MoneyModel implements Serializable{
	
	private String lookMoneyUserId;
	private String lookMoneyUserName;
	private String lookMoneyTypeId;
	private String lookMoneyTypeName;
	private String lookMoneyMoney;
	private String lookMoneyTime;
	private String typeMessage;
	private String totalMoney;
	private String monthMessage;
	private String lookMoneyId;
	private String tipMessage;

	
	public String getTipMessage() {
		return tipMessage;
	}
	public void setTipMessage(String tipMessage) {
		this.tipMessage = tipMessage;
	}
	public String getLookMoneyId() {
		return lookMoneyId;
	}
	public void setLookMoneyId(String lookMoneyId) {
		this.lookMoneyId = lookMoneyId;
	}
	public String getMonthMessage() {
		return monthMessage;
	}
	public void setMonthMessage(String monthMessage) {
		this.monthMessage = monthMessage;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getLookMoneyUserId() {
		return lookMoneyUserId;
	}
	public void setLookMoneyUserId(String lookMoneyUserId) {
		this.lookMoneyUserId = lookMoneyUserId;
	}
	public String getLookMoneyUserName() {
		return lookMoneyUserName;
	}
	public void setLookMoneyUserName(String lookMoneyUserName) {
		this.lookMoneyUserName = lookMoneyUserName;
	}
	public String getLookMoneyTypeId() {
		return lookMoneyTypeId;
	}
	public void setLookMoneyTypeId(String lookMoneyTypeId) {
		this.lookMoneyTypeId = lookMoneyTypeId;
	}
	public String getLookMoneyTypeName() {
		return lookMoneyTypeName;
	}
	public void setLookMoneyTypeName(String lookMoneyTypeName) {
		this.lookMoneyTypeName = lookMoneyTypeName;
	}
	public String getLookMoneyMoney() {
		return lookMoneyMoney;
	}
	public void setLookMoneyMoney(String lookMoneyMoney) {
		this.lookMoneyMoney = lookMoneyMoney;
	}
	public String getLookMoneyTime() {
		return lookMoneyTime;
	}
	public void setLookMoneyTime(String lookMoneyTime) {
		this.lookMoneyTime = lookMoneyTime;
	}
	public String getTypeMessage() {
		return typeMessage;
	}
	public void setTypeMessage(String typeMessage) {
		this.typeMessage = typeMessage;
	}
	
	
	

}
