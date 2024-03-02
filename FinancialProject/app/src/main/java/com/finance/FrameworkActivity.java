package com.finance;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.db.MemberUserUtils;
import com.finance.fragment.MainIndexFragment;
import com.finance.fragment.MainMessageFragment;
import com.finance.fragment.MeFragment;
import com.finance.fragment.TotalMoneyFragment;
import com.finance.view.DialogTipMsg;

import java.text.SimpleDateFormat;
import java.util.Date;

public class FrameworkActivity extends FragmentActivity implements OnClickListener {

	/**
	 * 用于展示消息的Fragment
	 */
	private MainIndexFragment messageFragment;
	private TotalMoneyFragment totalMoneyFragment;

	private MainMessageFragment incomeFragment;

	/**
	 * 用于展示联系人的Fragment
	 */
	private MeFragment imFragment;

	private long mExitTime;
	private static final int INTERVAL = 2000;
	Intent intent;

	/**
	 * 用于对Fragment进行管理
	 */
	private FragmentManager fragmentManager;

	private DialogTipMsg dialogMsg;

	/**
	 * 消息界面布局
	 */
	private View messageLayout1;
	private View messageLayout2;
	private View messageLayout3;
	private View messageLayout4;
	private View messageLayout5;

	/**
	 * 在Tab布局上显示消息图标的控件
	 */
	private ImageView messageImage1;
	private ImageView messageImage2;
	private ImageView messageImage3;
	private ImageView messageImage4;
	private ImageView messageImage5;

