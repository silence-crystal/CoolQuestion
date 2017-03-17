package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.text.Layout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ChangeHeadImgRes;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;
import com.example.czz.coolquestion.view.ImageViewShape;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/6.
 */

public class PersonalInfoActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView personal_info_hand_img;
    private View useraccount_layout;
    private View username_layout;
    private View userqq_layout;
    private View userphone_layout;
    private View useraddress_layout;
    private View personal_info_name_layout;
    private Button tuku_btn, paizhao_btn, quxiao_btn;
    private HttpUtils httputils;
    private Uri fileUri;
    private TextView username_tv, userqq_tv, userphone_tv, useraddress_tv;
    private View view;
    private PopupWindow pw;
    private RequestQueue queue;
    private ACache aCache;
    private UserInfo.UserInfoBean user;
    private TextView useraccount_tv;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;
    private ImageViewShape personal_info_name_img;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_info);
        aCache = ACache.get(this);
        imageLoader = ImageLoader.getInstance();
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.mipmap.head)
                .showImageOnFail(R.mipmap.head)
                .build();
        queue= Volley.newRequestQueue(getApplicationContext());
        InitView();
        InitListener();
        httputils = new HttpUtils();


    }

    //个人信息回传值方法
    private void infofuzhi() {
        Intent intent = new Intent(PersonalInfoActivity.this, UpdateActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        pw.dismiss();
        String path = "";
        if (requestCode == 0) {
            if (data.toString().equals("Intent {  }")){
                return;
            }
            path = fileUri.getPath(); //取得拍照存储的地址
        } else if (requestCode == 1) { //解析得到所选相册图片的地址
            if (data==null){
                return;
            }
            Uri uri = data.getData();
            Cursor cursor = this.getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            path = cursor.getString(1);//图片文件路径

        }
        Log.i("this", path + "-----");
        File f = new File(path);
        Bitmap map = BitmapFactory.decodeFile(f.getPath());
//        personal_info_name_img.setImageBitmap(map);

        String uploadHost = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/UpdateHeadImg";

        RequestParams params = new RequestParams();
        params.addBodyParameter("photo",f);
        params.addBodyParameter("uid",user.getUserId()+"");
        httputils.send(HttpMethod.POST, uploadHost, params, new RequestCallBack<String>() {
            @Override
            public void onStart() {
                super.onStart();
                Toast.makeText(PersonalInfoActivity.this, "开始上传", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLoading(long total, long current, boolean isUploading) {
                super.onLoading(total, current, isUploading);
                Log.i("图片正在上传",current/total+"%");
            }

            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Gson gson = new Gson();
                ChangeHeadImgRes res = gson.fromJson(responseInfo.result, ChangeHeadImgRes.class);
                if (res.getResult().equals("success")){
                    String imgPath = res.getImgurl();
                    imageLoader.displayImage(URLConfig.MAIN_URL+imgPath,personal_info_name_img,options);
                    user = getCurrentUser();
                    user.setUserImg(imgPath);
                    aCache.put("user",user);
//                //更新服务器用户数据或者在更换头像时更换数据
//                StringRequest updateImg = new StringRequest("", new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//                    }
//                });
//                queue.add(updateImg);
//                queue.start();
                    Toast.makeText(PersonalInfoActivity.this, "保存成功", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(PersonalInfoActivity.this, "保存失败", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(HttpException e, String s) {
                Toast.makeText(PersonalInfoActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * 为保存图片或视频创建一个文件地址
     */
    private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }

    /**
     * Create a File for saving an image or video
     */
    private static File getOutputMediaFile(int type) {

        // 首先检测外部SDCard是否存在并且可读可写
        if (!Environment.getExternalStorageState().equals("mounted")) {
            return null;
        }
        File mediaStorageDir = new File(Environment.getExternalStorageDirectory().getPath());


        // 如果存储路径不存在，则创建
        // Create the storage directory if it does not exist
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }
        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        if (type == 0) {
            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                    "IMG_" + timeStamp + ".jpg");
        } else {
            return null;
        }

        return mediaFile;
    }

    @Override
    protected void onResume() {
        super.onResume();
        user = getCurrentUser();
        useraccount_tv.setText(user.getUserAccount());
        //昵称
        if (user.getUserName().length()==0){
            username_tv.setText("请先完善信息");
        }else {
            username_tv.setText(user.getUserName());
        }
        //qq
        if (user.getUserQQ().length()==0){
            userqq_tv.setText("请先完善信息");
        }else {
            userqq_tv.setText(user.getUserQQ());
        }
        //电话
        if (user.getUserPhone().length()==0){
            userphone_tv.setText("请先完善信息");
        }else {
            userphone_tv.setText(user.getUserPhone());
        }
        //地址
        if (user.getUserAddress().length()==0){
            useraddress_tv.setText("请先完善信息");
        }else {
            useraddress_tv.setText(user.getUserAddress());
        }
    }

    //各种控件
    public void InitView(){
        username_tv = (TextView) findViewById(R.id.username_tv);
        userqq_tv = (TextView) findViewById(R.id.userqq_tv);
        userphone_tv = (TextView) findViewById(R.id.userphone_tv);
        useraddress_tv = (TextView) findViewById(R.id.useraddress_tv);
        personal_info_name_img = (ImageViewShape) findViewById(R.id.personal_info_name_img);
        personal_info_hand_img = (ImageView) findViewById(R.id.personal_info_hand_img);
        useraccount_layout = findViewById(R.id.useraccount_layout);
        username_layout = findViewById(R.id.username_layout);
        userqq_layout = findViewById(R.id.userqq_layout);
        userphone_layout = findViewById(R.id.userphone_layout);
        useraddress_layout = findViewById(R.id.useraddress_layout);
        useraccount_tv = (TextView) findViewById(R.id.useraccount_tv);

        view = getLayoutInflater().inflate(R.layout.touxiangpopupwindow, null);
        personal_info_name_layout = findViewById(R.id.personal_info_name_layout);
        tuku_btn = (Button) view.findViewById(R.id.tuku_btn);
        paizhao_btn = (Button) view.findViewById(R.id.paizhao_btn);
        quxiao_btn = (Button) view.findViewById(R.id.quxiao_btn);

        imageLoader.displayImage("http://"+URLConfig.MAIN_URL+":8080/"+getCurrentUser().getUserImg(),personal_info_name_img,options);
    }
    //监听事件
    public void InitListener(){
        personal_info_hand_img.setOnClickListener(this);
        useraccount_layout.setOnClickListener(this);
        username_layout.setOnClickListener(this);
        userqq_layout.setOnClickListener(this);
        userphone_layout.setOnClickListener(this);
        useraddress_layout.setOnClickListener(this);
        personal_info_name_layout.setOnClickListener(this);
        tuku_btn.setOnClickListener(this);
        paizhao_btn.setOnClickListener(this);
        quxiao_btn.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.personal_info_hand_img://返回上一页按钮
                finish();
                break;
            case R.id.useraccount_layout://用户账户跳转修改
            case R.id.username_layout://用户昵称跳转修改
            case R.id.userqq_layout://用户QQ跳转修改
            case R.id.userphone_layout://用户电话跳转修改
            case R.id.useraddress_layout://用户地址跳转修改
                infofuzhi();
                break;
            case R.id.personal_info_name_layout://点击头像更换头像
                View v1= LayoutInflater.from(this).inflate(R.layout.activity_personal_info,null);
                pw = new PopupWindow(view, LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
                //设置显示动画
                pw.setAnimationStyle(R.style.main_menu_animstyle);
                pw.setBackgroundDrawable(getResources().getDrawable(R.mipmap.touming));
                pw.setOutsideTouchable(true);
                pw.showAtLocation(v1,Gravity.BOTTOM,0,0);
                break;
            case R.id.tuku_btn://使用图库
                Intent picture = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(picture, 1);
                break;
            case R.id.paizhao_btn://使用拍照
                fileUri = getOutputMediaFileUri(0); //得到存储地址的Uri
                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE); //此action表示进行拍照
                i.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);  //指定图片的输出地址
                startActivityForResult(i, 0);
                break;
            case R.id.quxiao_btn://点击取消
                pw.dismiss();
                break;
            default:
                break;
        }
    }

    private UserInfo.UserInfoBean getCurrentUser(){
        return (UserInfo.UserInfoBean) aCache.getAsObject("user");
    }
}
