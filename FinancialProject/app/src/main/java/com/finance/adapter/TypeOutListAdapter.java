package com.finance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.finance.R;
import com.finance.UpdateTypeInMessageActivity;
import com.finance.UpdateTypeOutMessageActivity;
import com.finance.listener.DeleteTypeListner;
import com.finance.model.PayModel;

import java.util.List;

public class TypeOutListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<PayModel> list_result;
	private int posIndex;
	private Context mContext;
	private DeleteTypeListner mDeleteTypeListner;
	public TypeOutListAdapter(Context context, List<PayModel> list_result, DeleteTypeListner deleteTypeListner) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
		this.mDeleteTypeListner = deleteTypeListner;

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
			holder.friendupdate = (TextView) convertView.findViewById(R.id.friendupdate);
			holder.friendState = (TextView) convertView.findViewById(R.id.friendState);
			holder.mivAdd = (ImageView) convertView.findViewById(R.id.mivAdd);

			holder.ImageView01 = (ImageView) convertView.findViewById(R.id.ImageView01);

			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.uname.setText(list_result.get(position).getTypePayName());
			holder.friendState.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mDeleteTypeListner.setRemoveOut(position,list_result.get(position));
				}
			});
			holder.friendupdate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(mContext, UpdateTypeOutMessageActivity.class);
					intent.putExtra("msg",list_result.get(posIndex));
					mContext.startActivity(intent);
				}
			});


		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView uname;
		private TextView friendupdate;
		private ImageView mivAdd;
		private ImageView ImageView01;
		private TextView friendState;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
