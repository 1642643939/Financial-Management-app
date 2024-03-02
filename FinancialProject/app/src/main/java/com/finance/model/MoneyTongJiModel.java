package com.finance.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/4/3.
 */

public class MoneyTongJiModel implements Serializable {


    private String  timeMsg;
    private String  money;


    public String getTimeMsg() {
        return timeMsg;
    }

    public void setTimeMsg(String timeMsg) {
        this.timeMsg = timeMsg;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }
}
