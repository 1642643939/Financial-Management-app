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
import com.finance.adapter.TypeInListAdapter;
import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.listener.DeleteTypeListner;
import com.finance.model.IncomeModel;
import com.finance.model.PayModel;
import com.finance.model.ResponseEntry;
import com.google.gson.reflect.TypeToken;

import net.tsz.afinal.http.AjaxParams;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class TypeInFragment extends BaseFragment implements DeleteTypeListner {

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
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {
		mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
		
		mivCreateMessage = (ImageView) rootView.findViewById(R.id.mivCreateMessage);
		mivCreateMessage.setOnClickListener(this);
		mivCreateMessage.setVisibility(View.VISIBLE);

		mivTongJi = (ImageView) rootView.findViewById(R.id.mivTongJi);


		mivTongJi.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(getActivity(), TongJiInMessageActivity.class);

				Bundle bundle=new Bundle();
				bundle.putSerializable("msg",(Serializable)list_result_income);//序列化,要注意转化(Serializable)
				intent.putExtras(bundle);//发送数据
				startActivity(intent);//启动intent


//				intent.putExtra("msg",(Serializable) list_result_income);
//				startActivity(intent);
			}
		});

	}

	@Override
	public void onClick(View v) {
		
		Intent intent = new Intent(getActivity(), CreateTypeMessageActivity.class);
		startActivity(intent);
		
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
		params.put("action_flag", "listIncome");
		params.put("userId", MemberUserUtils.getUid(getActivity()));
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
	}


	private void deleteTypeZhiChuMessage(boolean isShow,IncomeModel incomeModel) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "deleteTypeZhiChuMessage");
		params.put("typeIncomeId", incomeModel.getTypeIncomeId());
		httpPost(Consts.URL + Consts.APP.MoneyAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
	}





	private List<IncomeModel> list_result_income = new ArrayList<IncomeModel>();
	TypeInListAdapter typeListAdapter;
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				list_result_income = mGson.fromJson(entry.getData(), new TypeToken<List<IncomeModel>>() {
				}.getType());
				 typeListAdapter = new TypeInListAdapter(getActivity(), list_result_income,this);
				mListMessage.setAdapter(typeListAdapter);

			}
			break;
			case Consts.actionId.resultCode:
				list_result_income.remove(posIndex);
				typeListAdapter.notifyDataSetChanged();
				break;
		}

	}


	@Override
	public void setRemoveOut(int pos, PayModel payModel) {

	}
	private int posIndex;
	@Override
	public void setRemoveIn(int pos, IncomeModel incomeModel) {
		posIndex = pos;
		deleteTypeZhiChuMessage(false,incomeModel);
	}
}
