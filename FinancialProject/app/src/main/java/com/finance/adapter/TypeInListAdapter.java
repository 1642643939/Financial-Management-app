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
import com.finance.UpdateMessageActivity;
import com.finance.UpdateTypeInMessageActivity;
import com.finance.listener.DeleteTypeListner;
import com.finance.model.IncomeModel;

import java.util.List;

public class TypeInListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<IncomeModel> list_result;
	private int posIndex;
	private Context mContext;
	private DeleteTypeListner mDeleteTypeListner;

	public TypeInListAdapter(Context context, List<IncomeModel> list_result, DeleteTypeListner deleteTypeListner) {
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
			holder.uname.setText( list_result.get(position).getTypeIncomeName());
			holder.friendState.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					mDeleteTypeListner.setRemoveIn(position,list_result.get(position));
				}
			});

			holder.friendupdate.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(mContext, UpdateTypeInMessageActivity.class);
					intent.putExtra("msg",list_result.get(posIndex));
					mContext.startActivity(intent);
				}
			});


//			if(!list_result.get(position).getTypeMoney().equals("")){
//
//				NumberFormat numberFormat = NumberFormat.getInstance();
//				// 设置精确到小数点后2位
//				numberFormat.setMaximumFractionDigits(2);
//				String result = numberFormat.format(Double.valueOf(list_result.get(position).getTypeMoney()) / Double.valueOf(list_result.get(position).getTotalMoney())  * 100);//所占百分比
//				holder.uhobby.setText("所占比例：" + result+"%");
//			}else{
//				holder.uhobby.setText("暂无消费");
//			}

			
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
