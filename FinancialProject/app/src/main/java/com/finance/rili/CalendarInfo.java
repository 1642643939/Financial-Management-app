package com.finance.rili;

/**
 * Created by Administrator on 2016/7/30.
 */
public class CalendarInfo {
    /**
     * �?
     */
    public int year;
    /**
     * �?
     */
    public int month;
    /**
     * �?
     */
    public int day;
    /**
     * 描述�?
     */
    public String des;
    /**
     * 是否为休、班。�??1为休�?2为班，默认为普�?�日�?
     */
    public int rest;

    /**
     * 构�?�函�?
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期�?
     * @param des   事务描述
     */
    public CalendarInfo(int year, int month, int day, String des) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
    }

    /**
     * 构�?�函�?
     * @param year  事务年份
     * @param month 事务月份
     * @param day   事务日期�?
     * @param des   事务描述
     * @param rest  是否为休、班。�??1为休�?2为班，默认为普�?�日�?
     */
    public CalendarInfo(int year, int month, int day, String des, int rest) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.des = des;
        this.rest = rest;
    }
}
