package com.finance.listener;

import com.finance.model.FriendModel;
import com.finance.model.UserModel;

public interface RecommendListner {
	void setRecommend(int pos, UserModel userModel);
	void setRecommendDelete(int pos, FriendModel userModel);
}
