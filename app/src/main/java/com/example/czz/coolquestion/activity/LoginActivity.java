package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.fragment.PersonalFragment;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;
import com.google.gson.Gson;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_log,tv_reg;
    private EditText et_username;
    private EditText et_password;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        queue = Volley.newRequestQueue(this);
        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        //登录按钮
        tv_log= (TextView) findViewById(R.id.textView_log);
        //注册按钮
        tv_reg= (TextView) findViewById(R.id.textView_reg);
        et_username = (EditText) findViewById(R.id.editText_log_name);
        et_password = (EditText) findViewById(R.id.editText_log_password);

    }

    //监听事件
    public void InitListener(){
        tv_log.setOnClickListener(this);
        tv_reg.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView_reg://注册
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.textView_log://登录
                StringRequest loginReq = new StringRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/LoginServlet?account="+et_username.getText().toString()+"&password="+et_password.getText().toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Gson gson = new Gson();
                        //登录返回结果实体类对象
                        UserInfo uInfo = gson.fromJson(response, UserInfo.class);
                        if (uInfo.getResult().equals("success")){
                            //如果成功提取用户信息对象并保存
                            UserInfo.UserInfoBean userinfo = uInfo.getUserInfo();
                            ACache aCache = ACache.get(LoginActivity.this);
                            aCache.put("user",userinfo);
                            Toast.makeText(LoginActivity.this,"登录成功",Toast.LENGTH_SHORT).show();
                            finish();
                        }else {
                            Toast.makeText(LoginActivity.this,"登录失败",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(LoginActivity.this,"网络情况异常",Toast.LENGTH_SHORT).show();
                    }
                });
                queue.add(loginReq);
                queue.start();
                break;
            default:
                break;
        }
    }
}
