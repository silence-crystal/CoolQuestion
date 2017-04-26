package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.CTBActivity;
import com.example.czz.coolquestion.activity.GoQuestionActivity;
import com.example.czz.coolquestion.activity.LoginActivity;
import com.example.czz.coolquestion.activity.MainActivity;
import com.example.czz.coolquestion.adapter.Question_GV_Adapter;
import com.example.czz.coolquestion.bean.Language;
import com.example.czz.coolquestion.bean.UserInfo;
import com.example.czz.coolquestion.url.URLConfig;
import com.example.czz.coolquestion.utils.ACache;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell on 2017/2/28.
 */

public class QuestionFragment extends Fragment implements View.OnClickListener {

    private ImageView left_slidingmenu_img, right_error_collect_img;//侧滑页面和错题本页面的跳转按钮
    private MainActivity mainactivity;//为了拿到Activity的slidingmenu
    private List<Language.TypeListBean> list;//存放所有编程语言的集合
    private GridView gridView;//显计算机语言的网格视图
    private Question_GV_Adapter question_gv_adapter;//显示计算机语言的网格视图的适配器
    private Button go_dati_question_btn, go_kaoshi_question_btn;
    private RequestQueue queue;//用来请求网络数据
    private ImageLoader imageLoader;//用来加载网络图片
    private DisplayImageOptions options;//用来给imageloader设置相关的配置
    private TextView question_des_tv, volley_again_tv;//描述编程语言简介,再次请求网络数据TextView
    private ProgressBar progressBar;//进度条
    private int tid_num, tid;//用来选择的变成语言的下标位置,判断用户有没有选择编程语言


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.question_fra, null);
        left_slidingmenu_img = (ImageView) view.findViewById(R.id.question_left_slidingmenu);
        right_error_collect_img = (ImageView) view.findViewById(R.id.question_right_error_collect);
        left_slidingmenu_img.setOnClickListener(this);
        right_error_collect_img.setOnClickListener(this);
        mainactivity = (MainActivity) getActivity();
        list = new ArrayList<Language.TypeListBean>();
        queue = Volley.newRequestQueue(getActivity());//给请求队列实例化
        imageLoader = ImageLoader.getInstance();//实例化imageloader对象
        //实例化options
        options = new DisplayImageOptions.Builder()
                //.showImageOnFail(R.mipmap.ic_launcher)//设置加载失败显示的图片
                //.showImageOnLoading(R.mipmap.ic_launcher)//设置正在加载中显示的图片
                .build();
        //给list集合里面添加数据
        AddData();
        gridView = (GridView) view.findViewById(R.id.question_gridview);
        question_gv_adapter = new Question_GV_Adapter(getActivity(), imageLoader, options);
        go_dati_question_btn = (Button) view.findViewById(R.id.question_dati_btn);
        go_dati_question_btn.setOnClickListener(this);
        go_kaoshi_question_btn = (Button) view.findViewById(R.id.question_kaoshi_btn);
        go_kaoshi_question_btn.setOnClickListener(this);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                for (int i = 0; i < list.size(); i++) {
                    if (i == position) {
                        list.get(i).setLanguage_select(true);
                    } else {
                        list.get(i).setLanguage_select(false);
                    }
                }
                question_gv_adapter.setList(list);
                question_gv_adapter.notifyDataSetChanged();
                question_des_tv.setText(list.get(position).getTypeDescribe());
                tid_num = position;
                tid = position + 1;
            }
        });
        question_des_tv = (TextView) view.findViewById(R.id.question_description_tv);
        volley_again_tv = (TextView) view.findViewById(R.id.volley_again_tv);
        volley_again_tv.setOnClickListener(this);
        progressBar = (ProgressBar) view.findViewById(R.id.question_progressbar);

        return view;
    }

    //给list集合中添加数据
    private void AddData() {
        //Toast.makeText(getActivity(),URLConfig.GETLANGUAGE_URL,Toast.LENGTH_SHORT).show();
        JsonObjectRequest jor = new JsonObjectRequest("http://"+URLConfig.MAIN_URL+":8080/CoolTopic/GetTypeInfo", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("volley", response.toString());
                Gson gson = new Gson();
                String info = response.toString();
                Language language = gson.fromJson(info, Language.class);
                list = language.getTypeList();
                for (Language.TypeListBean typelistbean : list) {
                    Log.i("language============", typelistbean.getTypeName());
                }
                question_gv_adapter.setList(list);
                gridView.setAdapter(question_gv_adapter);
                setGridViewHeight(gridView);
                progressBar.setVisibility(View.INVISIBLE);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("volley", "获取编程语言列表失败!!!!");
                progressBar.setVisibility(View.INVISIBLE);
                volley_again_tv.setVisibility(View.VISIBLE);
            }
        });
        queue.add(jor);
        queue.start();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.question_left_slidingmenu:
                //Toast.makeText(getActivity(), "侧滑页面!", Toast.LENGTH_SHORT).show();
                if (mainactivity.slidingmenu.isMenuShowing()) {//侧滑页面当前显示
                    mainactivity.slidingmenu.showContent();
                    ;//显示主页面
                } else {//侧滑页面当前没显示
                    mainactivity.slidingmenu.showMenu();
                }
                break;
            case R.id.question_right_error_collect:

                //Toast.makeText(getActivity(), "错题本页面出来了!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getActivity(), CTBActivity.class);
                startActivity(intent);


                break;
            case R.id.question_dati_btn:
                if (tid == 0) {
                    Toast.makeText(getActivity(), "请选择一种题目类型", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getActivity(), "前往答题页面!", Toast.LENGTH_SHORT).show();
                    Intent to_question_intent = new Intent(getActivity(), GoQuestionActivity.class);
                    to_question_intent.putExtra("tid_bean", list.get(tid_num));
                    to_question_intent.putExtra("question_kind", "dati");
                    //Toast.makeText(getActivity(), tid_num + "", Toast.LENGTH_SHORT).show();
                    startActivity(to_question_intent);
                }
                break;
            case R.id.question_kaoshi_btn:
                if (tid == 0) {
                    Toast.makeText(getActivity(), "请选择一种题目类型", Toast.LENGTH_SHORT).show();
                } else {
                    //Toast.makeText(getActivity(), "前往考试页面!", Toast.LENGTH_SHORT).show();
                    Intent to_question_intent = new Intent(getActivity(), GoQuestionActivity.class);
                    to_question_intent.putExtra("tid_bean", list.get(tid_num));
                    to_question_intent.putExtra("question_kind", "kaoshi");
                    //Toast.makeText(getActivity(), tid_num + "", Toast.LENGTH_SHORT).show();
                    startActivity(to_question_intent);
                }
                break;
            case R.id.volley_again_tv:
                AddData();
                volley_again_tv.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                break;
        }
    }

    //动态设置GridView的高度
    public void setGridViewHeight(GridView grid_view) {
        ListAdapter adapter = grid_view.getAdapter();
        if (adapter == null) {
            return;
        }
        int cols = grid_view.getNumColumns();//获取到gridview有多少列
        int totalHeight = 0;

        // i每次加4，相当于listAdapter.getCount()小于等于4时 循环一次，计算一次item的高度，
        // listAdapter.getCount()小于等于8时计算两次高度相加

        for (int i = 0; i < grid_view.getCount(); i += cols) {
            // 获取listview的每一个item
            View list_item = adapter.getView(i, null, grid_view);
            list_item.measure(0, 0);
            // 获取item的高度和
            totalHeight += list_item.getMeasuredHeight();
        }

        // 获取listview的布局参数
        ViewGroup.LayoutParams params = grid_view.getLayoutParams();
        // 设置高度
        params.height = totalHeight;
        // 设置margin
        ((ViewGroup.MarginLayoutParams) params).setMargins(5, 5, 5, 5);
        // 设置参数
        grid_view.setLayoutParams(params);
    }
}
