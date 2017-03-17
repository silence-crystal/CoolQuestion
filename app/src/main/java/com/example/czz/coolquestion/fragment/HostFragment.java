package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.MainActivity;
import com.example.czz.coolquestion.activity.NewsInfoActivity;
import com.example.czz.coolquestion.activity.SearchActivity;
import com.example.czz.coolquestion.adapter.ProgrammerAdapter;
import com.example.czz.coolquestion.bean.Programmer;
import com.example.czz.coolquestion.bean.ProgrammerNews;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.PullToRefreshBase;
import com.example.czz.coolquestion.utils.PullToRefreshListView;
import com.example.czz.coolquestion.utils.PullToRefreshScrollView;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONException;

/**
 * Created by dell on 2017/2/28.
 */

public class HostFragment extends Fragment implements AdapterView.OnItemClickListener,PullToRefreshBase.OnRefreshListener2{


    private ViewPager viewPager;
    private LinearLayout point_group;
    private TextView image_desc;
    private ListView lv;
    private PullToRefreshScrollView sv;
    private ProgrammerAdapter adapter;
    private ImageView iv_search,iv_sm;
    private RequestQueue rq;
    private  List<ProgrammerNews> ll;
    private  int curpage=1;
    private ProgressBar pb;



    // 图片资源id
    private final int[] images = { R.mipmap.ke_oneo,R.mipmap.ke_two,R.mipmap.ke_three,R.mipmap.ke_four,R.mipmap.ke_five};
    // 图片标题集合
    private final String[] imageDescriptions = { "Android时代的到来“大爆炸”",
            "程序“锁”，让代码连着跑步", "三星note7终获首杀", "WiFi时代，你我之间的联系", "互联地球村，数据大链接" };

    private ArrayList<ImageView> imageList;
    // 上一个页面的位置
    protected int lastPosition = 0;

