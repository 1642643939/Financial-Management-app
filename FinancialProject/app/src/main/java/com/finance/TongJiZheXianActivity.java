package com.finance;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

public class TongJiZheXianActivity extends FragmentActivity implements OnClickListener {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	/** onCreate回调方法 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_zhexiantongji_detail);
		setWindowStatus();
		initWidget();
		initData();
	}

	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			TongJiZheXianActivity.this.finish();
			break;

		}
	}

	public void initWidget() {

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("折线图统计");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);

	}

	public void initData() {
		FragmentManager mFragmentManager = this.getSupportFragmentManager();
		FragmentTransaction mFragmentTransaction = mFragmentManager.beginTransaction();
		Fragment searchFragment = mFragmentManager.findFragmentById(R.id.fragment_GuideStoryDetailFragment);
		mFragmentTransaction.show(searchFragment);
		mFragmentTransaction.commitAllowingStateLoss();
	}

	// 设置状态栏
	private void setWindowStatus() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 透明状态栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
			// 设置状态栏颜色
			getWindow().setBackgroundDrawableResource(R.color.main_color);
		}
	}
}
