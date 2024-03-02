package com.finance.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.finance.R;
import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.model.IncomeModel;
import com.finance.model.PayModel;
import com.finance.model.ResponseEntry;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.PieChartView;


public class BingZhiChuFragment extends BaseFragment {

    // 获取view
    private View rootView;
    PieChartView chart_bing;
    private View tol;
    // 数据
    private PieChartData pieChardata;
    List<SliceValue> values = new ArrayList<SliceValue>();
    // 定义数据，实际情况肯定不是这样写固定值的

    private double[] stateData = new double[2];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bing, null);
        initWidget();
        initData();
        return rootView;
    }

    @Override
    public void initWidget() {
        chart_bing = (PieChartView) rootView.findViewById(R.id.chart_bing);
        tol=rootView.findViewById(R.id.tol);
        tol.setVisibility(View.GONE);
        chart_bing.setOnValueTouchListener(selectListener);// 设置点击事件监听


    }

    @Override
    public void onClick(View v) {

    }

    private void listIncome(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listPay");
        params.put("userId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
    }

    private List<PayModel> list_result = new ArrayList<PayModel>();

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {

                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<PayModel>>() {
                        }.getType());


                        for (int i = 0; i < list_result.size(); ++i) {
                            String typeMoney = list_result.get(i).getTypeMoney();
                            if (typeMoney != null) {
                                SliceValue sliceValue = new SliceValue(Float.valueOf(typeMoney.trim()), ChartUtils.pickColor());
                                values.add(sliceValue);
                            }
                        }
                        initPieChart();
                    }


                }
                break;


        }

    }

    @Override
    public void initData() {

        listIncome(false);
    }

    /**
     * 初始化
     */
    private void initPieChart() {
        pieChardata = new PieChartData();
        pieChardata.setHasLabels(true);// 显示表情
        pieChardata.setHasLabelsOnlyForSelected(false);// 不用点击显示占的百分比
        pieChardata.setHasLabelsOutside(false);// 占的百分比是否显示在饼图外面
        pieChardata.setHasCenterCircle(true);// 是否是环形显示
        pieChardata.setValues(values);// 填充数据
        pieChardata.setCenterCircleColor(Color.WHITE);// 设置环形中间的颜色
        pieChardata.setCenterCircleScale(0.5f);// 设置环形的大小级别


        chart_bing.setPieChartData(pieChardata);
        chart_bing.setValueSelectionEnabled(true);// 选择饼图某一块变大
        chart_bing.setAlpha(0.9f);// 设置透明度
        chart_bing.setCircleFillRatio(0.8f);// 设置饼图大小

        pieChardata.setCenterText1(list_result.get(0).getTypePayName());
        pieChardata.setCenterText1FontSize(2);

    }

    /**
     * 监听事件
     */
    private PieChartOnValueSelectListener selectListener = new PieChartOnValueSelectListener() {

        @Override
        public void onValueDeselected() {
            // TODO Auto-generated method stub

        }

        @Override
        public void onValueSelected(int arg0, SliceValue value) {
            // 选择对应图形后，在中间部分显示相应信息
            pieChardata.setCenterText1(list_result.get(arg0).getTypePayName());
            pieChardata.setCenterText1Color(ChartUtils.pickColor());
            pieChardata.setCenterText1FontSize(2);
//			pieChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
            pieChardata.setCenterText2Color(ChartUtils.pickColor());
            pieChardata.setCenterText2FontSize(4);
//			Toast.makeText(ChartActivity.this, stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
        }
    };




}
