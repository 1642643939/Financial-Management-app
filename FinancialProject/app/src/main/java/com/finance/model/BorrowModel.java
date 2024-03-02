package com.finance.model;

import java.io.Serializable;

/**
 * Created by Pony on 2020/2/21.
 */

public class BorrowModel implements Serializable {

    private String borrowRealName;
    private String borrowMoney;
    private String borrowType;
    private String borrowUserId;
    private String borrowId;
    private String borrowTime;


    public String getBorrowRealName() {
        return borrowRealName;
    }

    public void setBorrowRealName(String borrowRealName) {
        this.borrowRealName = borrowRealName;
    }

    public String getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(String borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType;
    }

    public String getBorrowUserId() {
        return borrowUserId;
    }

    public void setBorrowUserId(String borrowUserId) {
        this.borrowUserId = borrowUserId;
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }
}
