package com.finance.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.R;
import com.finance.listener.DeleteTypeListner;
import com.finance.model.IncomeModel;
import com.finance.model.PayTypeModel;

import java.util.List;

public class PayTypeListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<PayTypeModel> list_result;
	private int posIndex;
	private Context mContext;

	public PayTypeListAdapter(Context context, List<PayTypeModel> list_result) {
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
			holder.uname.setText( list_result.get(position).getLookMoneyPayType()+"("+list_result.get(position).getTotalNumber()+"笔交易)");
			holder.friendState.setVisibility(View.GONE);

			
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
