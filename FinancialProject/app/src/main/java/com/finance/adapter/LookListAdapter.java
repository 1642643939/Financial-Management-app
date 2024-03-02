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
import com.finance.listener.DeleteListner;
import com.finance.model.MoneyModel;

public class LookListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<MoneyModel> list_result;
	private int posIndex;
	DeleteListner mRemoveListner;

	public LookListAdapter(Context context, List<MoneyModel> list_result) {
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
			convertView = inflater.inflate(R.layout.look_item, null);
			holder = new ViewHolder();
			holder.mtvCate = (TextView) convertView.findViewById(R.id.mtvCate);
			holder.mtvPrice = (TextView) convertView.findViewById(R.id.mtvPrice);
//			holder.mivDelete = (ImageView) convertView.findViewById(R.id.mivDelete);
			
			holder.mtvCateLeft = (TextView) convertView.findViewById(R.id.mtvCateLeft);
			holder.mtvPriceLeft = (TextView) convertView.findViewById(R.id.mtvPriceLeft);
			
			
			holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
			holder.mtvTimeLeft = (TextView) convertView.findViewById(R.id.mtvTimeLeft);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		// try {
	
		

		if (list_result.get(position).getTypeMessage().equals("1")) {
			holder.mtvCateLeft.setText(list_result.get(position).getLookMoneyTypeName());
			holder.mtvPriceLeft.setText("￥" + list_result.get(position).getLookMoneyMoney());
			holder.mtvTimeLeft.setText(list_result.get(position).getLookMoneyTime());
			
			holder.mtvCate.setVisibility(View.GONE);
			holder.mtvPrice.setVisibility(View.GONE);
			holder.mtvTime.setVisibility(View.GONE);
			
			holder.mtvCateLeft.setVisibility(View.VISIBLE);
			holder.mtvPriceLeft.setVisibility(View.VISIBLE);
			holder.mtvTimeLeft.setVisibility(View.VISIBLE);
			
			
		} else {
			
			holder.mtvCateLeft.setVisibility(View.GONE);
			holder.mtvPriceLeft.setVisibility(View.GONE);
			holder.mtvTimeLeft.setVisibility(View.GONE);
			
			holder.mtvCate.setVisibility(View.VISIBLE);
			holder.mtvPrice.setVisibility(View.VISIBLE);
			holder.mtvTime.setVisibility(View.VISIBLE);
			
			holder.mtvCate.setText(list_result.get(position).getLookMoneyTypeName());
			holder.mtvPrice.setText("￥" + list_result.get(position).getLookMoneyMoney());
			holder.mtvTime.setText(list_result.get(position).getLookMoneyTime());
		}
		return convertView;

	}

	private class ViewHolder {
		private TextView mtvCate;
		private TextView mtvPrice;
		private TextView mtvCateLeft;
		private TextView mtvPriceLeft;
		
		private TextView mtvTime;
		private TextView mtvTimeLeft;
		
		
		

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
