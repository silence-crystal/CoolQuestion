package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.fragment.HostFragment;
import com.example.czz.coolquestion.fragment.KnowledgeFragment;
import com.example.czz.coolquestion.fragment.PersonalFragment;
import com.example.czz.coolquestion.fragment.QuestionFragment;
import com.slidingmenu.lib.SlidingMenu;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public SlidingMenu slidingmenu;//侧滑菜单
    private RelativeLayout host_rel, knowledge_rel, question_rel, personal_rel;
    private TextView tab_host_tv,tab_knowledge_tv,tab_question_tv,tab_personal_tv;
    private ImageView tab_host_img,tab_knowledge_img,tab_question_img,tab_personal_img;
    private Fragment host,knowledge,question,personal,current;
    private TextView tv_topic,tv_study,tv_col;
    private ImageView iv_head;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        InitView();
        
        InitFragment();
    }

    //刚进来的时候显示的Fragment
    private void InitFragment() {
        if (host==null){
            host=new HostFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.content_layout,host).commit();
        }
        if (host.isAdded()){
            getSupportFragmentManager().beginTransaction().show(host).commit();
            InitTabColor();
            tab_host_tv.setTextColor(getResources().getColor(R.color.tabcolor_select));
            tab_host_img.setImageResource(R.mipmap.host_select);
        }
        current=host;//记录当前的Fragment
    }
    //初始化所有控件
    private void InitView() {


        //初始化
        slidingmenu=new SlidingMenu(getApplicationContext());
        //滑动模式
        slidingmenu.setMode(SlidingMenu.LEFT);
        //滑动距离
        slidingmenu.setBehindWidthRes(R.dimen.left_slide_distance);
        //滑动触点
        slidingmenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
        //设置侧滑页面
        View view=getLayoutInflater().inflate(R.layout.left_layout,null);
        slidingmenu.setMenu(view);
        //绑定actiity
        slidingmenu.attachToActivity(MainActivity.this, SlidingMenu.SLIDING_CONTENT);
        //初始化标签栏的相对布局
        host_rel= (RelativeLayout) findViewById(R.id.host_layout);
        knowledge_rel= (RelativeLayout) findViewById(R.id.knowledge_layout);
        question_rel= (RelativeLayout) findViewById(R.id.question_layout);
        personal_rel= (RelativeLayout) findViewById(R.id.personal_layout);
        host_rel.setOnClickListener(this);
        knowledge_rel.setOnClickListener(this);
        question_rel.setOnClickListener(this);
        personal_rel.setOnClickListener(this);
        //初始化控件
        tab_host_img= (ImageView) findViewById(R.id.tab_host_img);
        tab_host_tv= (TextView) findViewById(R.id.tab_host_tv);
        tab_knowledge_img= (ImageView) findViewById(R.id.tab_knowledge_img);
        tab_knowledge_tv= (TextView) findViewById(R.id.tab_knowledge_tv);
        tab_question_img= (ImageView) findViewById(R.id.tab_question_img);
        tab_question_tv= (TextView) findViewById(R.id.tab_question_tv);
        tab_personal_img= (ImageView) findViewById(R.id.tab_personal_img);
        tab_personal_tv= (TextView) findViewById(R.id.tab_personal_tv);
        //初始化用来切换的四个Frament
//        host=new HostFragment();
//        knowledge=new KnowledgeFragment();
//        question=new QuestionFragment();
//        personal=new PersonalFragment();

        //头像
        iv_head= (ImageView) view.findViewById(R.id.imageView_left_head);
        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });


        //我的错题
        tv_topic= (TextView) view.findViewById(R.id.textView_left_topic);
        tv_topic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CTBActivity.class);
                startActivity(intent);
            }
        });
        //知识点
        tv_study= (TextView) view.findViewById(R.id.textView_left_study);
        tv_study.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, KnowledgeActivity.class);
                startActivity(intent);
            }
        });
        //收藏
        tv_col= (TextView) view.findViewById(R.id.textView_left_col);
        tv_col.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });




    }




    //底部标签栏重置的方法
    public void InitTabColor(){
        tab_host_tv.setTextColor(getResources().getColor(R.color.tabcolor_normal));
        tab_knowledge_tv.setTextColor(getResources().getColor(R.color.tabcolor_normal));
        tab_question_tv.setTextColor(getResources().getColor(R.color.tabcolor_normal));
        tab_personal_tv.setTextColor(getResources().getColor(R.color.tabcolor_normal));
        tab_host_img.setImageResource(R.mipmap.host_normal);
        tab_knowledge_img.setImageResource(R.mipmap.knowledge_normal);
        tab_question_img.setImageResource(R.mipmap.question_normal);
        tab_personal_img.setImageResource(R.mipmap.personal_normal);
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.host_layout:
                Tab_One();;
            break;
            case R.id.knowledge_layout:
                Tab_Two();
            break;
            case R.id.question_layout:
                Tab_Three();
            break;
            case R.id.personal_layout:
                Tab_Four();;
            break;
        }
    }

    //点击第一个标签栏触发的方法
    public void Tab_One(){
        if (host==null){
            host=new HostFragment();
        }
        InitTabColor();
        tab_host_tv.setTextColor(getResources().getColor(R.color.tabcolor_select));
        tab_host_img.setImageResource(R.mipmap.host_select);
        AddOrShowFragment(getSupportFragmentManager().beginTransaction(),host);
    }
    //点击第二个标签栏触发的方法
    public void Tab_Two(){
        if (knowledge==null){
            knowledge=new KnowledgeFragment();
        }
        InitTabColor();
        tab_knowledge_tv.setTextColor(getResources().getColor(R.color.tabcolor_select));
        tab_knowledge_img.setImageResource(R.mipmap.knowledge_select);
        AddOrShowFragment(getSupportFragmentManager().beginTransaction(),knowledge);
    }
    //点击第三个标签栏触发的方法
    public void Tab_Three(){
        if (question==null){
            question=new QuestionFragment();
        }
        InitTabColor();
        tab_question_tv.setTextColor(getResources().getColor(R.color.tabcolor_select));
        tab_question_img.setImageResource(R.mipmap.question_select);
        AddOrShowFragment(getSupportFragmentManager().beginTransaction(),question);
    }
    //点击第四个标签栏触发的方法
    public void Tab_Four(){
        if (personal==null){
            personal=new PersonalFragment();
        }
        InitTabColor();;
        tab_personal_tv.setTextColor(getResources().getColor(R.color.tabcolor_select));
        tab_personal_img.setImageResource(R.mipmap.personal_select);
        AddOrShowFragment(getSupportFragmentManager().beginTransaction(),personal);
    }

    //添加或者实现Fragmnt的方法
    public  void AddOrShowFragment(FragmentTransaction transaction, Fragment fragment){
        if (current==fragment){
            return;
        }
        if (!fragment.isAdded()){//如果当前的fragment没有被添加到Fragmeent管理类中，就添加
            transaction.hide(current);
            transaction.add(R.id.content_layout,fragment).show(fragment).commit();
        }
        else
        {
            transaction.hide(current).show(fragment).commit();
        }
        current=fragment;
    }
}
