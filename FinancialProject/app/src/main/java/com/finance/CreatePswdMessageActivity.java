package com.finance;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.finance.base.BaseActivity;
import com.finance.db.MemberUserUtils;
import com.finance.util.ToastUtil;

public class CreatePswdMessageActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnAdd;
	private EditText metMoney;
	private RadioGroup mrgChoice;
	private RadioButton mrbIncome = null;
	private RadioButton mrbCost = null;
	private int choiceType = 1;
	private EditText metlimit;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create_pswd);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {


		mrbIncome = (RadioButton) findViewById(R.id.mrbIncome);
		mrbCost = (RadioButton) findViewById(R.id.mrbCost);
		mrgChoice = (RadioGroup) findViewById(R.id.mrgChoice);
		metMoney = (EditText) findViewById(R.id.metMoney);

		mbtnAdd = (Button) findViewById(R.id.mbtnAdd);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("添加类型数据");
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
			CreatePswdMessageActivity.this.finish();
			break;

		case R.id.mbtnAdd:
			
			

			if (TextUtils.isEmpty(metMoney.getText().toString())) {
				ToastUtil.ShowCentre(CreatePswdMessageActivity.this, "请输入密码");
				return;
			}
			MemberUserUtils.setPswdMoney(this, metMoney.getText().toString());
			ToastUtil.show(this, "添加成功");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					finish();
				}
			}, 2000);
			break;
		}
	}

	private int posIndex = 0;

	@Override
	public void initData() {
		// 数据的获取
		mrgChoice.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == mrbIncome.getId()) {
					choiceType = 1;
					MemberUserUtils.setIsOpen(CreatePswdMessageActivity.this, "no");
				} else if (checkedId == mrbCost.getId()) {
					choiceType = 2;
					MemberUserUtils.setIsOpen(CreatePswdMessageActivity.this, "ok");
					
				}
			}
		});
	
	}

	

}
