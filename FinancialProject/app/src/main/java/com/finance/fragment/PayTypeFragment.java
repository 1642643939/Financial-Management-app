package com.finance.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;

import com.finance.CreateTypeMessageActivity;
import com.finance.R;
import com.finance.TongJiInMessageActivity;
import com.finance.adapter.PayTypeListAdapter;
import com.finance.adapter.TypeInListAdapter;
import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.listener.DeleteTypeListner;
import com.finance.model.IncomeModel;
import com.finance.model.PayModel;
import com.finance.model.PayTypeModel;
import com.finance.model.ResponseEntry;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PayTypeFragment extends BaseFragment{

	// 获取view
	private View rootView;
	// 获取控件
	private ListView mListMessage;
	
	private ImageView mivCreateMessage;
	private ImageView mivTongJi;


	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_news_tiao, null);
		initWidget();
		initData();
		return rootView;
	}

	@Override
	public void initWidget() {
		mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
		
		mivCreateMessage = (ImageView) rootView.findViewById(R.id.mivCreateMessage);
		mivCreateMessage.setOnClickListener(this);
		mivCreateMessage.setVisibility(View.GONE);

		mivTongJi = (ImageView) rootView.findViewById(R.id.mivTongJi);




	}

	@Override
	public void onClick(View v) {
		

	}

	@Override
	public void initData() {
		listIncome(false);
	}

	/**
	 * 支出数据类型
	 * 
	 * @param isShow
	 */
	private void listIncome(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listPayTongJiTu");
		params.put("lookMoneyUserId", MemberUserUtils.getUid(getActivity()));
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
	}


	private List<PayTypeModel> list_result_income = new ArrayList<PayTypeModel>();
	PayTypeListAdapter typeListAdapter;
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				list_result_income = mGson.fromJson(entry.getData(), new TypeToken<List<PayTypeModel>>() {
				}.getType());
				 typeListAdapter = new PayTypeListAdapter(getActivity(), list_result_income);
				mListMessage.setAdapter(typeListAdapter);

			}
			break;

		}

	}


}
