package com.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.R;
import com.finance.model.ReplyModel;

import java.util.List;


public class ReplyListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ReplyModel> list_result;
	private int posIndex;
	private Context mContext;
	public ReplyListAdapter(Context context, List<ReplyModel> list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
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
			convertView = inflater.inflate(R.layout.item_replymsg, null);
			holder = new ViewHolder();
			holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
			holder.itemTime = (TextView) convertView.findViewById(R.id.itemTime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			
			holder.itemTitle.setText(list_result.get(position).getReplyUserName());
			holder.itemTime.setText(list_result.get(position).getReplyMessage());

		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView itemSize,itemTime;
		private TextView itemTitle;
		private ImageView itemImage;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
