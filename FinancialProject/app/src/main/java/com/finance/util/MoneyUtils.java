package com.finance.util;

import android.text.TextUtils;
import android.view.View;

import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.model.MoneyModel;
import com.finance.model.ResponseEntry;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.SliceValue;

public class MoneyUtils extends BaseFragment {

    private List<MoneyModel> list_result = new ArrayList<MoneyModel>();

    private void listMoney(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listAllUserMoney");
        params.put("lookMoneyUserId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
    }

    @Override
    public void initWidget() {

    }

    @Override
    public void initData() {
        listMoney(false);

    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultCode:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result.clear();
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<MoneyModel>>() {
                        }.getType());

                    } else {
                    }
                }

        }
    }

    public double getShouRuZhiChuDifference() {
        double ShouRuMoney = 0;
        double ZhiChuMoney = 0;
        listMoney(false);
        for (int i = 0; i < list_result.size(); i++) {
            if (list_result.get(i).getTypeMessage().equals("1")) {
                ShouRuMoney = Double.valueOf(ShouRuMoney) + Double.valueOf(list_result.get(i).getLookMoneyMoney());
            } else {
                ZhiChuMoney = Double.valueOf(ZhiChuMoney) + Double.valueOf(list_result.get(i).getLookMoneyMoney());
            }
        }
        System.out.println("1111111"+(ShouRuMoney-ZhiChuMoney));

        return ShouRuMoney - ZhiChuMoney;
    }

    @Override
    public void onClick(View view) {

    }
}
