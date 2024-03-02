package com.finance.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.finance.CreateMessageActivity;
import com.finance.R;
import com.finance.UpdateMessageActivity;
import com.finance.adapter.MoneyAdapter;
import com.finance.adapter.MoneyTongJiAdapter;
import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.db.MoneyRecordObservable;
import com.finance.listener.RemoveListner;
import com.finance.model.MoneyModel;
import com.finance.model.MoneyTongJiModel;
import com.finance.model.ResponseEntry;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class TJMoneyShouRuFragment extends BaseFragment implements RemoveListner,Observer{
	// 获取view
	private View rootView;
	private ImageView mivCreateMessage;
	// 获取控件
	private ListView mListMessage;
	private LinearLayout mllNomessage;
	private List<MoneyModel> list_result = new ArrayList<MoneyModel>();
	private List<MoneyTongJiModel> list_resultTongJi = new ArrayList<MoneyTongJiModel>();


	private RadioGroup mrgChoiceTime;
	private RadioButton mrbTime1 = null;
	private RadioButton mrbTime2 = null;
	private RadioButton mrbTime3 = null;
	private int choiceTime = 1;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_tongji, null);
		initWidget();
		initData();
		return rootView;
	}

	@Override
	public void initWidget() {

		mrbTime1 = (RadioButton) rootView.findViewById(R.id.mrbTime1);
		mrbTime2 = (RadioButton) rootView.findViewById(R.id.mrbTime2);
		mrbTime3 = (RadioButton) rootView.findViewById(R.id.mrbTime3);
		mrgChoiceTime = (RadioGroup) rootView.findViewById(R.id.mrgChoiceTime);

		
		mllNomessage = (LinearLayout) rootView.findViewById(R.id.mllNomessage);
		mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);

	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent(getActivity(), CreateMessageActivity.class);
		startActivity(intent);
	}

	@Override
	public void initData() {

		listAllTongJiMoney(false);

		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

			}
		});


		mrgChoiceTime.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == mrbTime1.getId()) {
					choiceTime = 1;
					listAllTongJiMoney(false);
					//listMoney(false);
				} else if (checkedId == mrbTime2.getId()) {
					choiceTime = 2;
					listAllTongJiMoney(false);
				}else if (checkedId == mrbTime3.getId()) {
					choiceTime = 3;
					listAllTongJiMoney(false);
				}
			}
		});


	}



	private void listAllTongJiMoney(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listAllTongJiMoney");
		params.put("lookMoneyUserId", MemberUserUtils.getUid(getActivity()));
		params.put("typeMessage", "1"+"");
		params.put("queryTime", choiceTime+"");

		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultState, isShow, "正在注册...");
	}



	private void listMoney(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listPhoneMoneyAll");
		params.put("lookMoneyUserId", MemberUserUtils.getUid(getActivity()));
		params.put("typeMessage", "1"+"");
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
	}
	
	
	private void deleteMoney(boolean isShow,MoneyModel moneyModel) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "deleteMoney");
		params.put("lookMoneyId", moneyModel.getLookMoneyId());
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
	}
	
	
	
	

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultState:
				if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

					String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
					if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
						list_resultTongJi.clear();
						list_resultTongJi = mGson.fromJson(entry.getData(), new TypeToken<List<MoneyTongJiModel>>() {
						}.getType());
						MoneyTongJiAdapter	moneyTongJiAdapter = new MoneyTongJiAdapter(getActivity(), list_resultTongJi, TJMoneyShouRuFragment.this);
						mListMessage.setAdapter(moneyTongJiAdapter);
						mllNomessage.setVisibility(View.GONE);
					} else {
						mllNomessage.setVisibility(View.VISIBLE);
					}
				}
				break;
		case Consts.actionId.resultCode:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result.clear();
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<MoneyModel>>() {
					}.getType());
					MoneyAdapter	 courseListAdapter = new MoneyAdapter(getActivity(), list_result, TJMoneyShouRuFragment.this);
					mListMessage.setAdapter(courseListAdapter);
					mllNomessage.setVisibility(View.GONE);
				} else {
					mllNomessage.setVisibility(View.VISIBLE);
				}
			}
			break;
		case Consts.actionId.resultFlag:
			list_result.remove(posIndex);
//			courseListAdapter.notifyDataSetChanged();
			break;
		}

	}

	private int posIndex;
	@Override
	public void setRemove(int pos, MoneyModel messageModel) {
		posIndex = pos;
		deleteMoney(false, messageModel);
	}
	
	
	@Override
	public void setUpdate(int pos, MoneyModel messageModel) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(getActivity(), UpdateMessageActivity.class);
		intent.putExtra("model",messageModel);
		intent.putExtra("msg", "1");
		startActivity(intent);
	}
	
	@Override
	public void onResume() {
		super.onResume();
		MoneyRecordObservable.getInstance().addObserver(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		MoneyRecordObservable.getInstance().deleteObserver(this);
	}

	@Override
	public void update(Observable arg0, Object object) {
		listMoney(false);
	}



}
