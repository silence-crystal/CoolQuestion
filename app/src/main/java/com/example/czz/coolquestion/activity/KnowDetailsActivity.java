package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
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
    private TextView k_title,k_content,know_collect_tv,know_fenxiang_tv;
    private View popview;
    private PopupWindow pw;
    private RequestQueue rq;
    private Volley volley;
    private int id1;
//    private KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean know1=new KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_details);
        InitView();
        InitListener();
        rq=Volley.newRequestQueue(getApplicationContext());
        Intent intent = getIntent();
        Know.KnowledgeListBean know = (Know.KnowledgeListBean) intent.getSerializableExtra("content");
        KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean knowCol = (KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean) intent.getSerializableExtra("colContent");
        if (know!=null){
            id1=know.getKnowledgeid();
            k_title.setText(know.getKnowledgetitle());
            k_content.setText(know.getKnowledgedescribe());
        }else {
            id1 = knowCol.getKnowledgeid();
            k_title.setText(knowCol.getKnowledgetitle());
            k_content.setText(knowCol.getKnowledgedescribe());
        }

    }
    //各种控件
    public void InitView(){
        know_details_hand_img= (ImageView) findViewById(R.id.know_details_hand_img);
        k_title=(TextView) findViewById(R.id.knowledge_title);
        k_content=(TextView) findViewById(R.id.knowledge_content);
        you_img= (ImageView) findViewById(R.id.you_img);
        popview=getLayoutInflater().inflate(R.layout.know_xiangqing_popupwindow,null);
        know_collect_tv= (TextView) popview.findViewById(R.id.know_collect_tv);
        know_fenxiang_tv= (TextView) popview.findViewById(R.id.know_fenxiang_tv);
    }
    //监听事件
    public void InitListener(){
        know_details_hand_img.setOnClickListener(this);
        you_img.setOnClickListener(this);
        know_collect_tv.setOnClickListener(this);
        know_fenxiang_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.know_details_hand_img:
                finish();
                break;
            case R.id.you_img:
                pw = new PopupWindow(popview, 150, ViewGroup.LayoutParams.WRAP_CONTENT);
                pw.setBackgroundDrawable(getResources().getDrawable(R.mipmap.know_popupwindow));
                pw.setOutsideTouchable(true);
                pw.showAsDropDown(you_img);
                break;
            case R.id.know_collect_tv:
                if(isLogin()==true){
                    //
                    JsonObjectRequest jor=new JsonObjectRequest("http://130.0.0.227:8080/CoolTopic/AddKnowledgeCollect?kid="+id1+"&uid=1",
                            null,new Response.Listener<JSONObject>(){
                        public void onResponse(JSONObject response) {
                            try {
                                Toast.makeText(KnowDetailsActivity.this,response.getString("reason"),Toast.LENGTH_LONG).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    },new Response.ErrorListener(){
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(KnowDetailsActivity.this,error.getCause().toString(),Toast.LENGTH_LONG).show();
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
            case R.id.know_fenxiang_tv:
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

    //自定义登录验证方法
    public boolean isLogin(){
        return true;
    }
}
