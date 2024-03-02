package com.finance.model;

import java.io.Serializable;

public class zxIncomeModel implements Serializable{


    private String money;
    private String lookMoneyTime;


    public String getMoney() {
        return money;
    }
    public void setMoney(String money) {
        this.money = money;
    }
    public String getLookMoneyTime() {
        return lookMoneyTime;
    }
    public void setLookMoneyTime(String lookMoneyTime) {
        this.lookMoneyTime = lookMoneyTime;
    }



}