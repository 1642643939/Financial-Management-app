package com.finance.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.R;
import com.finance.listener.RecommendListner;
import com.finance.model.FriendModel;
import com.squareup.picasso.Picasso;

public class FriendListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<FriendModel> list_result;
	private int posIndex;
	private Context mContext;
	private RecommendListner mRecommendListner;

	public FriendListAdapter(Context context, List<FriendModel> list_result, RecommendListner recommendListner) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
		this.mRecommendListner = recommendListner;
	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_friendmessage_delete, null);
			holder = new ViewHolder();
			holder.uname = (TextView) convertView.findViewById(R.id.uname);
			holder.uhobby = (TextView) convertView.findViewById(R.id.uhobby);
			holder.friendState = (TextView) convertView.findViewById(R.id.friendState);
			holder.mivAdd = (ImageView) convertView.findViewById(R.id.mivAdd);

			holder.ImageView01 = (ImageView) convertView.findViewById(R.id.ImageView01);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.uname.setText("好友名称：" + list_result.get(position).getUserMessage().getUname());
			holder.uhobby.setText("联系方式：" + list_result.get(position).getUserMessage().getUphone());
			holder.mivAdd.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mRecommendListner.setRecommendDelete(position, list_result.get(position));
				}
			});

		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView uname;
		private TextView uhobby;
		private ImageView mivAdd;
		private ImageView ImageView01;
		private TextView friendState;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
