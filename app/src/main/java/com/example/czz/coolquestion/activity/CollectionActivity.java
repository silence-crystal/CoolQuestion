package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.adapter.ProgrammerAdapter;
import com.example.czz.coolquestion.adapter.ProgrammerColAdapter;
import com.example.czz.coolquestion.bean.Programmer;
import com.example.czz.coolquestion.bean.ProgrammerNews;
import com.example.czz.coolquestion.bean.ProgrammerNewsCol;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

//收藏界面
public class CollectionActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener{

    private ImageView iv_back,iv_share;
    private ListView lv;
    private ProgrammerColAdapter adapter;
    private List<ProgrammerNewsCol.NewsCollectListBean> list=new ArrayList<ProgrammerNewsCol.NewsCollectListBean>();
    private RequestQueue rq;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        iv_share= (ImageView) findViewById(R.id.imageView1_left_col_right);
        iv_back= (ImageView) findViewById(R.id.imageView2_left_col_back);
        lv= (ListView) findViewById(R.id.listview_news_col);
        adapter=new ProgrammerColAdapter(CollectionActivity.this);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
        rq= Volley.newRequestQueue(getApplicationContext());
        InitCol();

        registerForContextMenu(lv);

    }

    //长按删除
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0,1,1,"删除");
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        final AdapterView.AdapterContextMenuInfo info= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int id= (int) info.id;
        if (id==-1){
            super.onContextItemSelected(item);
        }

        switch (item.getItemId()){
            case 1:
                ProgrammerNewsCol.NewsCollectListBean nbb=list.get(id);
                int t=nbb.getNewscollectid();

                JsonObjectRequest jor=new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/DeleteNewsCollect?ncid="+t,null,new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(CollectionActivity.this,response.getString("result"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(CollectionActivity.this,error.getCause().toString(),Toast.LENGTH_LONG).show();
                    }
                });
                rq.add(jor);
                rq.start();


                //adapter.setList(list);
                InitCol();
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);


                break;
            default:
                break;
        }

        return super.onContextItemSelected(item);


    }

    //收藏查询
    public void InitCol(){
        ACache aCache=ACache.get(CollectionActivity.this);
        UserInfo.UserInfoBean userInfoBean= (UserInfo.UserInfoBean) aCache.getAsObject("user");
        JsonObjectRequest jor=new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetNewsCollectByUid?page=1&size=100&uid="+userInfoBean.getUserId(),null,new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject response) {
                String info=response.toString();
                Gson gson=new Gson();
                ProgrammerNewsCol pnc=gson.fromJson(info,ProgrammerNewsCol.class);
                List<ProgrammerNewsCol.NewsCollectListBean> ll=pnc.getNewsCollectList();

//                Programmer p=gson.fromJson(info,Programmer.class);
//                List<ProgrammerNews> ll=p.getNews();
                list=ll;
                adapter.setList(ll);
                adapter.notifyDataSetChanged();
            }
        },new Response.ErrorListener(){


            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CollectionActivity.this,error.getCause().toString(),Toast.LENGTH_LONG).show();
            }
        });
        rq.add(jor);
        rq.start();


}

    //监听事件
    public void InitListener(){
        iv_back.setOnClickListener(this);
        iv_share.setOnClickListener(this);
    }


    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView2_left_col_back:
                finish();
                break;
            case R.id.imageView1_left_col_right:
                SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss", Locale.US);
                String fname = "/sdcard/" + sdf.format(new Date()) + ".png";
                View v = view.getRootView();
                v.setDrawingCacheEnabled(true);
                v.buildDrawingCache();
                Bitmap bitmap = v.getDrawingCache();
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


    //传值
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(CollectionActivity.this,NewsInfoColActivity.class);
        ProgrammerNewsCol.NewsCollectListBean nb= (ProgrammerNewsCol.NewsCollectListBean) adapter.getItem(i);
        intent.putExtra("info",nb);
        startActivity(intent);


    }
}
