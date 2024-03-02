package com.finance;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.base.BaseActivity;
import com.finance.config.Consts;
import com.finance.model.ResponseEntry;
import com.finance.util.ToastUtil;

/**
 * 用户注册
 */
public class RegisterActivity extends BaseActivity {
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private Button mSubmit;

	private EditText metName;
	private EditText metPhone;
	private EditText metPswd;

	private int choiceType = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;

		case R.id.mSubmit:

			if (TextUtils.isEmpty(metName.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入用户名");
				return;
			}

			if (TextUtils.isEmpty(metPhone.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入手机号码");
				return;
			}

			
			if (!isMobileNO(metPhone.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入正确的手机号码");
				return;
			}
			
			
			if (TextUtils.isEmpty(metPswd.getText().toString())) {
				ToastUtil.ShowCentre(this, "请输入登录密码");
				return;
			}
			
			if (metPswd.getText().toString().length()<8) {
				ToastUtil.ShowCentre(this, "密码长度不能少于8位");
				return;
			}
			createTopicPost(true);
			break;

		}
	}

	@Override
	public void initWidget() {

		metName = (EditText) findViewById(R.id.metName);
		metPhone = (EditText) findViewById(R.id.metPhone);
		metPswd = (EditText) findViewById(R.id.metPswd);

		mSubmit = (Button) findViewById(R.id.mSubmit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("注册");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mSubmit.setOnClickListener(this);

	}

	@Override
	public void initData() {
	}

	private void createTopicPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addUser");
		params.put("uname", metName.getText().toString());
		params.put("upswd", metPswd.getText().toString());
		params.put("uphone", metPhone.getText().toString());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(RegisterActivity.this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				finish();
			}
		}, 2000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(RegisterActivity.this, strMsg);

	}

	public static boolean isMobileNO(String mobiles) {
		String telRegex = "[1][3587]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles)) {
			return false;
		} else
			return mobiles.matches(telRegex);
	}
}
