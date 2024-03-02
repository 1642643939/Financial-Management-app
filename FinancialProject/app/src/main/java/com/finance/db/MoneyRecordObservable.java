package com.finance.db;

import java.util.Observable;

public class MoneyRecordObservable extends Observable {

    //单例
    private static MoneyRecordObservable instance = null;

    public static MoneyRecordObservable getInstance() {

        if (null == instance) {
            instance = new MoneyRecordObservable();
        }
        return instance;
    }

    //通知观察者更新数据
    public void notifyStepChange(String msg) {
        //关键方法，必须写，具体实现可以查看源码
        setChanged();//设置changeFlag
        notifyObservers(msg);//通知观察者
    }

}