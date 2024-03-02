package com.finance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.finance.adapter.PractitionersAdapter;
import com.finance.base.BaseActivity;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.db.MoneyObservable;
import com.finance.model.CategoryModel;
import com.finance.model.IncomeModel;
import com.finance.model.PayModel;
import com.finance.model.ResponseEntry;
import com.finance.time.JudgeDate;
import com.finance.time.MyAlertDialog;
import com.finance.time.ScreenInfo;
import com.finance.time.WheelMain;
import com.finance.util.ToastUtil;
import com.finance.view.DialogListMsg;
import com.finance.view.DialogMsg;
import com.google.gson.reflect.TypeToken;

public class UpdateTypeOutMessageActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnAdd;
	private TextView metMoney;
	private int choiceType = 1;
	private EditText metlimit;

	private PayModel payModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_update_type);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {

		metMoney = (TextView) findViewById(R.id.metMoney);

		mbtnAdd = (Button) findViewById(R.id.mbtnAdd);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("修改类型数据");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnAdd.setOnClickListener(this);

		metlimit = (EditText) findViewById(R.id.metlimit);

	}

	private int choiceFlag = 0;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mIvBack:
			UpdateTypeOutMessageActivity.this.finish();
			break;

		case R.id.mbtnAdd:

			if (TextUtils.isEmpty(metMoney.getText().toString())) {
				ToastUtil.ShowCentre(UpdateTypeOutMessageActivity.this, "请输入信息");
				return;
			}

			addIncome(false);

			break;
		}
	}

	private int posIndex = 0;

	@Override
	public void initData() {
		// 数据的获取

		payModel = (PayModel) this.getIntent().getSerializableExtra("msg");
		metMoney.setText(payModel.getTypePayName());
	}

	private void addIncome(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateType");
		params.put("typeFlag", "2");
		params.put("typeName", metMoney.getText().toString());
		params.put("typeId", payModel.getTypePayId());
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultState, isShow, "正在注册...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {

		case Consts.actionId.resultState:
			MoneyObservable.getInstance().notifyStepChange("ok");
			ToastUtil.show(UpdateTypeOutMessageActivity.this, entry.getRepMsg());
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					finish();
				}
			}, 2000);
			break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(UpdateTypeOutMessageActivity.this, strMsg);

	}

}
