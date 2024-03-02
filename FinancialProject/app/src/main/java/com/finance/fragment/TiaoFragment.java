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
import com.finance.db.MoneyObservable;
import com.finance.model.MoneyModel;
import com.finance.model.ResponseEntry;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.view.ColumnChartView;


public class TiaoFragment extends BaseFragment implements Observer {

	// 获取view
	private View rootView;
	ColumnChartView chart_tiao;
	private ColumnChartData data;
	private boolean hasLabels = true;
	private boolean hasLabelForSelected = false;
	private TextView ye;
	List<AxisValue> mAxisValues = new ArrayList<AxisValue>();

	// private int[] data = { 70,30 };
	private int[] colorData = { Color.parseColor("#458b74"), Color.parseColor("#ff0000") };
	private String[] typeMsg = { "总收入", "总支出" };

	private List<MoneyModel> list_result = new ArrayList<MoneyModel>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tiao, null);
		initWidget();
		initData();
		return rootView;
	}

	@Override
	public void initWidget() {
		chart_tiao = (ColumnChartView) rootView.findViewById(R.id.chart_tiao);
		ye=(TextView) rootView.findViewById(R.id.tv_balance);

	}

	@Override
	public void onClick(View v) {

	}

	@Override
	public void initData() {
		listMoney(false);


	}

	private void listMoney(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listAllUserMoney");
		params.put("lookMoneyUserId", MemberUserUtils.getUid(getActivity()));
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
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


						// for (int i = 0; i < list_result.size(); i++) {
						// if (list_result.get(i).getTypeMessage().equals("1")) {
						// ShouRuMoney = Double.valueOf(ShouRuMoney) +
						// Double.valueOf(list_result.get(i).getLookMoneyMoney());
						// } else {
						// ZhiChuMoney = Double.valueOf(ZhiChuMoney) +
						// Double.valueOf(list_result.get(i).getLookMoneyMoney());
						// }
						// }

						for (int i = 0; i < typeMsg.length; i++) {
							mAxisValues.add(new AxisValue(i, typeMsg[i].toCharArray()));
						}

						List<Column> columns = new ArrayList<Column>();
						Column column = new Column();
						column.setHasLabels(hasLabels);
						column.setHasLabelsOnlyForSelected(hasLabelForSelected);
						columns.add(column);

						data = new ColumnChartData(generateDefaultData());

						// 坐标轴
						Axis axisX = new Axis(); // X轴
						axisX.setName("消费记录");
						axisX.setTextColor(Color.BLACK);
						axisX.setValues(mAxisValues);
						data.setAxisXBottom(axisX);

						Axis axisY = new Axis(); // Y轴
						axisY.setName("消费值");
						axisY.setTextColor(Color.BLACK);
						// axisY.setValues(mAxisValues);
						data.setAxisYLeft(axisY);

						chart_tiao.setColumnChartData(data);

					} else {
					}
				}
				break;

		}

	}

	private List<Column> generateDefaultData() {

		int ShouRuMoney = 0;
		int ZhiChuMoney = 0;

		for (int i = 0; i < list_result.size(); i++) {
			if (list_result.get(i).getTypeMessage().equals("1")) {
				ShouRuMoney = Integer.valueOf(ShouRuMoney) + Integer.valueOf(list_result.get(i).getLookMoneyMoney());
			} else {
				ZhiChuMoney = Integer.valueOf(ZhiChuMoney) + Integer.valueOf(list_result.get(i).getLookMoneyMoney());
			}
		}

		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < colorData.length; ++i) {
			values = new ArrayList<SubcolumnValue>();
			SubcolumnValue sliceValue;
			if (i == 0) {
				sliceValue = new SubcolumnValue((float) ShouRuMoney, colorData[i]);
			} else {
				sliceValue = new SubcolumnValue((float) ZhiChuMoney, colorData[i]);
			}
			values.add(sliceValue);
			Column column = new Column(values);// 一个柱状图的实例
			column.setHasLabels(hasLabels);// 设置是否显示每个柱状图的高度，
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);// 点击的时候是否显示柱状图的高度，和setHasLabels()和用的时候，setHasLabels()失效
			columns.add(column);
			ye.setText((ShouRuMoney-ZhiChuMoney)+"元");
		}
		return columns;
	}

	@Override
	public void onResume() {
		super.onResume();
		listMoney(false);

		MoneyObservable.getInstance().addObserver(TiaoFragment.this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		MoneyObservable.getInstance().deleteObserver(TiaoFragment.this);
	}

	@Override
	public void update(Observable arg0, Object object) {
		listMoney(false);
	}

}
