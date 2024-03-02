package com.finance.model;

import java.io.Serializable;

public class PayModel implements Serializable{
	
	private String typePayId;
	private String typePayName;
	private String typeMoney;
	private String totalMoney;
	private String limitMoney;	
	
	
	
	public String getLimitMoney() {
		return limitMoney;
	}
	public void setLimitMoney(String limitMoney) {
		this.limitMoney = limitMoney;
	}
	public String getTypeMoney() {
		return typeMoney;
	}
	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getTypePayId() {
		return typePayId;
	}
	public void setTypePayId(String typePayId) {
		this.typePayId = typePayId;
	}
	public String getTypePayName() {
		return typePayName;
	}
	public void setTypePayName(String typePayName) {
		this.typePayName = typePayName;
	}
	
	
	
	
	
	

}
