package com.finance;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.base.BaseActivity;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.model.ResponseEntry;
import com.finance.model.UserModel;
import com.finance.util.LoadingDialog;
import com.finance.util.ToastUtil;

public class LoginActivity extends BaseActivity {

	// 登录用户名称
	private EditText mLoginNumber;
	// 登录密码
	private EditText mLoginPswd;
	// 登录按钮
	private Button mLogin;
	private Button mEnterpriseQuery;
	private LinearLayout mllTop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_loginnew);
		initWidget();
	}

	/**
	 * 控件初始化
	 */
	@Override
	public void initWidget() {

		mdialog = new LoadingDialog(this, "正在登录");
		mLoginNumber = (EditText) findViewById(R.id.mLoginNumber);
		mLoginPswd = (EditText) findViewById(R.id.mLoginPswd);
		mLogin = (Button) findViewById(R.id.mLogin);
		mEnterpriseQuery = (Button) findViewById(R.id.mEnterpriseQuery);
		// mLoginNumber.setInputType(EditorInfo.TYPE_CLASS_PHONE);
		// 事件的监听
		mLogin.setOnClickListener(this);
		mEnterpriseQuery.setOnClickListener(this);
		// 给输入框设置默认的测试数据
		mLoginNumber.setSelection(mLoginNumber.getText().length());
		// mLoginNumber.setText("TEA20170123164556");
		mLoginNumber.setText("15512345678");
		mLoginPswd.setText("12345678");
	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mLogin:
			if (TextUtils.isEmpty(mLoginNumber.getText().toString())) {
				ToastUtil.ShowCentre(LoginActivity.this, "请输入手机号码");
				return;
			}
			if (TextUtils.isEmpty(mLoginPswd.getText().toString())) {
				ToastUtil.ShowCentre(LoginActivity.this, "请输入登录密码");
				return;
			}

			//
			LoginUserPost(true);
			//
			break;
		case R.id.mEnterpriseQuery:
			Intent mEnterpriseQuery = new Intent(LoginActivity.this, RegisterActivity.class);
			startActivity(mEnterpriseQuery);
		default:
			break;
		}
	}

	@Override
	public void initData() {
	}

	/**
	 * 用户的登录
	 * 
	 * @param isShow
	 */
	private void LoginUserPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "login");
		params.put("uphone", mLoginNumber.getText().toString());
		params.put("pswd", mLoginPswd.getText().toString());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在登录...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:

			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
				MemberUserUtils.setLoginFlag(this, "true");
				UserModel userModel = mGson.fromJson(entry.getData(), UserModel.class);
				MemberUserUtils.setUid(LoginActivity.this, userModel.getUid());
				MemberUserUtils.setName(LoginActivity.this, userModel.getUname());
				MemberUserUtils.putBean(LoginActivity.this, "user_messgae", userModel);
				Intent intent = new Intent(LoginActivity.this, FrameworkActivity.class);
				startActivity(intent);
				finish();

			}
			break;

		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);

		ToastUtil.show(LoginActivity.this, strMsg);

	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		Log.i("pony_log", MemberUserUtils.getIsOpen(this));

		if (MemberUserUtils.getIsOpen(this).equals("ok")) {
			
			if(!MemberUserUtils.getPswdLinShi(this).equals("ok")){
				Intent mrlAddress = new Intent(this, PswdMessageActivity.class);
				this.startActivity(mrlAddress);
			}
		
		}
	}
}
