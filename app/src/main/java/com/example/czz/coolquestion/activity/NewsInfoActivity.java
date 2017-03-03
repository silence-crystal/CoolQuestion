package com.example.czz.coolquestion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ProgrammerNews;

public class NewsInfoActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;
    private WebView wv;
    private ProgrammerNews pn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        iv_back= (ImageView) findViewById(R.id.imageView_newsinfo_back);
        wv= (WebView) findViewById(R.id.newsinfo_webview);
        pn=(ProgrammerNews) getIntent().getSerializableExtra("info");
        String path=pn.getNewspic();
        wv.loadUrl(path);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String request) {
                wv.loadUrl(request);
                return true;
            }
        });

    }

    //监听事件
    public void InitListener(){
        iv_back.setOnClickListener(this);
    }

    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView_newsinfo_back://返回
                finish();
                break;
            default:
                break;

        }
    }





}
