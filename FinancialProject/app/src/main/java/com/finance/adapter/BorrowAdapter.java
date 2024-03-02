package com.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.R;
import com.finance.model.BorrowModel;

import java.util.List;

public class BorrowAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<BorrowModel> list_result ;
	private int posIndex;
	public BorrowAdapter(Context context, List<BorrowModel>  list_result) {
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
			convertView = inflater.inflate(R.layout.borrow_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoneymsg);
			holder.mivDelete = (TextView) convertView.findViewById(R.id.mivDelete);
			holder.mivUpdate = (TextView) convertView.findViewById(R.id.mivUpdate);
			
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		try {

			if(list_result.get(position).getBorrowType().equals("1")){
				holder.mTvTitle.setText("借出"+list_result.get(position).getBorrowRealName()+list_result.get(position).getBorrowMoney()+"元");
			}else{
				holder.mTvTitle.setText("借入"+list_result.get(position).getBorrowRealName()+list_result.get(position).getBorrowMoney()+"元");
			}

			holder.mivDelete.setText("还款日期："+list_result.get(position).getBorrowTime());

			holder.mTvMoney.setText("添加时间："+list_result.get(position).getBorrowTime());

		} catch (Exception e) {
		}
		
		
		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle,mTvMoney;
		private TextView mivDelete,mivUpdate;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
