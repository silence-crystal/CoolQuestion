package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/3.
 */

public class SetActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView set_fanhui_img;
    private View pingjia_layout,banquan_layout,xieyi_layout,shuoming_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        InitView();
        InitListener();
    }
    //各种控件
    public void InitView(){
        set_fanhui_img= (ImageView) findViewById(R.id.set_fanhui_img);
        pingjia_layout= findViewById(R.id.pingjia_layout);
        banquan_layout= findViewById(R.id.banquan_layout);
        xieyi_layout= findViewById(R.id.xieyi_layout);
        shuoming_layout=  findViewById(R.id.shuoming_layout);
    }

    //监听事件
    public void InitListener(){
        set_fanhui_img.setOnClickListener(this);
        pingjia_layout.setOnClickListener(this);
        banquan_layout.setOnClickListener(this);
        xieyi_layout.setOnClickListener(this);
        shuoming_layout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.set_fanhui_img://返回按钮
                finish();
                break;
            case R.id.pingjia_layout://评价
                Intent intent1=new Intent(SetActivity.this,PingjiaActivity.class);
                startActivity(intent1);
                break;
            case R.id.banquan_layout://版权
                Intent intent2=new Intent(SetActivity.this,BanquanActivity.class);
                startActivity(intent2);
                break;
            case R.id.xieyi_layout://协议
                Intent intent3=new Intent(SetActivity.this,XieyiActivity.class);
                startActivity(intent3);
                break;
            case R.id.shuoming_layout://说明
                Intent intent4=new Intent(SetActivity.this,ShuomingActivity.class);
                startActivity(intent4);
                break;
            default:
                break;
        }
    }
}
