package com.finance;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
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
import com.finance.model.CategoryModel;
import com.finance.model.IncomeModel;
import com.finance.model.MoneyModel;
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

public class CreateUpdateMessageActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnAdd;

	private EditText metMoney;
	private EditText metMessage;
	private Button mbtnchoice;
	private Button mbtnDate;
	private String timeMsg;
	private RadioGroup mrgChoice;
	private RadioButton mrbIncome = null;
	private RadioButton mrbCost = null;

	private List<CategoryModel> mlistData = new ArrayList<CategoryModel>();
	private DialogListMsg dialogListMsg;
	private PractitionersAdapter listaAdapter;

	private DialogMsg dialogMsg;
	private int incomeTotalMoney = 0;
	private int costTotalMoney = 0;

	private List<PayModel> list_result_pay = new ArrayList<PayModel>();
	private List<IncomeModel> list_result_income = new ArrayList<IncomeModel>();
	
	private MoneyModel moneyModel;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_create);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {

		dialogMsg = new DialogMsg(this);
		dialogMsg.Set_Msg("您的金额超出了最大额度");
		dialogListMsg = new DialogListMsg(this);
		dialogListMsg.setTitle().setText("请选择类别");
		listaAdapter = new PractitionersAdapter(this);

		mrbIncome = (RadioButton) findViewById(R.id.mrbIncome);
		mrbCost = (RadioButton) findViewById(R.id.mrbCost);
		mrgChoice = (RadioGroup) findViewById(R.id.mrgChoice);
		metMoney = (EditText) findViewById(R.id.metMoney);
		metMessage = (EditText) findViewById(R.id.metMessage);

		metMessage.setText("我的财务管理系统");

		mbtnchoice = (Button) findViewById(R.id.mbtnchoice);
		mbtnDate = (Button) findViewById(R.id.mbtnDate);
		mbtnAdd = (Button) findViewById(R.id.mbtnAdd);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("确认修改");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnAdd.setOnClickListener(this);
		mbtnDate.setOnClickListener(this);
		mbtnchoice.setOnClickListener(this);
		
		
		moneyModel = (MoneyModel) this.getIntent().getSerializableExtra("msg");

		if (moneyModel.getTypeMessage().equals("2")) {
			mbtnchoice.setText("请选择支出类别");
		} else {
			mbtnchoice.setText("请选择收入类别");
		}

	}

	private int choiceFlag = 0;

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mIvBack:
			CreateUpdateMessageActivity.this.finish();
			break;

		case R.id.mbtnDate:
			showTime();
			break;

		case R.id.mbtnchoice:

			if (moneyModel.getTypeMessage().equals("1")) {
				choiceFlag = 1;
				listIncome(false);
			} else {
				choiceFlag = 2;
				listPay(false);

			}

			Log.e("pony_log", mlistData.size() + "");

			break;
		case R.id.mbtnAdd:

			if (TextUtils.isEmpty(metMoney.getText().toString())) {
				ToastUtil.ShowCentre(CreateUpdateMessageActivity.this, "请输入消费金额");
				return;
			}
			createTopicPost(true);
			break;
		}
	}

	private int posIndex = 0;

	@Override
	public void initData() {
		// 数据的获取

		dialogListMsg.show_listview().setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				dialogListMsg.Close();
				posIndex = pos;
				mbtnchoice.setText(mlistData.get(pos).getTypeName());
			}
		});

		dialogMsg.submit_ok().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				dialogMsg.Close();

			}
		});

		dialogMsg.submit_no().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogMsg.Close();
			}
		});

		
		metMoney.setText(moneyModel.getLookMoneyMoney());
		mbtnchoice.setText(moneyModel.getLookMoneyTypeName());
		mbtnDate.setText(moneyModel.getLookMoneyTime());
	}

	/**
	 * 支出数据类型
	 * 
	 * @param isShow
	 */
	private void listIncome(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listIncome");
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
	}

	private void listPay(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listPay");
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
	}

	private void createTopicPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateMoney");
		params.put("lookMoneyTypeName",mbtnchoice.getText().toString());
		params.put("lookMoneyMoney", metMoney.getText().toString());
		params.put("lookMoneyTime", mbtnDate.getText().toString());
		params.put("lookMoneyId",moneyModel.getLookMoneyId());
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultState, isShow, "正在修改...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultCode:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result_income.clear();
					list_result_income = mGson.fromJson(entry.getData(), new TypeToken<List<IncomeModel>>() {
					}.getType());
					mlistData.clear();
					for (int i = 0; i < list_result_income.size(); i++) {
						CategoryModel categoryModel = new CategoryModel();
						categoryModel.setTypeId(list_result_income.get(i).getTypeIncomeId());
						categoryModel.setTypeName(list_result_income.get(i).getTypeIncomeName());
						mlistData.add(categoryModel);
					}

					listaAdapter.setData(mlistData);
					dialogListMsg.show_listview().setAdapter(listaAdapter);
					listaAdapter.notifyDataSetChanged();
					dialogListMsg.Show();
				} else {
				}
			}
			break;

		case Consts.actionId.resultFlag:
			String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
			if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
				list_result_pay.clear();
				list_result_pay = mGson.fromJson(entry.getData(), new TypeToken<List<PayModel>>() {
				}.getType());

				mlistData.clear();

				for (int i = 0; i < list_result_pay.size(); i++) {
					CategoryModel categoryModel = new CategoryModel();
					categoryModel.setTypeId(list_result_pay.get(i).getTypePayId());
					categoryModel.setTypeName(list_result_pay.get(i).getTypePayName());
					mlistData.add(categoryModel);
				}
				listaAdapter.setData(mlistData);
				dialogListMsg.show_listview().setAdapter(listaAdapter);
				listaAdapter.notifyDataSetChanged();
				dialogListMsg.Show();
			} else {
			}

			break;
		case Consts.actionId.resultState:

			ToastUtil.show(CreateUpdateMessageActivity.this, entry.getRepMsg());
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
		ToastUtil.show(CreateUpdateMessageActivity.this, strMsg);

	}

	WheelMain wheelMain;
	DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

	private void showTime() {
		LayoutInflater inflater1 = LayoutInflater.from(this);
		final View timepickerview1 = inflater1.inflate(R.layout.timepicker, null);
		ScreenInfo screenInfo1 = new ScreenInfo(this);
		wheelMain = new WheelMain(timepickerview1);
		wheelMain.screenheight = screenInfo1.getHeight();
		Calendar calendar = Calendar.getInstance();
		String time1 = calendar.get(Calendar.YEAR) + "-" + (calendar.get(Calendar.MONTH) + 1) + "-" + calendar.get(Calendar.DAY_OF_MONTH) + "";
		Calendar calendar1 = Calendar.getInstance();
		if (JudgeDate.isDate(time1, "yyyy-MM-dd")) {
			try {
				calendar1.setTime(dateFormat.parse(time1));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		int year1 = calendar1.get(Calendar.YEAR);
		int month1 = calendar1.get(Calendar.MONTH);
		int day1 = calendar1.get(Calendar.DAY_OF_MONTH);
		wheelMain.initDateTimePicker(year1, month1, day1);

		final MyAlertDialog dialog = new MyAlertDialog(this).builder().setTitle("选择时间")
		// .setMsg("再连续登陆15天，就可变身为QQ达人。退出QQ可能会使你现有记录归零，确定退出？")
		// .setEditText("1111111111111")
				.setView(timepickerview1).setNegativeButton("取消", new OnClickListener() {
					@Override
					public void onClick(View v) {

					}
				});
		dialog.setPositiveButton("保存", new OnClickListener() {
			@Override
			public void onClick(View v) {

				// SimpleDateFormat df = new SimpleDateFormat(" HH:mm:ss");//
				// 设置日期格式
				// timeMsg = wheelMain.getTime() + df.format(new Date());
				timeMsg = wheelMain.getTime();
				mbtnDate.setText(wheelMain.getTime());
			}
		});
		dialog.show();

	}
}
