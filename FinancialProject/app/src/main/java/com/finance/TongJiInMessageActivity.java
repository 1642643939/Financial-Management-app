package com.finance;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.base.BaseActivity;
import com.finance.model.IncomeModel;

import java.util.ArrayList;
import java.util.List;

import lecho.lib.hellocharts.model.Axis;
import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.model.Column;
import lecho.lib.hellocharts.model.ColumnChartData;
import lecho.lib.hellocharts.model.SubcolumnValue;
import lecho.lib.hellocharts.util.ChartUtils;
import lecho.lib.hellocharts.view.ColumnChartView;

public class TongJiInMessageActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	List<IncomeModel> list_result;

	ColumnChartView chart_tiao;
	private ColumnChartData data;
	private boolean hasLabels = true;
	private boolean hasLabelForSelected = false;
	List<AxisValue> mAxisValues = new ArrayList<AxisValue>();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tongji_message);
		initWidget();
		initData();
	}
	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mExit:

			break;
			
		}
	}

	@Override
	public void initWidget() {


		chart_tiao = (ColumnChartView) findViewById(R.id.chart_tiao);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);


		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mTvTitle.setText("统计信息");

	}

	

	@Override
	public void initData() {
		list_result = (List<IncomeModel>)this.getIntent().getSerializableExtra("msg");


		for (int i = 0; i < list_result.size(); i++) {

			Log.i("pony_log",list_result.get(i).getTypeMoney());
			mAxisValues.add(new AxisValue(i, list_result.get(i).getTypeIncomeName().toCharArray()));
		}
//
		List<Column> columns = new ArrayList<Column>();
		Column column = new Column();
		column.setHasLabels(hasLabels);
		column.setHasLabelsOnlyForSelected(hasLabelForSelected);
		columns.add(column);

		data = new ColumnChartData(generateDefaultData());

		// 坐标轴
		Axis axisX = new Axis(); // X轴
		axisX.setName("消费类别");
		axisX.setTextColor(Color.BLACK);
		axisX.setValues(mAxisValues);
		data.setAxisXBottom(axisX);

		Axis axisY = new Axis(); // Y轴
		axisY.setName("消费值");
		axisY.setTextColor(Color.BLACK);
		// axisY.setValues(mAxisValues);
		data.setAxisYLeft(axisY);

		chart_tiao.setColumnChartData(data);

	}

	private List<Column> generateDefaultData() {



		List<Column> columns = new ArrayList<Column>();
		List<SubcolumnValue> values;
		for (int i = 0; i < list_result.size(); ++i) {
			values = new ArrayList<SubcolumnValue>();
//			if (i == 0) {
//				sliceValue = new SubcolumnValue((float) ShouRuMoney, colorData[i]);
//			} else {
//				sliceValue = new SubcolumnValue((float) ZhiChuMoney, colorData[i]);
//			}


			SubcolumnValue sliceValue = new SubcolumnValue(Float.valueOf(list_result.get(i).getTypeMoney()), ChartUtils.pickColor());// 第一个值是数值(值>0
			values.add(sliceValue);
			Column column = new Column(values);// 一个柱状图的实例
			column.setHasLabels(hasLabels);// 设置是否显示每个柱状图的高度，
			column.setHasLabelsOnlyForSelected(hasLabelForSelected);// 点击的时候是否显示柱状图的高度，和setHasLabels()和用的时候，setHasLabels()失效
			columns.add(column);
		}
		return columns;
	}

}
