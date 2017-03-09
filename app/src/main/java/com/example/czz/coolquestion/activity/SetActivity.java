package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/3.
 */

public class SetActivity extends Activity{
    private ImageView set_fanhui_img;
    private View pingjia_layout,banquan_layout,xieyi_layout,shuoming_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);
        set_fanhui_img= (ImageView) findViewById(R.id.set_fanhui_img);
        set_fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        pingjia_layout= findViewById(R.id.pingjia_layout);
        pingjia_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,PingjiaActivity.class);
                startActivity(intent);
            }
        });
        banquan_layout= findViewById(R.id.banquan_layout);
        banquan_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,BanquanActivity.class);
                startActivity(intent);
            }
        });
        xieyi_layout= findViewById(R.id.xieyi_layout);
        xieyi_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,XieyiActivity.class);
                startActivity(intent);
            }
        });
        shuoming_layout=  findViewById(R.id.shuoming_layout);
        shuoming_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SetActivity.this,ShuomingActivity.class);
                startActivity(intent);
            }
        });
    }
}
