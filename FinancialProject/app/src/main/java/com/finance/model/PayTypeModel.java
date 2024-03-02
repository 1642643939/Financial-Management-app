package com.finance.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2020/4/22.
 */

public class PayTypeModel implements Serializable {

    private String totalNumber;
    private String lookMoneyPayType;

    public String getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(String totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getLookMoneyPayType() {
        return lookMoneyPayType;
    }

    public void setLookMoneyPayType(String lookMoneyPayType) {
        this.lookMoneyPayType = lookMoneyPayType;
    }
}
