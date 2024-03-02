package com.finance.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.finance.R;
import com.finance.listener.RemoveListner;
import com.finance.model.MoneyModel;
import com.finance.model.MoneyTongJiModel;

import java.util.List;

public class MoneyTongJiAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<MoneyTongJiModel> list_result;
    private int posIndex;
    private RemoveListner mListner;

    public MoneyTongJiAdapter(Context context, List<MoneyTongJiModel> list_result, RemoveListner mRemoveListner) {
        inflater = LayoutInflater.from(context);
        this.list_result = list_result;
        mListner = mRemoveListner;
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
            holder.mTvMoney = (TextView) convertView.findViewById(R.id.mTvMoneymsg);
            holder.mivDelete = (TextView) convertView.findViewById(R.id.mivDelete);
            holder.mivUpdate = (TextView) convertView.findViewById(R.id.mivUpdate);
            holder.mivType = (TextView) convertView.findViewById(R.id.mivType);
            holder.mTvTip = (TextView) convertView.findViewById(R.id.mTvTip);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        try {
            holder.mTvTitle.setText(list_result.get(position).getMoney()+ "元");
            holder.mTvMoney.setText("统计时间：" + list_result.get(position).getTimeMsg());

            holder.mTvTip.setText(" ");
            holder.mivUpdate.setVisibility(View.GONE);
            holder.mivDelete.setVisibility(View.GONE);
            holder.mivType.setVisibility(View.GONE);


        } catch (Exception e) {
        }


        return convertView;

    }

    private class ViewHolder {

        private TextView mTvTitle, mTvMoney,mTvTip;
        private TextView mivDelete, mivUpdate, mivType;
    }

    public void setPos(int pos) {
        posIndex = pos;
    }

}
