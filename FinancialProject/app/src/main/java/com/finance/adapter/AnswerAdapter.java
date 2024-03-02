package com.finance.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.finance.R;
import com.finance.model.AnswerModel;
import com.finance.view.ListViewForScrollView;

import java.util.List;


public class AnswerAdapter extends BaseAdapter {
	
    private Context mContext;
    private List<AnswerModel> list_result;

    public AnswerAdapter(Context mContext) {
        super();
        this.mContext = mContext;
    }

    public AnswerAdapter(Context mContext, List<AnswerModel> list_msg) {
        super();
        this.mContext = mContext;
        this.list_result = list_msg;
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
        ViewHolder vh = null;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_reply, null);
            vh = new ViewHolder();
            vh.message_name = (TextView) convertView.findViewById(R.id.message_name);
            vh.message_state = (TextView) convertView.findViewById(R.id.message_state);
            vh.mtvContent = (TextView) convertView.findViewById(R.id.mtvContent);
            vh.userInfo = (LinearLayout) convertView.findViewById(R.id.userInfo);
            vh.mListSearchMessage = (ListViewForScrollView) convertView.findViewById(R.id.mListSearchMessage);
            convertView.setTag(vh);

        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        vh.message_state.setText(list_result.get(position).getAnswerCreatime());
        vh.mtvContent.setText(list_result.get(position).getAnswerMessage());
        vh.message_name.setText(list_result.get(position).getAnswerUserName());
        ReplyListAdapter  replyListAdapter = new ReplyListAdapter(mContext, list_result.get(position).getReplyMsg());
        vh.mListSearchMessage.setAdapter(replyListAdapter);
        
        vh.userInfo.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			}
		});
        return convertView;
    }

    class ViewHolder {
        TextView message_name;
        TextView message_state;
        TextView messageContent;
        TextView mtvContent;
        TextView mtvReplyContent;
        LinearLayout userInfo;
        ListViewForScrollView mListSearchMessage;
    }
}