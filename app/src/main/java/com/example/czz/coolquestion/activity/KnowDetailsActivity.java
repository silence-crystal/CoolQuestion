package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.Know;
import com.example.czz.coolquestion.bean.KnowledgeCollect;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Administrator on 2017/3/1.
 * 知识点详情
 */

public class KnowDetailsActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView know_details_hand_img,you_img;
    private TextView know_collect_tv,know_fenxiang_tv;
   // private View popview;
    //private PopupWindow pw;
    private RequestQueue rq;
    private Volley volley;
    private int id1;
    private WebView wv_knowledge;
    private ImageView iv_share,iv_col;
//    private KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean know1=new KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //接收传递的参数
        Intent intent = getIntent();
        setContentView(R.layout.activity_know_details);
        Know.KnowledgeListBean know = (Know.KnowledgeListBean) intent.getSerializableExtra("content");
        KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean knowCol = (KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean) intent.getSerializableExtra("colContent");
        if (know!=null){
            id1=know.getKnowledgeid();
        }else {
            id1 = knowCol.getKnowledgeid();
        }

        InitView();
        InitListener();
        rq=Volley.newRequestQueue(getApplicationContext());



    }
    //各种控件
    public void InitView(){
        know_details_hand_img= (ImageView) findViewById(R.id.know_details_hand_img);
        wv_knowledge = (WebView) findViewById(R.id.wv_knowledge);
        //you_img= (ImageView) findViewById(R.id.you_img);

        iv_col= (ImageView) findViewById(R.id.know_col);
        iv_share= (ImageView) findViewById(R.id.know_share);
        //popview=getLayoutInflater().inflate(R.layout.know_xiangqing_popupwindow,null);
//        know_collect_tv= (TextView) popview.findViewById(R.id.know_collect_tv);
//        know_fenxiang_tv= (TextView) popview.findViewById(R.id.know_fenxiang_tv);

        String path ="http://"+ URLConfig.MAIN_URL+":8080/CoolTopic/KnowledgeContent.jsp?kid="+id1;
        Log.d("333",path);
        wv_knowledge.loadUrl(path);
        wv_knowledge.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
    //监听事件
    public void InitListener(){
        know_details_hand_img.setOnClickListener(this);
        iv_share.setOnClickListener(this);
        iv_col.setOnClickListener(this);
        //you_img.setOnClickListener(this);
//        know_collect_tv.setOnClickListener(this);
//        know_fenxiang_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.know_details_hand_img://返回
                finish();
                break;
//            case R.id.you_img:
////                pw = new PopupWindow(popview, 150, ViewGroup.LayoutParams.WRAP_CONTENT);
////                pw.setBackgroundDrawable(getResources().getDrawable(R.mipmap.know_popupwindow));
////                pw.setOutsideTouchable(true);
////                pw.showAsDropDown(you_img);
//                break;
            case R.id.know_col://收藏
                ACache aCache=ACache.get(KnowDetailsActivity.this);
                UserInfo.UserInfoBean userInfo= (UserInfo.UserInfoBean) aCache.getAsObject("user");
                if(userInfo!=null){
                    //
                    JsonObjectRequest jor=new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/AddKnowledgeCollect?kid="+id1+"&uid="+userInfo.getUserId(),
                            null,new Response.Listener<JSONObject>(){
                        public void onResponse(JSONObject response) {
                            try {
                                if (response.getString("reason").length()==0){
                                    Toast.makeText(KnowDetailsActivity.this,"收藏成功",Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText(KnowDetailsActivity.this,response.getString("reason"),Toast.LENGTH_SHORT).show();
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },new Response.ErrorListener(){
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(KnowDetailsActivity.this,error.getCause().toString(),Toast.LENGTH_SHORT).show();
                        }
                    });
                    rq.add(jor);
                    rq.start();
                }else{
                    //跳转到登录界面
                    Intent intent=new Intent(KnowDetailsActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.know_share://分享
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
                String fname = "/sdcard/" + sdf.format(new Date()) + ".png";
                View view = v.getRootView();
                view.setDrawingCacheEnabled(true);
                view.buildDrawingCache();
                Bitmap bitmap = view.getDrawingCache();
                if (bitmap != null) {
                    System.out.println("bitmapgot!");
                    try {
                        FileOutputStream out = new FileOutputStream(fname);
                        bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
                        Uri imageUri = Uri.fromFile(new File(fname));
                        Intent shareIntent = new Intent();
                        shareIntent.setAction(Intent.ACTION_SEND);
                        shareIntent.putExtra(Intent.EXTRA_STREAM, imageUri);
                        shareIntent.setType("image/*");
                        startActivity(Intent.createChooser(shareIntent, "分享到"));
                        System.out.println("file" + fname + "所得图片");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("bitmap is NULL!");
                }
                break;
            default:
                break;
        }
    }


}
