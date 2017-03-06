package com.example.czz.coolquestion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;


import com.example.czz.coolquestion.R;

public class SearchActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_search;
    private EditText et_search;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){

        iv_search= (ImageView) findViewById(R.id.imageView_search_cai);
        et_search= (EditText) findViewById(R.id.editText_search);

    }

    //监听事件
    public void InitListener(){
        iv_search.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_search_cai:
            finish();

                break;
            default:
                break;
        }
    }

    //点击空白处隐藏键盘
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }


}
