package com.example.czz.coolquestion.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ProgrammerNews;
import com.example.czz.coolquestion.bean.ProgrammerNewsCol;
import com.example.czz.coolquestion.url.URLConfig;

import org.json.JSONException;
import org.json.JSONObject;

public class NewsInfoColActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;
    private WebView wv;
    private ProgrammerNewsCol.NewsCollectListBean nb;

    //private RequestQueue rq;

    private int idd;//用户id

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_info_col);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){

        //rq= Volley.newRequestQueue(getApplicationContext());

        iv_back= (ImageView) findViewById(R.id.imageView_newsinfo_back);

        wv= (WebView) findViewById(R.id.newsinfo_webview);
        nb= (ProgrammerNewsCol.NewsCollectListBean) getIntent().getSerializableExtra("info");
        //final String path=pn.getNewsdetails();
        // Log.i("555555555",path+"");

        WebSettings settings=wv.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);
        settings.setCacheMode(WebSettings.LOAD_NO_CACHE);
        settings.setJavaScriptCanOpenWindowsAutomatically(true);


//        //settings.setJavaScriptCanOpenWindowsAutomatically(true);//设置js可以直接打开窗口，如window.open()，默认为false
//        //settings.setJavaScriptEnabled(true);//是否允许执行js，默认为false。设置true时，会提醒可能造成XSS漏洞
//        settings.setSupportZoom(true);//是否可以缩放，默认true
//        settings.setBuiltInZoomControls(true);//是否显示缩放按钮，默认false
//        settings.setUseWideViewPort(true);//设置此属性，可任意比例缩放。大视图模式
//        settings.setLoadWithOverviewMode(true);//和setUseWideViewPort(true)一起解决网页自适应问题
//        //settings.setAppCacheEnabled(true);//是否使用缓存
//        settings.setDomStorageEnabled(true);//DOM Storage



//        WebSettings webSettings = wv.getSettings();
//        settings.setJavaScriptCanOpenWindowsAutomatically(true);
//        settings.setJavaScriptEnabled(true);
//        settings.setPluginState(WebSettings.PluginState.ON);
//        settings.setAllowFileAccess(true);
//        settings.setLoadWithOverviewMode(true);
//        settings.setSupportZoom(true);
//        settings.setSupportMultipleWindows(true);
//        settings.setBuiltInZoomControls(true);
//        settings.setAppCacheEnabled(true);
//        settings.setAppCachePath("/data/data" + getPackageName()
//                + "/app_path/");
//        settings.setLoadsImagesAutomatically(true);
//        settings.setSavePassword(true);
//        settings.setLightTouchEnabled(true);
//        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);
//        settings.setBlockNetworkImage(true);
//        settings.setUseWideViewPort(true);

//        wv.onResume();



        idd=nb.getNewscontent().getNewsId();
        String path ="http://"+ URLConfig.MAIN_URL+":8080/CoolTopic/NewsContent.jsp?nid="+idd;
        wv.loadUrl(path);
        wv.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
//                Log.d("111",path);
//                Log.d("222",url);
//                if (url.startsWith("ucweb://")){
//                    url = url.substring(8);
//                }
                view.loadUrl(url);
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
