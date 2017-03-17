package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.KnowDetailsActivity;
import com.example.czz.coolquestion.adapter.KnowAdapter;
import com.example.czz.coolquestion.bean.Know;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.PullToRefreshBase;
import com.example.czz.coolquestion.utils.PullToRefreshScrollView;
import com.example.czz.coolquestion.view.MyListView;
import com.google.gson.Gson;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItem;


import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 * 知识点问题清单
 */

public class KnowVpFragment extends Fragment implements AdapterView.OnItemClickListener, PullToRefreshBase.OnRefreshListener2 {
    private View view;
    private MyListView lv_knowVp;
    private ProgressBar progressBar;//读取网络数据时的进度条
    private PullToRefreshScrollView scrollView;
    private int position;
    private List<Know.KnowledgeListBean> list = new ArrayList<Know.KnowledgeListBean>();
    private KnowAdapter adapter;
    private RequestQueue queue;
    private Know know = null;
    private int currentPage=1;
    //private ProgressBar pb;
    //Java语言
    private final String Java = "http://"+ URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=1&page=1&size=10";
    //C语言
    private final String C = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=2&page=1&size=10";
    //C++语言
    private final String Cadd = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=3&page=1&size=10";
    //Android语言
    private final String Android = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=4&page=1&size=10";
    //Object-C语言
    private final String Objectc = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=5&page=1&size=10";
    //PHP语言
    private final String PHP = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=6&page=1&size=10";

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {
            if (msg.what == 0) {
                adapter.notifyDataSetChanged();
                scrollView.onRefreshComplete();
            }
        }
    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.knowvp_far, null);
        queue = Volley.newRequestQueue(getActivity());
        initRefresh();
        initListView();
        initData();

        return view;
    }

    //根据判断获取不同数据
    private void initData() {
        position = FragmentPagerItem.getPosition(getArguments());
        if (position == 0) {
            initListData(Java);
        } else if (position == 1) {
            initListData(C);
        } else if (position == 2) {
            initListData(Cadd);
        } else if (position == 3) {
            initListData(Android);
        } else if (position == 4) {
            initListData(Objectc);
        } else if (position == 5) {
            initListData(PHP);
        }
    }


    //初始化刷新
    private void initRefresh() {
        scrollView = (PullToRefreshScrollView) view.findViewById(R.id.know_la);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(this);
    }


    //获取listView数据
    private void initListData(String url) {
       // pb.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        JsonObjectRequest request = new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String info = response.toString();
                Gson gson = new Gson();
                Know know = gson.fromJson(info, Know.class);
                list = know.getKnowledgeList();
                adapter.setList(list);
                adapter.notifyDataSetChanged();
                if (scrollView.isRefreshing()) {
                    scrollView.onRefreshComplete();
                }
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (scrollView.isRefreshing()) {
                    scrollView.onRefreshComplete();
                }
                Toast.makeText(getActivity(), "请求数据失败", Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        queue.add(request);
        queue.start();
    }

    //初始化listView
    private void initListView() {
        lv_knowVp = (MyListView) view.findViewById(R.id.lv_knowVp);
        progressBar= (ProgressBar) view.findViewById(R.id.juhua);
        adapter = new KnowAdapter(getActivity());
        lv_knowVp.setAdapter(adapter);
        lv_knowVp.setOnItemClickListener(this);
        //pb= (ProgressBar) view.findViewById(R.id.pb_juhua1);
    }

    //下拉
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        currentPage=1;
        initData();
        Toast.makeText(getActivity(),"lalala",Toast.LENGTH_SHORT).show();
    }

    //上拉
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {
        String currentUrl = null;
        currentPage+=1;
        if (position == 0) {
            currentUrl = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=1&size=10&page=";
        } else if (position == 1) {
            currentUrl = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=2&size=10&page=";
        } else if (position == 2) {
            currentUrl = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=3&size=10&page=";
        } else if (position == 3) {
            currentUrl = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=4&size=10&page=";
        } else if (position == 4) {
            currentUrl = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=5&size=10&page=";
        } else if (position == 5) {
            currentUrl = "http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetKnowledgeByTid?tid=6&size=10&page=";
        }
        JsonObjectRequest jor = new JsonObjectRequest(currentUrl+currentPage, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                String info = response.toString();
                Gson gson = new Gson();
                Know know = gson.fromJson(info, Know.class);
                List<Know.KnowledgeListBean> ll = know.getKnowledgeList();
                adapter.addDataToFooter(ll);
                adapter.notifyDataSetChanged();
                scrollView.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                if (scrollView.isRefreshing()) {
                    scrollView.onRefreshComplete();
                }
                Toast.makeText(getActivity(), "网络异常", Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jor);
        queue.start();
//        initData();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), KnowDetailsActivity.class);
        Know.KnowledgeListBean k = (Know.KnowledgeListBean) adapter.getItem(position);
        intent.putExtra("content", k);
        startActivity(intent);
    }

}
