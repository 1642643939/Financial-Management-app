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

public class PswdMessageActivity extends BaseActivity {

	private Button mLogin;
	private EditText mLoginPswd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginpswd);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {


		mLoginPswd = (EditText) findViewById(R.id.mLoginPswd);

		mLogin = (Button) findViewById(R.id.mLogin);
		mLogin.setOnClickListener(this);
		


	}



	@Override
	public void onClick(View v) {

		switch (v.getId()) {


		case R.id.mLogin:
			
			if (!MemberUserUtils.getPswdMoney(this).equals(mLoginPswd.getText().toString())) {
				ToastUtil.ShowCentre(PswdMessageActivity.this, "请输入确定启动密码");
				return;
			}
			MemberUserUtils.setPswdLinShi(this, "ok");
			ToastUtil.show(this, "输入正确");
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
	public void initData() {
		// TODO Auto-generated method stub
		
	}

	

	

}
