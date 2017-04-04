package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.adapter.KnowledgeCollectAdapter;
import com.example.czz.coolquestion.bean.KnowledgeCollect;
import com.example.czz.coolquestion.bean.ProgrammerNewsCol;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;
import com.google.gson.Gson;

import org.json.JSONException;
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
    private ProgressBar progressBar;//加载网络数据的进度条
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_knowledge);
        InitView();
        InitListener();
        registerForContextMenu(lv);
    }

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
                KnowledgeCollect.KnowledgeCollectListBean nbb=list.get(id);

                int t=nbb.getKnowledgecollectid();

                JsonObjectRequest jor=new JsonObjectRequest("http://"+ URLConfig.MAIN_URL+":8080/CoolTopic/DeleteKnowledgeCollect?kcid="+t,null,new Response.Listener<JSONObject>(){

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Toast.makeText(KnowledgeActivity.this,response.getString("result"),Toast.LENGTH_LONG).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },new Response.ErrorListener(){

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(KnowledgeActivity.this,error.getCause().toString(),Toast.LENGTH_LONG).show();
                    }
                });
                rq.add(jor);
                rq.start();


                //adapter.setList(list);
                initchaxun();
                adapter.notifyDataSetChanged();
                lv.setAdapter(adapter);


                break;
            default:
                break;
        }


        return super.onContextItemSelected(item);
    }

    //各种控件
    public void InitView(){
        iv_back= (ImageView) findViewById(R.id.imageView2_left_knowledge_back);
        progressBar= (ProgressBar) findViewById(R.id.act_know_progressbar);
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
        ACache aCache = ACache.get(this);
        UserInfo.UserInfoBean user = (UserInfo.UserInfoBean) aCache.getAsObject("user");
        int uid = user.getUserId();
            JsonObjectRequest jor=new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeCollectByUid?page=1&size=100&uid="+uid, null,new Response.Listener<JSONObject>(){
                public void onResponse(JSONObject response) {
                    String info=response.toString();
                    Gson gson=new Gson();
                    KnowledgeCollect knowledgeCollect = gson.fromJson(info,KnowledgeCollect.class);
                    list=knowledgeCollect.getKnowledgeCollectList();
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter.setList(list);
                    adapter.notifyDataSetChanged();
                }
            },new Response.ErrorListener(){

                @Override
                public void onErrorResponse(VolleyError error) {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(KnowledgeActivity.this,"网络异常",Toast.LENGTH_LONG).show();
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
