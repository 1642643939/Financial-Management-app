package com.finance.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.finance.CalActivity;
import com.finance.GuideTopicDetailActivity;
import com.finance.LoginActivity;
import com.finance.R;
import com.finance.SoftMessageActivity;
import com.finance.TongJiBingActivity;
import com.finance.TongJiZheXianActivity;
import com.finance.UserActivity;
import com.finance.base.BaseFragment;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.model.UserModel;
import com.finance.view.CircleImageView;
import com.squareup.picasso.Picasso;

/**
 * @author WangXuan
 */
public class MeFragment extends BaseFragment {

    // 获取view
    private View rootView;
    private RelativeLayout mtvUser;
    private RelativeLayout mrlFabu;
    private RelativeLayout mtvSoft;
    private RelativeLayout mtvCal;
    private RelativeLayout mrlDownload;
    private CircleImageView mivUserImg;
    private TextView nameMsg;

    private RelativeLayout mrlrili;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content, null);

        return rootView;
    }


    @Override
    public void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        initWidget();
        initData();
    }

    /**
     * 获取控件
     */
    @Override
    public void initWidget() {
        mivUserImg = (CircleImageView) rootView.findViewById(R.id.mivUserImg);
        mrlDownload = (RelativeLayout) rootView.findViewById(R.id.mrlDownload);
        mtvSoft = (RelativeLayout) rootView.findViewById(R.id.mtvSoft);
        mrlFabu = (RelativeLayout) rootView.findViewById(R.id.mrlFabu);
        mtvUser = (RelativeLayout) rootView.findViewById(R.id.mtvUser);
        mtvCal = (RelativeLayout) rootView.findViewById(R.id.mtvCal);
        mrlFabu.setOnClickListener(this);
        mtvUser.setOnClickListener(this);
        mtvCal.setOnClickListener(this);
        mtvSoft.setOnClickListener(this);
        mrlDownload.setOnClickListener(this);
        nameMsg = (TextView) rootView.findViewById(R.id.nameMsg);

        mrlrili = (RelativeLayout) rootView.findViewById(R.id.mrlrili);
        mrlrili.setOnClickListener(this);


    }

    /**
     * 处理点击事件
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.mrlFabu:
                Intent mrlFabu = new Intent(getActivity(), TongJiBingActivity.class);
                getActivity().startActivity(mrlFabu);

                break;

            case R.id.mrlrili:
                Intent mrlrili = new Intent(getActivity(), TongJiZheXianActivity.class);
                getActivity().startActivity(mrlrili);

                break;

            case R.id.mrlDownload:

                Intent mrlDownload = new Intent(getActivity(), GuideTopicDetailActivity.class);
                getActivity().startActivity(mrlDownload);

                break;



            case R.id.mtvSoft:
                Intent mtvSoft = new Intent(getActivity(), SoftMessageActivity.class);
                getActivity().startActivity(mtvSoft);
                break;

            case R.id.mtvUser:
                Intent mtvUser = new Intent(getActivity(), UserActivity.class);
                getActivity().startActivity(mtvUser);
                break;

            case R.id.mtvCal:
                Intent mtvCal = new Intent(getActivity(), CalActivity.class);
                getActivity().startActivity(mtvCal);
                break;

            case R.id.mExit:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
                break;
            default:
                break;
        }

    }

    /**
     * 处理数据
     */
    @Override
    public void initData() {
        UserModel userModel = (UserModel) MemberUserUtils.getBean(getActivity(), "user_messgae");
        nameMsg.setText(userModel.getUname());
        if (!TextUtils.isEmpty(userModel.getuImg())) {
//            Picasso.with(getActivity()).load(Consts.URL_IMAGE + userModel.getuImg()).placeholder(R.drawable.icon_dota2_logo).into(mivUserImg);
            byte[] decodedString = Base64.decode(userModel.getuImg(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            mivUserImg.setImageBitmap(decodedByte);
        }

    }

}
