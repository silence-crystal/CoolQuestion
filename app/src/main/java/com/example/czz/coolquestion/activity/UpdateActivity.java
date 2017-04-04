package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.UpdateUserInfo;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;
import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by Administrator on 2017/3/6.
 */

public class UpdateActivity extends Activity{
    private ImageView update_hand_img;
    private TextView update_baocun_tv,update_useraccount_tv;
    private View update_useraccount_layout;
    private EditText update_username_et,update_userqq_et,update_userphone_et,update_useraddress_et;
    private ACache aCache;
    private RequestQueue queue;
    private UserInfo.UserInfoBean user;
    //转为utf-8的用户信息
    private String name;
    private String qq;
    private String phone;
    private String address;
    //原始编码格式的用户信息
    private String strname;
    private String strqq;
    private String strphone;
    private String straddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        aCache = ACache.get(this);
        queue = Volley.newRequestQueue(this);
        update_hand_img= (ImageView) findViewById(R.id.update_hand_img);
        update_hand_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        update_useraccount_layout=findViewById(R.id.update_useraccount_layout);
        update_useraccount_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(UpdateActivity.this,"用户账号不可修改！",Toast.LENGTH_SHORT).show();
            }
        });

        update_username_et = (EditText) findViewById(R.id.update_username_et);
        update_userqq_et = (EditText) findViewById(R.id.update_userqq_et);
        update_userphone_et = (EditText) findViewById(R.id.update_userphone_et);
        update_useraddress_et = (EditText) findViewById(R.id.update_useraddress_et);
        update_useraccount_tv = (TextView) findViewById(R.id.update_useraccount_tv);

        initData();

        update_baocun_tv= (TextView) findViewById(R.id.update_baocun_tv);
        update_baocun_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                strname=update_username_et.getText().toString().trim();
                strqq=update_userqq_et.getText().toString().trim();
                strphone=update_userphone_et.getText().toString().trim();
                straddress=update_useraddress_et.getText().toString().trim();

                try {
                    name = URLEncoder.encode(strname,"utf-8");
                    qq = URLEncoder.encode(strqq,"utf-8");
                    phone = URLEncoder.encode(strphone,"utf-8");
                    address = URLEncoder.encode(straddress,"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                StringRequest updateReq = new StringRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/UpdateUserInfo?userid="+user.getUserId()+"&username="+name+"&userqq="+qq+"&userphone="+phone+"&useraddress="+address, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        UpdateUserInfo res = gson.fromJson(response, UpdateUserInfo.class);
                        if (res.getResult().equals("success")){
                            user.setUserName(strname);
                            user.setUserQQ(strqq);
                            user.setUserPhone(strphone);
                            user.setUserAddress(straddress);
                            aCache.put("user",user);
                            Toast.makeText(UpdateActivity.this,"保存成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(UpdateActivity.this,"更新失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(UpdateActivity.this,"网络连接异常！",Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(updateReq);
                queue.start();

            }
        });

    }

    private void initData() {
        user = (UserInfo.UserInfoBean) aCache.getAsObject("user");
        update_useraccount_tv.setText(user.getUserAccount());
        update_username_et.setText(user.getUserName());
        update_userqq_et.setText(user.getUserQQ());
        update_userphone_et.setText(user.getUserPhone());
        update_useraddress_et.setText(user.getUserAddress());
    }
}
