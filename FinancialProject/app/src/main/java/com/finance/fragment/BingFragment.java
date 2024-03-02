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

import lecho.lib.hellocharts.listener.PieChartOnValueSelectListener;
import lecho.lib.hellocharts.model.PieChartData;
import lecho.lib.hellocharts.model.SliceValue;
import lecho.lib.hellocharts.view.PieChartView;


public class BingFragment extends BaseFragment implements Observer{

	// 获取view
	private View rootView;
	PieChartView  chart_bing;
	private TextView mtvMoney;
	private TextView ye;

	// 数据
	private PieChartData pieChardata;
	List<SliceValue> values = new ArrayList<SliceValue>();
	// 定义数据，实际情况肯定不是这样写固定值的
	
	private int[] colorData = { Color.parseColor("#458b74"), Color.parseColor("#ff0000") };
	private String[] stateChar = { "总收入", "总支出" };
	private double[] stateData =new double [2];
	private List<MoneyModel> list_result = new ArrayList<MoneyModel>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_bing, null);
		mtvMoney=(TextView) rootView.findViewById(R.id.mtvMoney);

		initWidget();
		initData();

		return rootView;
	}

	@Override
	public void initWidget() {
		chart_bing = (PieChartView) rootView.findViewById(R.id.chart_bing);
		ye=(TextView) rootView.findViewById(R.id.tv_balance);

		chart_bing.setOnValueTouchListener(selectListener);// 设置点击事件监听
	

	}

	@Override
	public void onClick(View v) {

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
				// double ShouRuMoney = 0;
				// double ZhiChuMoney = 0;
				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result.clear();
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<MoneyModel>>() {
					}.getType());
					
					
					double ShouRuMoney = 0;
					double ZhiChuMoney = 0;

					for (int i = 0; i < list_result.size(); i++) {
						if (list_result.get(i).getTypeMessage().equals("1")) {
							ShouRuMoney = Double.valueOf(ShouRuMoney) + Double.valueOf(list_result.get(i).getLookMoneyMoney());
						} else {
							ZhiChuMoney = Double.valueOf(ZhiChuMoney) + Double.valueOf(list_result.get(i).getLookMoneyMoney());
						}
					}

//					mtvMoney.setText(z+"元(余额)");
					
					stateData[0] = ShouRuMoney;
					stateData[1] = ZhiChuMoney;
					values.clear();
					for (int i = 0; i < stateChar.length; ++i) {
						SliceValue sliceValue;
						if (i == 0) {
							sliceValue = new SliceValue((float) ShouRuMoney, colorData[i]);
						} else {
							sliceValue = new SliceValue((float) ZhiChuMoney, colorData[i]);
						}
//						SliceValue sliceValue = new SliceValue((float) data[i], colorData[i]);
						values.add(sliceValue);
						double z=ShouRuMoney-ZhiChuMoney;
						ye.setText((int) z+"元");
					}
					
					initPieChart();

				} else {
				}
			}
			break;

		}

	}

	@Override
	public void initData() {
		
		listMoney(false);
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

		pieChardata.setCenterText1("总收入");


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
			pieChardata.setCenterText1(stateChar[arg0]);
			pieChardata.setCenterText1Color(colorData[arg0]);
			pieChardata.setCenterText1FontSize(6);
//			pieChardata.setCenterText2(value.getValue() + "（" + calPercent(arg0) + ")");
			pieChardata.setCenterText2Color(colorData[arg0]);
			pieChardata.setCenterText2FontSize(8);
//			Toast.makeText(ChartActivity.this, stateChar[arg0] + ":" + value.getValue(), Toast.LENGTH_SHORT).show();
		}
	};

	private String calPercent(int i) {
		String result = "";
		int sum = 0;
		for (int i1 = 0; i1 < stateChar.length; i1++) {
			sum += stateData[i1];
		}
		result = String.format("%.2f", (float) stateData[i] * 100 / sum) + "%";
		return result;
	}
	
	
	@Override
	public void onResume() {
		super.onResume();
		MoneyObservable.getInstance().addObserver(BingFragment.this);
		listMoney(false);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		MoneyObservable.getInstance().deleteObserver(BingFragment.this);
	}

	@Override
	public void update(Observable arg0, Object object) {
		listMoney(false);
	}



}

