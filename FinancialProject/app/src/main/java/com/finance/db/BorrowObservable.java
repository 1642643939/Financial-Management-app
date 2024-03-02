package com.finance.db;

import java.util.Observable;

public class BorrowObservable extends Observable {

    //单例
    private static BorrowObservable instance = null;

    public static BorrowObservable getInstance() {

        if (null == instance) {
            instance = new BorrowObservable();
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