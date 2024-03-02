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
import com.finance.model.UserModel;
import com.finance.view.RoundedCornerImageView;
import com.squareup.picasso.Picasso;

public class SearchListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<UserModel> list_result;
	private int posIndex;
	private Context mContext;
	private RecommendListner mRecommendListner;

	public SearchListAdapter(Context context, List<UserModel> list_result, RecommendListner recommendListner) {
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
			convertView = inflater.inflate(R.layout.item_friendmessage, null);
			holder = new ViewHolder();
			holder.uname = (TextView) convertView.findViewById(R.id.uname);
			holder.uhobby = (TextView) convertView.findViewById(R.id.uhobby);
			holder.friendState = (TextView) convertView.findViewById(R.id.friendState);
			holder.mivAdd = (ImageView) convertView.findViewById(R.id.mivAdd);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.uname.setText(list_result.get(position).getUname());
			holder.uhobby.setText("联系方式：" + list_result.get(position).getUphone());

			holder.mivAdd.setVisibility(View.VISIBLE);

			holder.mivAdd.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mRecommendListner.setRecommend(position, list_result.get(position));
				}
			});
			
//			float distance = AMapUtils.calculateLineDistance(latLng1,latLng2);

			if (list_result.get(position).isFriend()) {
				holder.friendState.setVisibility(View.VISIBLE);
				holder.mivAdd.setVisibility(View.GONE);
			} else {
				holder.friendState.setVisibility(View.GONE);
				holder.mivAdd.setVisibility(View.VISIBLE);
			}

	

		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView uname;
		private TextView uhobby;
		private ImageView mivAdd;
		private TextView friendState;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
