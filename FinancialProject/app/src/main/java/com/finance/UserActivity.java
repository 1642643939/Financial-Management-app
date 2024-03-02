package com.finance;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.base.BaseActivity;
import com.finance.db.MemberUserUtils;
import com.finance.model.UserModel;

public class UserActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mName;
	private String state;
	private RelativeLayout mtvUserName;
	private TextView mPhone;
	private TextView mAddress;
	private RelativeLayout mrlPhone;
	private RelativeLayout mrlAddress;
	private RelativeLayout mrlxiane;
	private TextView mIvStu;
	private TextView xianemsg;
	private RelativeLayout mrlpswdmsh;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			UserActivity.this.finish();
			break;
		case R.id.mIvStu:
			Intent mIvStu = new Intent(UserActivity.this, UserMessageUpdateActivity.class);
			startActivity(mIvStu);
			break;
		case R.id.mrlAddress:
			Intent mrlAddress = new Intent(UserActivity.this, UpdatePswdActivity.class);
			startActivity(mrlAddress);
			break;
		case R.id.mrlxiane:
			break;
			
		case R.id.mrlpswdmsh:
			Intent mrlpswdmsh = new Intent(UserActivity.this, CreatePswdMessageActivity.class);
			startActivity(mrlpswdmsh);
			break;
		}
	}

	@Override
	public void initWidget() {
		
		
		
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		xianemsg = (TextView) findViewById(R.id.xianemsg);
		
		mPhone = (TextView) findViewById(R.id.mPhone);
		mAddress = (TextView) findViewById(R.id.mAddress);
		mrlPhone = (RelativeLayout) findViewById(R.id.mrlPhone);
		mrlAddress = (RelativeLayout) findViewById(R.id.mrlAddress);
		
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mName = (TextView) findViewById(R.id.mName);

		mTvTitle.setText("我的资料");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mrlAddress.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
		mIvStu.setText("修改");
		//mIvStu.setVisibility(View.GONE);
		
		mrlxiane = (RelativeLayout) findViewById(R.id.mrlxiane);
		mrlxiane.setOnClickListener(this);
		
		mrlpswdmsh = (RelativeLayout) findViewById(R.id.mrlpswdmsh);
		mrlpswdmsh.setOnClickListener(this);
	}

	@Override
	public void initData() {

		try {
			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
			mName.setText("用户名称： " + MemberUserUtils.getName(this));
			mPhone.setText("手机号码： " + userModel.getUphone());
			
			
			if(MemberUserUtils.getCostMoney(this).equals("")){
				xianemsg.setText("限制金额：暂无限额");	
			}else{
				xianemsg.setText("限制金额："+MemberUserUtils.getCostMoney(this)+"元");
			}
			
	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	
	@Override
	protected void onResume() {
		super.onResume();
		initWidget();
		initData();
	}
}
