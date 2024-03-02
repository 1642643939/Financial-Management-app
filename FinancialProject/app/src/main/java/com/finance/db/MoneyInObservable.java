package com.finance.db;

import java.util.List;
import java.util.Observable;

import com.finance.model.MoneyModel;

public class MoneyInObservable extends Observable {

    //单例
    private static MoneyInObservable instance = null;

    public static MoneyInObservable getInstance() {

        if (null == instance) {
            instance = new MoneyInObservable();
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