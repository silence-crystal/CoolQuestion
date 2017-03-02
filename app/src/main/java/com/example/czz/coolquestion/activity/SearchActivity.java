package com.example.czz.coolquestion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.czz.coolquestion.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        tv_back= (TextView) findViewById(R.id.search_back);
    }

    //监听事件
    public void InitListener(){
        tv_back.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.search_back:
                finish();
                break;
            default:
                break;
        }
    }
}
