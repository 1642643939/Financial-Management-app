package com.finance.db;

import java.util.List;
import java.util.Observable;

import com.finance.model.MoneyModel;

public class MoneyOutObservable extends Observable {

    //单例
    private static MoneyOutObservable instance = null;

    public static MoneyOutObservable getInstance() {

        if (null == instance) {
            instance = new MoneyOutObservable();
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