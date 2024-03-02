package com.finance.model;

import java.io.Serializable;
import java.util.List;

public class AnswerModel implements Serializable{
	
	
	private String answerUserId;
	private String answerMessage;
	private String answerUserName;
	private String answerCreatime;
	private String answerId;
	private List<ReplyModel> replyMsg;
	

	public List<ReplyModel> getReplyMsg() {
		return replyMsg;
	}
	public void setReplyMsg(List<ReplyModel> replyMsg) {
		this.replyMsg = replyMsg;
	}
	public String getAnswerUserId() {
		return answerUserId;
	}
	public void setAnswerUserId(String answerUserId) {
		this.answerUserId = answerUserId;
	}
	public String getAnswerMessage() {
		return answerMessage;
	}
	public void setAnswerMessage(String answerMessage) {
		this.answerMessage = answerMessage;
	}
	public String getAnswerUserName() {
		return answerUserName;
	}
	public void setAnswerUserName(String answerUserName) {
		this.answerUserName = answerUserName;
	}
	public String getAnswerCreatime() {
		return answerCreatime;
	}
	public void setAnswerCreatime(String answerCreatime) {
		this.answerCreatime = answerCreatime;
	}
	public String getAnswerId() {
		return answerId;
	}
	public void setAnswerId(String answerId) {
		this.answerId = answerId;
	}

}
