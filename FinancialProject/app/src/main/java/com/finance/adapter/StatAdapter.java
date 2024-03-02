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
import com.finance.model.CategoryModel;

public class StatAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<CategoryModel> list_result ;
	private int posIndex;
	public StatAdapter(Context context, List<CategoryModel>  list_result) {
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
			convertView = inflater.inflate(R.layout.course_item, null);
			holder = new ViewHolder();
			holder.mtvCostMoney = (TextView) convertView.findViewById(R.id.mtvCostMoney);
			holder.mtvIncomeMoney = (TextView) convertView.findViewById(R.id.mtvIncomeMoney);
			holder.mtvName = (TextView) convertView.findViewById(R.id.mtvName);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		try {
//			holder.mTvTitle.setText(list_result.get(position).getCategoryName());
//			holder.mivDelete.setOnClickListener(new OnClickListener() {
//				
//				@Override
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					mListner.setRemove(position, list_result.get(position));
//				}
//			});
		} catch (Exception e) {
		}
		
		
		return convertView;

	}

	private class ViewHolder {
		private TextView mtvCostMoney;
		private TextView mtvIncomeMoney;
		private TextView mtvName;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
