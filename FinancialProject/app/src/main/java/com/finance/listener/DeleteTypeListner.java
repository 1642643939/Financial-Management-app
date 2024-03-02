package com.finance.listener;

import com.finance.model.IncomeModel;
import com.finance.model.PayModel;

public interface DeleteTypeListner {
	void setRemoveOut(int pos, PayModel payModel);
	void setRemoveIn(int pos, IncomeModel incomeModel);
}
