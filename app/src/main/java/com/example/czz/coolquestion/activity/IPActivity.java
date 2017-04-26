package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.url.URLConfig;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class IPActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText et_ip;
    private TextView tv_sure;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ip);
        CloseAllActivitys.list.add(this);
        initView();
    }

    private void initView() {
        et_ip = (EditText) findViewById(R.id.et_ip);
        tv_sure = (TextView) findViewById(R.id.tv_sure);

        tv_sure.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String userIP = et_ip.getText().toString().trim();
        String reg = "^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])$";
        Pattern pattern = Pattern.compile(reg);
        Matcher matcher = pattern.matcher(userIP);
        if (matcher.matches()){
            URLConfig.MAIN_URL = userIP;
            Intent intent = new Intent(this,GuideActivity.class);
            startActivity(intent);
        }else {
            Toast.makeText(this,"输入格式不正确",Toast.LENGTH_SHORT).show();
        }
    }
}
