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
import com.finance.listener.RemoveListner;
import com.finance.model.MoneyModel;

public class ShouRuAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<MoneyModel> list_result ;
	private int posIndex;
	private RemoveListner mListner;
	public ShouRuAdapter(Context context, List<MoneyModel>  list_result) {
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
			convertView = inflater.inflate(R.layout.notice_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mivDelete = (ImageView) convertView.findViewById(R.id.mivDelete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		try {
			holder.mTvTitle.setText(list_result.get(position).getMonthMessage()+"月("+list_result.get(position).getTotalMoney()+"元)");
			holder.mivDelete.setVisibility(View.GONE);
		} catch (Exception e) {
		}
		
		
		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle;
		private ImageView mivDelete;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