	/**
	 * 在Tab布局上显示消息标题的控件
	 */
	private TextView messageText1;
	private TextView messageText2;
	private TextView messageText3;
	private TextView messageText4;
	private TextView messageText5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_framework);

		setWindowStatus();
		// 初始化布局元素
		initViews();
		findViewById(R.id.iv_menu).setOnClickListener(clickListener);
		// getSupportFragmentManager().beginTransaction()
		fragmentManager = getSupportFragmentManager();
		// 第一次启动时选中第0个tab
		setTabSelection(0);
	}

	private OnClickListener clickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.iv_menu:// 打开左边抽屉

				break;
			}
		}
	};

	/**
	 * 在这里获取到每个需要用到的控件的实例，并给它们设置好必要的点击事件。
	 */
	private void initViews() {

		messageLayout1 = findViewById(R.id.message_1);
		messageLayout2 = findViewById(R.id.message_2);
		messageLayout3 = findViewById(R.id.message_3);
		messageLayout4 = findViewById(R.id.message_4);
		messageLayout5 = findViewById(R.id.message_5);

		messageImage1 = (ImageView) findViewById(R.id.message_image_1);
		messageImage2 = (ImageView) findViewById(R.id.message_image_2);
		messageImage3 = (ImageView) findViewById(R.id.message_image_3);
		messageImage4 = (ImageView) findViewById(R.id.message_image_4);
		messageImage5 = (ImageView) findViewById(R.id.message_image_5);

		messageText1 = (TextView) findViewById(R.id.message_text_1);
		messageText2 = (TextView) findViewById(R.id.message_text_2);
		messageText3 = (TextView) findViewById(R.id.message_text_3);
		messageText4 = (TextView) findViewById(R.id.message_text_4);
		messageText5 = (TextView) findViewById(R.id.message_text_5);

		messageLayout1.setOnClickListener(this);
		messageLayout2.setOnClickListener(this);
		messageLayout3.setOnClickListener(this);
		messageLayout4.setOnClickListener(this);
		messageLayout5.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_1:
			setTabSelection(0);
			break;
		case R.id.message_2:
			setTabSelection(1);
			break;
		case R.id.message_3:
			setTabSelection(2);
			break;
		case R.id.message_4:
			setTabSelection(3);
			break;
		case R.id.message_5:
			setTabSelection(4);
			break;

		default:
			break;
		}
	}

	/**
	 * 根据传入的index参数来设置选中的tab页。
	 * 
	 * @param index
	 *            每个tab页对应的下标。0表示消息，1表示联系人，2表示动态，3表示设置。
	 */
	private void setTabSelection(int index) {
		// 每次选中之前先清楚掉上次的选中状态
		clearSelection();
		// 开启一个Fragment事务
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		// 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况
		hideFragments(transaction);
		switch (index) {
		case 0:
			// 当点击了消息tab时，改变控件的图片和文字颜色
			//messageImage1.setImageResource(R.drawable.scenic_true);
			messageText1.setTextColor(Color.parseColor("#458B74"));
			if (messageFragment == null) {
				// 如果MessageFragment为空，则创建一个并添加到界面上
				messageFragment = new MainIndexFragment();
				transaction.add(R.id.content, messageFragment);
			} else {
				// 如果MessageFragment不为空，则直接将它显示出来
				transaction.show(messageFragment);
			}
			break;

		case 1:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			// messageImage2.setImageResource(R.drawable.scenic_true);
			messageText2.setTextColor(Color.parseColor("#458B74"));
			if (incomeFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				incomeFragment = new MainMessageFragment();
				transaction.add(R.id.content, incomeFragment);
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(incomeFragment);
			}
			break;

		case 2:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			//messageImage3.setImageResource(R.drawable.scenic_true);
			messageText3.setTextColor(Color.parseColor("#458B74"));
			// if (spendFragment == null) {
			// // 如果ContactsFragment为空，则创建一个并添加到界面上
			// spendFragment = new SpendFragment();
			// transaction.add(R.id.content, spendFragment);
			// } else {
			// // 如果ContactsFragment不为空，则直接将它显示出来
			// transaction.show(spendFragment);
			// }
			break;

		case 3:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			//messageImage4.setImageResource(R.drawable.scenic_true);
			messageText4.setTextColor(Color.parseColor("#458B74"));
			 if (totalMoneyFragment == null) {
			// // 如果ContactsFragment为空，则创建一个并添加到界面上
			 totalMoneyFragment = new TotalMoneyFragment();
			 transaction.add(R.id.content, totalMoneyFragment);
			 } else {
			// // 如果ContactsFragment不为空，则直接将它显示出来
			 transaction.show(totalMoneyFragment);
			 }
			break;

		case 4:
			// 当点击了联系人tab时，改变控件的图片和文字颜色
			// messageImage5.setImageResource(R.drawable.me_true);
			messageText5.setTextColor(Color.parseColor("#458B74"));
			if (imFragment == null) {
				// 如果ContactsFragment为空，则创建一个并添加到界面上
				imFragment = new MeFragment();
				transaction.add(R.id.content, imFragment);
			} else {
				// 如果ContactsFragment不为空，则直接将它显示出来
				transaction.show(imFragment);
			}
			break;
		}
		transaction.commit();
	}

	/**
	 * 清除掉所有的选中状态。
	 */
	private void clearSelection() {
		messageImage1.setImageResource(R.drawable.scenic_false);
		messageText1.setTextColor(Color.parseColor("#82858b"));

		messageImage2.setImageResource(R.drawable.scenic_false);
		messageText2.setTextColor(Color.parseColor("#82858b"));

		messageImage3.setImageResource(R.drawable.scenic_false);
		messageText3.setTextColor(Color.parseColor("#82858b"));

		messageImage4.setImageResource(R.drawable.scenic_false);
		messageText4.setTextColor(Color.parseColor("#82858b"));

		messageImage5.setImageResource(R.drawable.me_false);
		messageText5.setTextColor(Color.parseColor("#82858b"));

	}

	/**
	 * 将所有的Fragment都置为隐藏状态。
	 * 
	 * @param transaction
	 *            用于对Fragment执行操作的事务
	 */
	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}

		if (incomeFragment != null) {
			transaction.hide(incomeFragment);
		}

		if (totalMoneyFragment != null) {
			transaction.hide(totalMoneyFragment);
		}

		if (imFragment != null) {
			transaction.hide(imFragment);
		}

	}

	private void checkFlag() {
		dialogMsg = new DialogTipMsg(FrameworkActivity.this);
		dialogMsg.submit_ok().setText("去记账");

		SimpleDateFormat sdfShow = new SimpleDateFormat("MM月dd日");
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		String tim = sdf.format(new Date());
		String timShow = sdfShow.format(new Date());
		if (MemberUserUtils.getCheckDateFlag(FrameworkActivity.this).equals("") && MemberUserUtils.getCheckOrFlag(FrameworkActivity.this).equals("")) {
			dialogMsg.Set_Msg(timShow + "还没有记账哦!");
			dialogMsg.Show();
			Log.i("pony_log", "okle");
		} else {
			if (!MemberUserUtils.getCheckDateFlag(FrameworkActivity.this).equals(tim)) {
				Log.i("pony_log", MemberUserUtils.getCheckDateFlag(FrameworkActivity.this));
				Log.i("pony_log", "tim" + tim);
				dialogMsg.Set_Msg(timShow + "还没有记账哦!");
				dialogMsg.Show();
			}
		}

		dialogMsg.submit_ok().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogMsg.Close();

				Intent intent = new Intent(FrameworkActivity.this, CreateMessageActivity.class);
				startActivity(intent);
			}
		});
		dialogMsg.submit_no().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogMsg.Close();
			}
		});
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

	public void onBackPressed() {
		if (System.currentTimeMillis() - mExitTime > INTERVAL) {
			Toast.makeText(this, "再点一次退出程序", Toast.LENGTH_SHORT).show();
			mExitTime = System.currentTimeMillis();
		} else {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			this.startActivity(intent);
			System.exit(0);
		}
	}

	DialogTipMsg dialogTipMsg;
	SimpleDateFormat df;
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

		df= new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
//
//
//		dialogTipMsg = new DialogTipMsg(this);
//		dialogTipMsg.Set_Msg("记得记录今天的消费哦！");
//		dialogTipMsg.submit_ok().setText("知道了");
//		dialogTipMsg.submit_ok().setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View arg0) {
//				dialogTipMsg.Close();
//				MemberUserUtils.setTime(FrameworkActivity.this, df.format(new Date()));
//				MemberUserUtils.setisRead(FrameworkActivity.this,"ok");
//			}
//		});
//
//
//		Log.i("pony_log", "---------"+MemberUserUtils.getTime(this));
//		Log.i("pony_log",  "---------"+MemberUserUtils.getisRead(this));
//		if (MemberUserUtils.getTime(this).equals(df.format(new Date()))) {
//
//			if(MemberUserUtils.getisRead(this).equals("no")){
//				dialogTipMsg.Show();
//			}
//
//		}else{
//			MemberUserUtils.setTime(FrameworkActivity.this, df.format(new Date()));
//			MemberUserUtils.setisRead(FrameworkActivity.this,"ok");
//		}
//		MemberUserUtils.setisRead(FrameworkActivity.this,"no");
		// checkFlag();
	}
}