package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.example.czz.coolquestion.adapter.KnowledgeCollectAdapter;
import com.example.czz.coolquestion.bean.KnowledgeCollect;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

//知识点收藏
public class KnowledgeActivity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemClickListener {
    private KnowledgeCollectAdapter adapter;
    private ImageView iv_back;
    private ListView lv;
    private RequestQueue rq;
    private List<KnowledgeCollect.KnowledgeCollectListBean> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        iv_back= (ImageView) findViewById(R.id.imageView2_left_knowledge_back);
        lv= (ListView) findViewById(R.id.knowledge_lv);
        adapter=new KnowledgeCollectAdapter(this);
        lv.setAdapter(adapter);
        rq= Volley.newRequestQueue(getApplicationContext());
        lv.setOnItemClickListener(this);
        initchaxun();
    }

    //监听事件
    public void InitListener(){
        iv_back.setOnClickListener(this);
    }
    //查询数据
    public void initchaxun(){

            JsonObjectRequest jor=new JsonObjectRequest("http://130.0.0.227:8080/CoolTopic/GetKnowledgeCollectByUid?page=1&size=3&uid=1", null,new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject response) {
                    String info=response.toString();
                    Gson gson=new Gson();
                    KnowledgeCollect knowledgeCollect = gson.fromJson(info,KnowledgeCollect.class);
                    list=knowledgeCollect.getKnowledgeCollectList();
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(KnowledgeActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                }
            });
            rq.add(jor);
            rq.start();

    }


    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView2_left_knowledge_back:
                finish();
                break;
            default:
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(KnowledgeActivity.this, KnowDetailsActivity.class);
        KnowledgeCollect.KnowledgeCollectListBean.KnowledgeBean k = list.get(position).getKnowledge();
        intent.putExtra("colContent",k);
        startActivity(intent);
    }
}
