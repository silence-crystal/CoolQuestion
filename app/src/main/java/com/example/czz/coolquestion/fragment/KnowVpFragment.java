package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.KnowDetailsActivity;
import com.example.czz.coolquestion.adapter.KnowAdapter;
import com.example.czz.coolquestion.bean.Know;
import com.example.czz.coolquestion.utils.PullToRefreshBase;
import com.example.czz.coolquestion.utils.PullToRefreshScrollView;
import com.example.czz.coolquestion.view.MyListView;


import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 * 知识点问题清单
 */

public class KnowVpFragment extends Fragment implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener2{
    private View view;
    private MyListView lv_knowVp;
    private PullToRefreshScrollView scrollView;
    private int position;
    private List list;
    private int currentIndex=1;
    private RequestQueue queue;
    private KnowAdapter adapter;
    //Java语言
    private final String Java = "";
    //C语言
    private final String C = "";
    //C++语言
    private final String Cadd = "";
    //C#语言
    private final String CSP = "";
    //Android语言
    private final String Android = "";
    //Php语言
    private final String Php = "";
    //Python语言
    private final String Python = "";
    //ios语言
    private final String ios = "";
    //Wed语言
    private final String Web = "";
    //Html5语言
    private final String Html5 = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.knowvp_far,null);
        initRefresh();
        initListView();
        initData();
        initListData();
        return view;
    }
    //根据判断获取不同数据
    private void initData() {
//        if (position==0){
//            initListData(Java);
//        }else if (position==1){
//            initListData(C);
//        }else if (position==2){
//            initListData(Cadd);
//        }else if (position==3){
//            initListData(CSP);
//        }else if (position==4){
//            initListData(Android);
//        }else if (position==5){
//            initListData(Php);
//        }else if (position==6){
//            initListData(Python);
//        }else if (position==7){
//            initListData(ios);
//        }else if (position==8){
//            initListData(Web);
//        }else if (position==9){
//            initListData(Html5);
//        }
    }

    //初始化刷新
    private void initRefresh() {
        scrollView= (PullToRefreshScrollView) view.findViewById(R.id.know_la);
        scrollView.setMode(PullToRefreshBase.Mode.BOTH);
        scrollView.setOnRefreshListener(this);
    }
    //初始化listView
    private void initListView() {
        lv_knowVp=(MyListView) view.findViewById(R.id.lv_knowVp);
        adapter=new KnowAdapter(getActivity());
        lv_knowVp.setAdapter(adapter);
        lv_knowVp.setOnItemClickListener(this);
    }
    //获取listView数据
    private void initListData() {
        List<Know> list=new ArrayList<Know>();
        for (int i=0;i<10;i++){
            Know know=new Know();
            know.setTimu("中华人民共和国万岁？？？？"+i);
            list.add(know);
        }
        Toast.makeText(getActivity(),list.size()+"",Toast.LENGTH_SHORT).show();
        adapter.setList(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(getActivity(), KnowDetailsActivity.class);
        startActivity(intent);
    }
    //下拉
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        initData();
    }
    //上拉
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

    }


}
