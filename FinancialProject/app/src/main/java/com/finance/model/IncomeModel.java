package com.finance.model;

import java.io.Serializable;

public class IncomeModel implements Serializable{

	
	private String typeIncomeId;
	private String typeIncomeName;
	private String typeMoney;
	private String totalMoney;
	private String limitMoney;
	

	public String getLimitMoney() {
		return limitMoney;
	}
	public void setLimitMoney(String limitMoney) {
		this.limitMoney = limitMoney;
	}
	public String getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(String totalMoney) {
		this.totalMoney = totalMoney;
	}
	public String getTypeMoney() {
		return typeMoney;
	}
	public void setTypeMoney(String typeMoney) {
		this.typeMoney = typeMoney;
	}
	public String getTypeIncomeId() {
		return typeIncomeId;
	}
	public void setTypeIncomeId(String typeIncomeId) {
		this.typeIncomeId = typeIncomeId;
	}
	public String getTypeIncomeName() {
		return typeIncomeName;
	}
	public void setTypeIncomeName(String typeIncomeName) {
		this.typeIncomeName = typeIncomeName;
	}
	
	
	
}
