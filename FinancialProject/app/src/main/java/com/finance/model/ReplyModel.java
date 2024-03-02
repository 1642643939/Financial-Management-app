package com.finance.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2019/12/17.
 */

public class ReplyModel implements Serializable {


    /**
     * replyAnswerId : 7
     * replyUserId : 90
     * replyCreatime : 2019-12-17 17:36
     * replyId : 8
     * replyUserName : Tom
     * replyMessage : 今天的作业信息应该这样子作答处理
     */

    private int replyAnswerId;
    private int replyUserId;
    private String replyCreatime;
    private int replyId;
    private String replyUserName;
    private String replyMessage;
    private String replyTeaMessage;


    public String getReplyTeaMessage() {
        return replyTeaMessage;
    }

    public void setReplyTeaMessage(String replyTeaMessage) {
        this.replyTeaMessage = replyTeaMessage;
    }

    public int getReplyAnswerId() {
        return replyAnswerId;
    }

    public void setReplyAnswerId(int replyAnswerId) {
        this.replyAnswerId = replyAnswerId;
    }

    public int getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(int replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyCreatime() {
        return replyCreatime;
    }

    public void setReplyCreatime(String replyCreatime) {
        this.replyCreatime = replyCreatime;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    public String getReplyMessage() {
        return replyMessage;
    }

    public void setReplyMessage(String replyMessage) {
        this.replyMessage = replyMessage;
    }
}
