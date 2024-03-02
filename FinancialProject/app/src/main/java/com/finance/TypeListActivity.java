package com.finance;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.finance.adapter.FriendListAdapter;
import com.finance.base.BaseActivity;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.listener.RecommendListner;
import com.finance.model.FriendModel;
import com.finance.model.ResponseEntry;
import com.finance.model.UserModel;
import com.google.gson.reflect.TypeToken;

public class TypeListActivity extends BaseActivity implements RecommendListner {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private ListView mListMessage;
	private List<FriendModel> list_result = new ArrayList<FriendModel>();
	private String state;
	private LinearLayout mllNomessage;
	FriendListAdapter orderListAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_im);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mIvStu:
			Intent intent = new Intent(TypeListActivity.this, CreateTypeMessageActivity.class);
			startActivity(intent);
			break;
		}
	}

	@Override
	public void initWidget() {
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvStu.setText("添加");
		mIvStu.setVisibility(View.VISIBLE);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		state = this.getIntent().getStringExtra("state");
		mTvTitle.setText("类型信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
	}

	@Override
	public void initData() {
		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listMyFriend");
		params.put("friendUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}

	private void deleteFriend(boolean isShow, FriendModel userModel) {

		AjaxParams params = new AjaxParams();
		params.put("action_flag", "deleteFriend");
		params.put("friendId", userModel.getFriendId());
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<FriendModel>>() {
					}.getType());
					orderListAdapter = new FriendListAdapter(TypeListActivity.this, list_result, TypeListActivity.this);
					mListMessage.setAdapter(orderListAdapter);
				} else {
					mllNomessage.setVisibility(View.VISIBLE);
				}
			}
			break;
		case Consts.actionId.resultCode:
			list_result.remove(posIndex);
			orderListAdapter.notifyDataSetChanged();
			break;
		default:
			break;
		}

	}

	@Override
	public void setRecommend(int pos, UserModel userModel) {

	}

	private int posIndex;

	@Override
	public void setRecommendDelete(int pos, FriendModel userModel) {
		posIndex = pos;
		deleteFriend(false, userModel);
	}

}
