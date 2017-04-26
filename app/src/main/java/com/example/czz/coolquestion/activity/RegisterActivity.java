package com.example.czz.coolquestion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.czz.coolquestion.bean.SignupRes;
import com.example.czz.coolquestion.url.URLConfig;
import com.google.gson.Gson;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_useraccount;
    private EditText et_password;
    private EditText et_surepass;
    private TextView tv_signup;
    private RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        queue = Volley.newRequestQueue(this);
        initView();
    }

    private void initView() {
        et_useraccount = (EditText) findViewById(R.id.editText_reg_name);
        et_password = (EditText) findViewById(R.id.editText_reg_password);
        et_surepass = (EditText) findViewById(R.id.editText_reg_passToo);
        tv_signup = (TextView) findViewById(R.id.tv_signup);

        tv_signup.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_signup:
                if (et_password.getText().toString().equals(et_surepass.getText().toString())){
                    StringRequest signupReq = new StringRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/Signup?useraccount="+et_useraccount.getText().toString()+"&password="+et_password.getText().toString(), new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            Gson gson = new Gson();
                            SignupRes res = gson.fromJson(response, SignupRes.class);
                            if (res.getResult().equals("success")){
                                Toast.makeText(RegisterActivity.this,"注册成功",Toast.LENGTH_SHORT).show();
                                finish();
                            }else {
                                Toast.makeText(RegisterActivity.this,res.getReason(),Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

                        }
                    });
                    queue.add(signupReq);
                    queue.start();
                    //Toast.makeText(this,"注册成功！",Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(this,"两次输入密码不一致，请重新输入",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
