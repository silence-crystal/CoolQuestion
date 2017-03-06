package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czz.coolquestion.R;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_log,tv_reg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        tv_log= (TextView) findViewById(R.id.textView_log);
        tv_reg= (TextView) findViewById(R.id.textView_reg);

    }

    //监听事件
    public void InitListener(){
        tv_reg.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.textView_reg://注册
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.textView_log://登录

                break;
            default:
                break;
        }
    }
}
