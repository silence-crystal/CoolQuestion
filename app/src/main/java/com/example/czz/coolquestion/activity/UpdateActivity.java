package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/6.
 */

public class UpdateActivity extends Activity{
    private ImageView update_hand_img;
    private TextView update_baocun_tv;
    private View update_useraccount_layout;
    private Intent intent;//用来接收传递过来的intent
    private EditText update_username_et,update_userqq_et,update_userphone_et,update_useraddress_et;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
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

        intent=getIntent();
        update_username_et.setText(intent.getStringExtra("name"));
        update_userqq_et.setText(intent.getStringExtra("qq"));
        update_userphone_et.setText(intent.getStringExtra("phone"));
        update_useraddress_et.setText(intent.getStringExtra("address"));
        update_baocun_tv= (TextView) findViewById(R.id.update_baocun_tv);
        update_baocun_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strname=update_username_et.getText().toString().trim();
                String strqq=update_userqq_et.getText().toString().trim();
                String strphone=update_userphone_et.getText().toString().trim();
                String straddress=update_useraddress_et.getText().toString().trim();
                Intent intent=new Intent();
                intent.putExtra("srt_name",strname);
                intent.putExtra("srt_qq",strqq);
                intent.putExtra("srt_phone",strphone);
                intent.putExtra("srt_address",straddress);
                setResult(RESULT_OK,intent);
                finish();
                Toast.makeText(UpdateActivity.this,"保存成功！",Toast.LENGTH_SHORT).show();
            }
        });

    }
}
