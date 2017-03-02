package com.example.czz.coolquestion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;

public class TopicActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topic);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        iv_back= (ImageView) findViewById(R.id.imageView2_left_topic_back);

    }

    //监听事件
    public void InitListener(){
        iv_back.setOnClickListener(this);
    }


    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView2_left_topic_back:
                finish();
                break;
            default:
                break;

        }

    }
}