    // 判断是否自动滚动viewPager
    private boolean isRunning = true;
    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {
            // 执行滑动到下一个页面
            viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
            if (isRunning) {
                // 在发一个handler延时
                handler.sendEmptyMessageDelayed(0, 5000);
            }

        };
    };



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

       View view=inflater.inflate(R.layout.host_fra,null);

        InitView(view);
        InitViewPager(view);

        return view;

    }

    //控件
    public void InitView(View view){
        //科技资讯
        pb= (ProgressBar) view.findViewById(R.id.pb_juhua);
        lv= (ListView) view.findViewById(R.id.hp_listview);
        ll=new ArrayList<ProgrammerNews>();
        adapter=new ProgrammerAdapter(getActivity());

        rq= Volley.newRequestQueue(getActivity());
        sv= (PullToRefreshScrollView) view.findViewById(R.id.scrollView_sv);
        sv.setMode(PullToRefreshBase.Mode.BOTH);
        sv.setOnRefreshListener(this);
        lv.setOnItemClickListener(this);
        lv.setAdapter(adapter);
        lv.setFocusable(false);
        AddData();

        //搜索
        iv_search= (ImageView) view.findViewById(R.id.imageView_hp_right_top);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (ll.size()==0){
                    Toast.makeText(getActivity(),"当前没有新闻数据!!",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent=new Intent(getActivity(), SearchActivity.class);
                    intent.putExtra("news_list",(Serializable) ll);
                    startActivity(intent);
                }

            }
        });


        //进入侧滑界面
        iv_sm= (ImageView) view.findViewById(R.id.imageView_hp_left_top);
        iv_sm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((MainActivity) getActivity()).slidingmenu.isMenuShowing()){
                    ((MainActivity) getActivity()).slidingmenu.showContent();
                }else {
                    ((MainActivity) getActivity()).slidingmenu.showMenu();
                }
            }
        });
    }

    //顶部ViewPager
    public void InitViewPager(View view){

        viewPager = (ViewPager) view.findViewById(R.id.viewPager);
        point_group = (LinearLayout) view.findViewById(R.id.point_group);
        image_desc = (TextView) view.findViewById(R.id.image_desc);
        image_desc.setText(imageDescriptions[0]);

        // 初始化图片资源
        imageList = new ArrayList<ImageView>();
        for (int i : images) {
            // 初始化图片资源
            ImageView imageView = new ImageView(getActivity());
            imageView.setBackgroundResource(i);
            imageList.add(imageView);

            // 添加指示小点
            ImageView point = new ImageView(getActivity());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(5,
                    5);
            params.rightMargin = 20;
            point.setLayoutParams(params);
            // point.setBackgroundResource(R.mipmap.pointp);
            if (i == R.mipmap.ic_launcher) {
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
            }

            point_group.addView(point);
        }

        viewPager.setAdapter(new MyPageAdapter());
        // 设置当前viewPager的位置
        viewPager.setCurrentItem(Integer.MAX_VALUE / 2
                - (Integer.MAX_VALUE / 2 % imageList.size()));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                // 页面切换后调用， position是新的页面位置

                // 实现无限制循环播放
                position %= imageList.size();

                image_desc.setText(imageDescriptions[position]);

                // 把当前点设置为true,将上一个点设为false
                point_group.getChildAt(position).setEnabled(true);
                point_group.getChildAt(lastPosition).setEnabled(false);
                lastPosition = position;

            }

            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
                // 页面正在滑动时间回调

            }

            @Override
            public void onPageScrollStateChanged(int state) {
                // 当pageView 状态发生改变的时候，回调

            }
        });

        /**
         * 自动循环： 1.定时器：Timer 2.开子线程：while true循环 3.ClockManger
         * 4.用Handler发送延时信息，实现循环，最简单最方便
         *
         */

        handler.sendEmptyMessageDelayed(0, 5000);


    }

    //科技资讯list实现方法
    public void AddData() {
       pb.setVisibility(View.VISIBLE);
        JsonObjectRequest jor = new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetAllNews?page=1&size=10", null, new Response.Listener<JSONObject>(){


            @Override
            public void onResponse(JSONObject jsonObject) {


                String info=jsonObject.toString();
                Gson gson=new Gson();
                Programmer p=gson.fromJson(info,Programmer.class);
                ll=p.getNews();
                adapter.setList(ll);
                adapter.notifyDataSetChanged();
                pb.setVisibility(View.GONE);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"请求失败！",Toast.LENGTH_LONG).show();
                pb.setVisibility(View.GONE);
            }
        });
        rq.add(jor);
        rq.start();
    }




    @Override
    public void onDestroy() {
        // 停止滚动
        isRunning = false;
        super.onDestroy();
    }


    //传值监听
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(getActivity(), NewsInfoActivity.class);
        ProgrammerNews pn=(ProgrammerNews) adapter.getItem(i);
        //Toast.makeText(getActivity(),"--"+i,Toast.LENGTH_LONG).show();
        intent.putExtra("info",pn);
        startActivity(intent);
    }

    //下拉
    @Override
    public void onPullDownToRefresh(PullToRefreshBase refreshView) {
        curpage=1;
        JsonObjectRequest jor = new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetAllNews?page=1&size=10", null, new Response.Listener<JSONObject>(){


            @Override
            public void onResponse(JSONObject jsonObject) {

                String info=jsonObject.toString();
                Gson gson=new Gson();
                Programmer p=gson.fromJson(info,Programmer.class);
                ll=p.getNews();
                adapter.setList(ll);
                adapter.notifyDataSetChanged();
                sv.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"请求失败！",Toast.LENGTH_LONG).show();
            }
        });
        rq.add(jor);
        rq.start();

    }

    //上拉
    @Override
    public void onPullUpToRefresh(PullToRefreshBase refreshView) {

        curpage+=1;
        JsonObjectRequest jor = new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetAllNews?size=10&page="+curpage, null, new Response.Listener<JSONObject>(){

            @Override
            public void onResponse(JSONObject jsonObject) {
                String info=jsonObject.toString();
                Gson gson=new Gson();
                Programmer p=gson.fromJson(info,Programmer.class);
                List<ProgrammerNews> ll=p.getNews();
                adapter.addDataToFooter(ll);
                adapter.notifyDataSetChanged();
                sv.onRefreshComplete();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                Toast.makeText(getActivity(),"请求失败！",Toast.LENGTH_LONG).show();
            }
        });
        rq.add(jor);
        rq.start();

    }






    public class MyPageAdapter extends PagerAdapter {
        // 需要实现以下四个方法

        @Override
        public int getCount() {
            // 获得页面的总数
            return Integer.MAX_VALUE;
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            // 判断view和Object对应是否有关联关系
            if (view == object) {
                return true;
            } else {
                return false;
            }
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // 获得相应位置上的view； container view的容器，其实就是viewpage自身,
            // position: viewpager上的位置
            // 给container添加内容
            container.addView(imageList.get(position % imageList.size()));

            return imageList.get(position % imageList.size());
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // 销毁对应位置上的Object
            // super.destroyItem(container, position, object);
            container.removeView((View) object);
            object = null;
        }

    }




}
