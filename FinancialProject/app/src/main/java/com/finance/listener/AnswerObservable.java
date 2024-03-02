package com.finance.listener;

import java.util.Observable;


public class AnswerObservable extends Observable {

    private static AnswerObservable instance = null;

    public static AnswerObservable getInstance() {

        if (null == instance) {
            instance = new AnswerObservable();
        }
        return instance;
    }

    public void notifyStepChange(String msg) {
        setChanged();
        notifyObservers(msg);
    }

}