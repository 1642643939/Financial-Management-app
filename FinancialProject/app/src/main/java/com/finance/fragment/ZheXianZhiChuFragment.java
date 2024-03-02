package com.finance.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.finance.R;
import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.db.MoneyObservable;
import com.finance.model.MoneyModel;
import com.finance.model.ResponseEntry;
import com.finance.model.zxPayModel;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Line;
import lecho.lib.hellocharts.model.LineChartData;
import lecho.lib.hellocharts.model.PointValue;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.model.ValueShape;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.LineChartView;


public class ZheXianZhiChuFragment extends BaseFragment {

    // 获取view
    private View rootView;


    private LineChartView quxianchat;
    private LineChartData data;
    private int numberOfLines = 1;
    private int maxNumberOfLines = 12;
    private int numberOfPoints = 8;
    private boolean hasLines = true;
    private boolean hasPoints = true;
    private ValueShape shape = ValueShape.CIRCLE;
    private boolean isFilled = true;
    private boolean hasLabels = true;
    private boolean isCubic = true;
    private boolean hasLabelForSelected = true;
    List<Line> lines = new ArrayList<Line>();

    List<PointValue> values = new ArrayList<PointValue>();
    List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_quxian, null);
        initWidget();
        initData();
        return rootView;
    }

    @Override
    public void initWidget() {
        quxianchat = (LineChartView) rootView.findViewById(R.id.quxianchat);
    }

    @Override
    public void onClick(View v) {

    }

    private List<zxPayModel> list_result = new ArrayList<zxPayModel>();

    private void listIncome(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "zxlistPay");
        params.put("userId", MemberUserUtils.getUid(getActivity()));
        httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);



        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<zxPayModel>>() {
                        }.getType());


                        for (int i = 0; i < list_result.size(); ++i) {
                            mAxisValues.add(new AxisValue(i, list_result.get(i).getLookMoneyTime().toCharArray()));
                            values.add(new PointValue(i, Float.valueOf(list_result.get(i).getMoney())));
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

        Line line = new Line(values);
        line.setColor(Color.parseColor("#ff0000"));
        line.setShape(shape);
        line.setCubic(isCubic);
        line.setFilled(isFilled);
        line.setHasLabels(hasLabels);
        line.setHasLabelsOnlyForSelected(hasLabelForSelected);
        line.setHasLines(hasLines);
        line.setHasPoints(hasPoints);
        lines.add(line);

        data = new LineChartData(lines);
        Axis axisX = new Axis();
        Axis axisY = new Axis();

        axisX.setName("Axis X");
        // 让文字倾斜
        // axisX.setHasTiltedLabels(true);
        axisX.setName("日期");
        axisX.setMaxLabelChars(1);
        axisX.setValues(mAxisValues);
        data.setAxisXBottom(axisX);

        axisY.setName("金额");
        axisX.setValues(mAxisValues);
        data.setAxisYLeft(axisY);

        // data.setBaseValue(Float.NEGATIVE_INFINITY);
        quxianchat.cancelDataAnimation();
        quxianchat.setLineChartData(data);
        quxianchat.startDataAnimation(3000);

    }



}
