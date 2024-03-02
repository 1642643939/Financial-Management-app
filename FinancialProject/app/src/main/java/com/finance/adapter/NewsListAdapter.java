package com.finance.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.R;
import com.finance.model.NewsModel;

public class NewsListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<NewsModel> list_result;
	private int posIndex;
	private Context mContext;

	public NewsListAdapter(Context context, List<NewsModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_studyguide_msg, null);
			holder = new ViewHolder();
			holder.itemSize = (TextView) convertView.findViewById(R.id.itemSize);
			holder.itemTitle = (TextView) convertView.findViewById(R.id.itemTitle);
			holder.itemTime = (TextView) convertView.findViewById(R.id.itemTime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// try {

		holder.itemTitle.setText(list_result.get(position).getNewsTitle());
		holder.itemTime.setText(list_result.get(position).getNewsTime());
		holder.itemSize.setText("      " + list_result.get(position).getNewsContent());

		// } catch (Exception e) {
		// }

		return convertView;

	}

	private class ViewHolder {
		private TextView itemSize, itemTime;
		private TextView itemTitle;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
