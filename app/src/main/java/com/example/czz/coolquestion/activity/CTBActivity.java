package com.example.czz.coolquestion.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.adapter.CTB_Adapter;
import com.example.czz.coolquestion.bean.Question;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.List;

//错题本
public class CTBActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageView iv_back;//返回按钮和分享按钮
    private SwipeMenuCreator creator;//滑动菜单创建器
    private SwipeMenuListView smListView;//滑动菜单
    private CTB_Adapter ctb_adapter;//显示错题本的适配器
    private List<Question.QuestionListBean> ctb_question_list;//存放错题的集合

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ctb);

        InitView();
        InitListener();
    }

    //各种控件
    public void InitView(){
        iv_back= (ImageView) findViewById(R.id.imageView2_left_topic_back);
        ctb_question_list=new ArrayList<Question.QuestionListBean>();
        creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                //添加查看详情按钮
//                SwipeMenuItem openItem=new SwipeMenuItem(CTBActivity.this);
//                openItem.setWidth(90);
//                openItem.setBackground(new ColorDrawable(Color.rgb(0x33,0x66,0xcc)));//设置详情的背景色
//                openItem.setWidth(dpTopx(CTBActivity.this,60));
//                openItem.setIcon(R.mipmap.listview_item_detail);
//                menu.addMenuItem(openItem);
                //添加删除按钮
                SwipeMenuItem deleteItem= new SwipeMenuItem(CTBActivity.this);
                deleteItem.setBackground(new ColorDrawable(Color.RED));
                deleteItem.setWidth(dpTopx(CTBActivity.this,60));
                deleteItem.setIcon(R.mipmap.listview_item_delete);
                menu.addMenuItem(deleteItem);
            }
        };
        smListView= (SwipeMenuListView) findViewById(R.id.act_ctb_listview);
        smListView.setMenuCreator(creator);
        smListView.setSwipeDirection(SwipeMenuListView.DIRECTION_LEFT);
        ctb_adapter=new CTB_Adapter(CTBActivity.this);
        //给适配器添加数据的方法
        AddData();
        ctb_adapter.setList(ctb_question_list);
        smListView.setAdapter(ctb_adapter);

    }

    private void AddData() {
        for (int i=0;i<14;i++){
            Question.QuestionListBean question_bean=new Question.QuestionListBean("A","A","A","A","A","这你也能错","开发的第一个Java程序",2,1);
            ctb_question_list.add(question_bean);
        }
    }


    //监听事件
    public void InitListener(){
        iv_back.setOnClickListener(this);
        //SwipeMenuListView点击子Item触发的事件
        smListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                //index就是在creator中一次添加的子item,类似数组的下标
                switch (index){
//                    case 0:
//                        Toast.makeText(CTBActivity.this,"点击详情按钮!!!",Toast.LENGTH_SHORT).show();
//                        Intent intent=new Intent(CTBActivity.this,QuestionDetailActivity.class);
//                        startActivity(intent);
//                        break;
                    case 0:
                        Toast.makeText(CTBActivity.this,"点击删除按钮!!!",Toast.LENGTH_SHORT).show();
                        break;
                }

                return false;//false---用户触发其他地方的屏幕时,自动收起菜单;true--不改变已经打开的菜单样式，保持原样不收起
            }
        });
        //SwipeMenuListView的滑动事件
        smListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {
            @Override
            public void onSwipeStart(int position) {
                Log.i("位置"+position,"开始侧滑");
            }

            @Override
            public void onSwipeEnd(int position) {
                Log.i("位置"+position,"侧滑结束");
            }
        });
        smListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(CTBActivity.this,"点击详情按钮!!!",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(CTBActivity.this,QuestionDetailActivity.class);
                startActivity(intent);
            }
        });
    }


    //点击事件
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageView2_left_topic_back:
                finish();
                break;
            default:
                break;

        }

    }

    //将dp转换为px
    public int dpTopx(Context context,float value){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(value*scale+0.5f);
    }
    //将px转换为dp
    public int pxTodp(Context context,float value){
        final float scale=context.getResources().getDisplayMetrics().density;
        return (int)(value/scale+0.5f);
    }
}
