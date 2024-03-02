package com.finance.listener;

import com.finance.model.MoneyModel;

public interface RemoveListner {
	void setRemove(int pos, MoneyModel messageModel);
	void setUpdate(int pos, MoneyModel messageModel);
}
