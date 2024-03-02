package com.finance;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.finance.base.BaseActivity;
import com.finance.config.Consts;
import com.finance.db.MemberUserUtils;
import com.finance.imgload.FaceUtil;
import com.finance.model.ResponseEntry;
import com.finance.model.UserModel;
import com.finance.util.ToastUtil;
import com.finance.view.CircleImageView;
import com.finance.view.DialogMsg;
import com.squareup.picasso.Picasso;


public class UserMessageUpdateActivity extends BaseActivity  {
    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private Button mSubmit;

    private EditText metName;


    private ImageView infoMusicOperating;
    private final int REQUEST_PICTURE_CHOOSE = 1;
    private final int REQUEST_CAMERA_IMAGE = 2;

    private Bitmap mImage = null;
    private byte[] mImageData = null;
    // authid为6-18个字符长度，用于唯一标识用户
    private Toast mToast;
    // 进度对话框
    private EditText online_authid;
    // 拍照得到的照片文件
    private File mPictureFile;
    // 采用身份识别接口进行在线人脸识别
    private String userImage;
    private LinearLayout mllImgae;
    private DialogMsg dialogMsg;
    private CircleImageView mivUserImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {


        mivUserImg = (CircleImageView) findViewById(R.id.mivUserImg);
        metName = (EditText) findViewById(R.id.metName);

        mSubmit = (Button) findViewById(R.id.mSubmit);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("更改用户信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mivUserImg.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.mSubmit:
                createTopicPost(true);
                break;

            case R.id.mivUserImg:
                dialogMsg.Show();
                break;

        }
    }
    @Override
    public void initData() {
        UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
        userImage = userModel.getuImg();
        metName.setText(userModel.getUname());
        if (!TextUtils.isEmpty(userModel.getuImg())) {
//            Picasso.with(this).load(Consts.URL_IMAGE + userModel.getuImg()).placeholder(R.drawable.default_drawable_show_pictrue).into(mivUserImg);
            byte[] decodedString = Base64.decode(userModel.getuImg(), Base64.DEFAULT);
            Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            mivUserImg.setImageBitmap(decodedByte);
        }

        dialogMsg = new DialogMsg(this);
        dialogMsg.Set_Msg("请选择图像获取方式");
        dialogMsg.submit_no().setText("相册上传");
        dialogMsg.submit_ok().setText("拍照上传");

        dialogMsg.submit_no().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMsg.Close();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, REQUEST_PICTURE_CHOOSE);
            }
        });
        dialogMsg.submit_ok().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMsg.Close();
                mPictureFile = new File(Environment.getExternalStorageDirectory(), "picture" + System.currentTimeMillis() / 1000 + ".jpg");
                // 启动拍照,并保存到临时文件
                Intent mIntent = new Intent();
                mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPictureFile));
                mIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                startActivityForResult(mIntent, REQUEST_CAMERA_IMAGE);

            }
        });
    }


    private void createTopicPost(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "updateUser");
        params.put("uname", metName.getText().toString());
        if (mImageData != null) {
            System.out.println(mImageData);
//            params.put("uImg", new ByteArrayInputStream(mImageData), "image.jpg");
            params.put("uImg", Base64.encodeToString(mImageData, Base64.DEFAULT));
        }
        params.put("uid", MemberUserUtils.getUid(this) + "");
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultCode, isShow, "正在注册...");
    }



    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        switch (actionId) {
            case Consts.actionId.resultCode:
                ToastUtil.show(this, entry.getRepMsg());
                UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
                userModel.setuImg(userImage);
            	MemberUserUtils.putBean(this, "user_messgae", userModel);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_PICTURE_CHOOSE) { // 相册选择
                // 获取选择的图片的URI
                Uri uri = data.getData();
                try {
                    // 将图片文件转换为二进制数据
                    InputStream inputStream = getContentResolver().openInputStream(uri);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                    mImageData = outputStream.toByteArray();
                    mImage = BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length);

                    // 显示图片
                    mivUserImg.setImageBitmap(mImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else if (requestCode == REQUEST_CAMERA_IMAGE) { // 拍照
                try {
                    // 将照片文件转换为二进制数据
                    FileInputStream inputStream = new FileInputStream(mPictureFile);
                    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
                    byte[] buffer = new byte[1024];
                    int len;
                    while ((len = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, len);
                    }
                    mImageData = outputStream.toByteArray();
                    mImage = BitmapFactory.decodeByteArray(mImageData, 0, mImageData.length);

                    // 显示图片
                    mivUserImg.setImageBitmap(mImage);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (resultCode != RESULT_OK) {
//            return;
//        }
//
//        String fileSrc = null;
//        if (requestCode == REQUEST_PICTURE_CHOOSE) {
//            if ("file".equals(data.getData().getScheme())) {
//                // 有些低版本机型返回的Uri模式为file
//                fileSrc = data.getData().getPath();
//            } else {
//                // Uri模型为content
//                String[] proj = { MediaStore.Images.Media.DATA };
//                Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
//                cursor.moveToFirst();
//                int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//                fileSrc = cursor.getString(idx);
//                cursor.close();
//            }
//            // 跳转到图片裁剪页面
//            FaceUtil.cropPicture(this, Uri.fromFile(new File(fileSrc)));
//        } else if (requestCode == REQUEST_CAMERA_IMAGE) {
//            if (null == mPictureFile) {
//                return;
//            }
//
//            fileSrc = mPictureFile.getAbsolutePath();
//            updateGallery(fileSrc);
//            // 跳转到图片裁剪页面
//            FaceUtil.cropPicture(this, Uri.fromFile(new File(fileSrc)));
//        } else if (requestCode == FaceUtil.REQUEST_CROP_IMAGE) {
//
//
//
//            // 获取返回数据
//            Bitmap bmp = data.getParcelableExtra("data");
//            // 若返回数据不为null，保存至本地，防止裁剪时未能正常保存
//            if (null != bmp) {
//                FaceUtil.saveBitmapToFile(UserMessageUpdateActivity.this, bmp);
//            }
//            // 获取图片保存路径
//            fileSrc = FaceUtil.getImagePath(UserMessageUpdateActivity.this);
//            String[] arrPath = fileSrc.split("\\/");
//            userImage = arrPath[arrPath.length - 1];
//            Log.i("pony_log", userImage);
//            Log.i("pony_log", arrPath[arrPath.length - 1]);
//            // 获取图片的宽和高
//            BitmapFactory.Options options = new BitmapFactory.Options();
//            options.inJustDecodeBounds = true;
//            mImage = BitmapFactory.decodeFile(fileSrc, options);
//
//            // 压缩图片
//            options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max((double) options.outWidth / 1024f, (double) options.outHeight / 1024f)));
//            options.inJustDecodeBounds = false;
//            mImage = BitmapFactory.decodeFile(fileSrc, options);
//
//            // 若mImageBitmap为空则图片信息不能正常获取
//            if (null == mImage) {
//                return;
//            }
//
//            // 部分手机会对图片做旋转，这里检测旋转角度
//            int degree = FaceUtil.readPictureDegree(fileSrc);
//            if (degree != 0) {
//                // 把图片旋转为正的方向
//                mImage = FaceUtil.rotateImage(degree, mImage);
//            }
//
//            ByteArrayOutputStream baos = new ByteArrayOutputStream();
//
//            // 可根据流量及网络状况对图片进行压缩
//            mImage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
//            mImageData = baos.toByteArray();
//
//            ((ImageView) findViewById(R.id.mivUserImg)).setImageBitmap(mImage);
//
//            FinalHttp finalHttp = new FinalHttp();
//            // 文件上传到服务器的位置
//
//            AjaxParams params = new AjaxParams();
//            // 获取要上传的本地资源
//            try {
//                params.put("userImage", new File(fileSrc));
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//
//            finalHttp.post(Consts.URL + Consts.APP.RegisterAction + "?action_flag=updateUserImage", params, new AjaxCallBack<Object>() {
//                @Override
//                public void onStart() {
//                    // mbtnAdd.setText("开始上传");
//                    super.onStart();
//                }
//
//                @Override
//                public void onSuccess(Object o) {
//                    // mbtnAdd.setText("上传成功");
//                    ToastUtil.show(UserMessageUpdateActivity.this,"上传成功");
//                    // updateUser(false);
//                    super.onSuccess(o);
//                }
//
//                @Override
//                public void onFailure(Throwable t, int errorNo, String strMsg) {
//                    // mbtnAdd.setText("上传失败");
//                    super.onFailure(t, errorNo, strMsg);
//                }
//            });
//
//        }
//
//    }
    private void updateGallery(String filename) {
        MediaScannerConnection.scanFile(this, new String[] { filename }, null, new MediaScannerConnection.OnScanCompletedListener() {

            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 设置为横屏
         */
        if(getRequestedOrientation()!= ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE){
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }
   
}
