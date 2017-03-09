package com.example.czz.coolquestion.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;

/**
 * Created by dell on 2017/3/6.
 */

public class QuestionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_img,share_img;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_questiondetail);
        IniteView();
    }

    private void IniteView() {
        back_img= (ImageView) findViewById(R.id.act_questiondetail_back);
        back_img.setOnClickListener(this);
        share_img= (ImageView) findViewById(R.id.act_questiondetail_share);
        share_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_questiondetail_back:
                finish();
                break;
            case R.id.act_questiondetail_share:
                Toast.makeText(QuestionDetailActivity.this,"分享功能还没有实现",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
