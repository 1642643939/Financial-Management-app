package com.finance.model;

import java.io.Serializable;

public class MessageModel implements Serializable{
	
	private String messageMoney;
	private String messageCate;
	private String messageDate;
	private String messageMessage;
	private String messageType;
	private String messageCreateTime;
	private String messageMonth;
	
	
	
	public String getMessageMonth() {
		return messageMonth;
	}
	public void setMessageMonth(String messageMonth) {
		this.messageMonth = messageMonth;
	}
	public String getMessageMoney() {
		return messageMoney;
	}
	public void setMessageMoney(String messageMoney) {
		this.messageMoney = messageMoney;
	}
	public String getMessageCate() {
		return messageCate;
	}
	public void setMessageCate(String messageCate) {
		this.messageCate = messageCate;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessageMessage() {
		return messageMessage;
	}
	public void setMessageMessage(String messageMessage) {
		this.messageMessage = messageMessage;
	}
	public String getMessageType() {
		return messageType;
	}
	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}
	public String getMessageCreateTime() {
		return messageCreateTime;
	}
	public void setMessageCreateTime(String messageCreateTime) {
		this.messageCreateTime = messageCreateTime;
	}
	
	
	
	

}
