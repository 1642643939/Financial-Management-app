package com.finance.model;

import java.io.Serializable;

public class FriendModel implements Serializable {

	private String friendUserId;
	private String friendId;
	private String friendRecommendUserId;

	private UserModel userMessage;

	public String getFriendUserId() {
		return friendUserId;
	}

	public void setFriendUserId(String friendUserId) {
		this.friendUserId = friendUserId;
	}

	public String getFriendId() {
		return friendId;
	}

	public void setFriendId(String friendId) {
		this.friendId = friendId;
	}

	public String getFriendRecommendUserId() {
		return friendRecommendUserId;
	}

	public void setFriendRecommendUserId(String friendRecommendUserId) {
		this.friendRecommendUserId = friendRecommendUserId;
	}

	public UserModel getUserMessage() {
		return userMessage;
	}

	public void setUserMessage(UserModel userMessage) {
		this.userMessage = userMessage;
	}

}
