package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.renderscript.Element;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.Language;
import com.example.czz.coolquestion.bean.Question;
import com.example.czz.coolquestion.url.URLConfig;
import com.google.gson.Gson;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.sql.DatabaseMetaData;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by dell on 2017/3/1.
 */

public class GoQuestionActivity extends Activity implements View.OnClickListener {

    private ImageView act_question_back;//返回上一层的按钮
    private ImageView act_question_addtoctb;//点击添加错题本按钮触发的事件
    private TextView goPrevious_tv, goNext_tv;//触发上一题和下一题
    private ViewFlipper viewFlipper;//图片播放
    Animation[] animations;//定义一个动画数组，用于为ViewFilpper指定切换动画效果。
    private List<Question.QuestionListBean> question_list;//数据源
    private List<Question.QuestionListBean> bean_list;//随机选出10道题之后的集合
    private ArrayList<String> correct_list;//用来存放正确答案的集合
    private ArrayList<String> select_list;//用来存放用户选择的集合
    private String kind_of_language="";//传递给下一个Activity的值
    private String select_answer = ""; //用来表示用户选择的答案
    private int position;//用来表示传递回来的位置坐标
    private RequestQueue queue;//用来请求网络数据
    private Intent intent;//表示接受到的Intent
    private String question_kind_string="";//表示接受到的类型---答题or考试
    private Language.TypeListBean tid_bean;//用来表示传递过来的编程语言
    private Chronometer chronometer;//用来表示计时器
    private String dati_or_kaoshi="";//用来记录是考试还是答题
    private  Question.QuestionListBean question_bean;//用来记录当前的问题


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goquestion_activity);
        InitData();//初始化数据的方法
        AddAnimation(animations);//给动画数组中添加元素
        AddNetData();//加载网络数据


    }

    //加载网络数据
    private void AddNetData() {
//        Log.i("volley================",
//                URLConfig.GETQUESTION_URL+"tid="+tid_bean.getTypeId());
        JsonObjectRequest jor=new JsonObjectRequest(Request.Method.POST, URLConfig.GETQUESTION_URL + "tid=" + tid_bean.getTypeId(), null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.i("volley================",response.toString());
                Gson gson=new Gson();
                String info=response.toString();
                Question question=gson.fromJson(info,Question.class);
                question_list=question.getQuestionList();
                if (question_list.size()==0){
                    Toast.makeText(GoQuestionActivity.this,"目前还没有相对应的题目！！",Toast.LENGTH_SHORT).show();
                }else{
                    if (question_kind_string.equals("dati")){
                        //Log.i("==2222=","答题");
                        AddDaTiData();//添加答题数据源
                        AddViewForViewFlipper(question_list);
                        dati_or_kaoshi="答题";
                    }else if (question_kind_string.equals("kaoshi")){
                         Log.i("==2222222=","考试");
                        //添加考试数据源
                        bean_list=AddKaoShiData();
                        //设置正确答案
                        for (int k =0;k<bean_list.size();k++){
                            //Log.i("list_questiontitle",list.get(k).getQuestionContent());
                            correct_list.add(bean_list.get(k).getAnswer());
                            Log.i("correct_list",correct_list.get(k));
                        }

                        AddViewForViewFlipper(bean_list);
                        dati_or_kaoshi="考试";
                        //开始计时器
                        chronometer.setVisibility(View.VISIBLE);
                        chronometer.setBase(SystemClock.elapsedRealtime());//计时器清零
                        int hour= (int) ((SystemClock.elapsedRealtime()-chronometer.getBase())/1000/60);
                        chronometer.setFormat("0"+String.valueOf(hour)+":%s");
                        chronometer.start();


                    }
                }

//                for (int i=0;i<question_list.size();i++){
//                    Log.i("=======题目",question_list.get(i).getQuestionContent());
//                    Log.i("=======A",question_list.get(i).getAnsA());
//                    Log.i("=======B",question_list.get(i).getAnsB());
//                    Log.i("=======C",question_list.get(i).getAnsC());
//                    Log.i("=======D",question_list.get(i).getAnsD());
//                    Log.i("=======current",question_list.get(i).getAnswer());
//                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                    Toast.makeText(GoQuestionActivity.this,"加载题目数据失败!!!",Toast.LENGTH_SHORT).show();
            }
        });
        queue.add(jor);
        queue.start();
    }

    //加载考试题目
    private List<Question.QuestionListBean> AddKaoShiData() {
            List<Question.QuestionListBean> list=new ArrayList<Question.QuestionListBean>();
                if (question_list.size()<10){
                    return question_list;
                }else{
                    Random ran=new Random();
                    int i=0;//循环次数
                    while (i<9){
                        int num=ran.nextInt(question_list.size());
                        if (list.size()==0){
                            list.add(question_list.get(num));
                        }else{
                            int int_num=0;//记录遍历过的个数
                            for (int j=0;j<list.size();j++){
                                if (list.get(j).getQuestionID()==question_list.get(num).getQuestionID()){
                                   break;
                                }else{
                                    int_num++;
                                }
                            }
                            if (int_num==list.size()){
                                list.add(question_list.get(num));
                                i++;
                            }
                            int_num=0;
                        }

                    }

                    return  list;
                }
    }

    //加载答题题目
    private void AddDaTiData() {
//        question_list.add(new Question("Java", "郝志雄是傻×嘛?", "是!", "就是!", "绝逼是!", "那必须是!", "A", "二逼，这道题你也能错？？？"));
//        question_list.add(new Question("Java", "赵世祺是男神嘛?", "是!", "就是!", "绝逼是!", "那必须是!", "A", "二逼，这道题你也能错？？？"));
//        question_list.add(new Question("Java", "编写的第san个Java程序是什么?", "Hello 3World!", "我去年买了3个表啊!", "3你瞅啥，瞅你咋滴!", "3我快抵制不住体内的洪荒之力了啊!", "A", "3这道题你也能错？？？"));
//        question_list.add(new Question("Java", "编写的第si个Java程序是什么?", "Hello  4 World!", "我去年买了4个表啊!", "4你瞅啥，瞅你咋滴!", "4我快抵制不住体内的洪荒之力了啊!", "A", "4这道题你也能错？？？"));
//        question_list.add(new Question("Java", "编写的第wu个Java程序是什么?", "Hello  5World!", "我去年买了5个表啊!", "5你瞅啥，瞅你咋滴!", "5我快抵制不住体内的洪荒之力了啊!", "A", "5这道题你也能错？？？"));
//        question_list.add(new Question("Java", "编写的第liu个Java程序是什么?", "Hello 6 World!", "我去年买了6个表啊!", "6你瞅啥，瞅你咋滴!", "6我快抵制不住体内的洪荒之力了啊!", "A", "6这道题你也能错？？？"));
//        correct_list.add("A");
//        correct_list.add("A");
//        correct_list.add("A");
//        correct_list.add("A");
//        correct_list.add("A");
//        correct_list.add("A");
        //将正确答案的集合添加数据
        for (int i = 0;i<question_list.size();i++){
            correct_list.add(question_list.get(i).getAnswer());
        }

    }

    private void AddViewForViewFlipper(List<Question.QuestionListBean> list) {


        for (int i = 0; i < list.size(); i++) {
            View view = getLayoutInflater().inflate(R.layout.viewflipper_item, null);
            //对ViewFlipper的item里的控件进行初始化
            TextView question_language_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_language_tv);
            TextView question_num_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_num_tv);
            TextView question_question_title = (TextView) view.findViewById(R.id.act_question_viewflipper_item_question_title);
            TextView answerA_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_answerA);
            TextView answerB_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_answerB);
            TextView answerC_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_answerC);
            TextView answerD_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_answerD);
            final TextView select_answer_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_select_tv);

            final TextView correct_answer_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
            final TextView question_description_tv = (TextView) view.findViewById(R.id.act_question_viewflipper_item_description);

            //对ViewFlipper的item里的控件进行监听
            answerA_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (correct_answer_tv.getVisibility() != View.VISIBLE || question_description_tv.getVisibility() != View.VISIBLE) {
                        Toast.makeText(GoQuestionActivity.this, "您选择了A选项", Toast.LENGTH_SHORT).show();
                        select_answer = "A";
                        select_answer_tv.setText("您选择的答案是:" + select_answer);
                    }


                }
            });
            answerB_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (correct_answer_tv.getVisibility() != View.VISIBLE || question_description_tv.getVisibility() != View.VISIBLE) {
                        Toast.makeText(GoQuestionActivity.this, "您选择了B选项", Toast.LENGTH_SHORT).show();
                        select_answer = "B";
                        select_answer_tv.setText("您选择的答案是:" + select_answer);
                    }


                }
            });
            answerC_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (correct_answer_tv.getVisibility() != View.VISIBLE || question_description_tv.getVisibility() != View.VISIBLE) {
                        Toast.makeText(GoQuestionActivity.this, "您选择了C选项", Toast.LENGTH_SHORT).show();
                        select_answer = "C";
                        select_answer_tv.setText("您选择的答案是:" + select_answer);
                    }


                }
            });
            answerD_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (correct_answer_tv.getVisibility() != View.VISIBLE || question_description_tv.getVisibility() != View.VISIBLE) {
                        Toast.makeText(GoQuestionActivity.this, "您选择了D选项", Toast.LENGTH_SHORT).show();
                        select_answer = "D";
                        select_answer_tv.setText("您选择的答案是:" + select_answer);
                    }


                }
            });

            //对ViewFlipper的item里的控件进行赋值
            question_language_tv.setText(tid_bean.getTypeName()+ "专项练习");
            String current_question=Integer.toString(i+1);
            question_num_tv.setText(current_question + "/" + list.size());
            question_question_title.setText(list.get(i).getQuestionContent());
            answerA_tv.setText(list.get(i).getAnsA());
            answerB_tv.setText(list.get(i).getAnsB());
            answerC_tv.setText(list.get(i).getAnsC());
            answerD_tv.setText(list.get(i).getAnsD());

            viewFlipper.addView(view);
        }


    }

    private void InitData() {
        intent=getIntent();
        question_kind_string=intent.getStringExtra("question_kind");
        tid_bean= (Language.TypeListBean) intent.getSerializableExtra("tid_bean");
        kind_of_language=tid_bean.getTypeName();
        act_question_back = (ImageView) findViewById(R.id.act_question_back);
        act_question_back.setOnClickListener(this);
        act_question_addtoctb= (ImageView) findViewById(R.id.act_question_addtoctb);
        act_question_addtoctb.setOnClickListener(this);
        goPrevious_tv = (TextView) findViewById(R.id.act_question_previous);
        goPrevious_tv.setOnClickListener(this);
        goNext_tv = (TextView) findViewById(R.id.act_question_next);
        goNext_tv.setOnClickListener(this);
        viewFlipper = (ViewFlipper) findViewById(R.id.activity_question_viewflipper);
        animations = new Animation[4];
        question_list = new ArrayList<Question.QuestionListBean>();
        bean_list=new ArrayList<Question.QuestionListBean>();

        correct_list=new ArrayList<String>();
        select_list=new ArrayList<String>();
        queue= Volley.newRequestQueue(GoQuestionActivity.this);
        chronometer= (Chronometer) findViewById(R.id.act_question_chronometer);

    }

    private void AddAnimation(Animation[] animations) {
        animations[0] = AnimationUtils.loadAnimation(this, R.anim.left_in);
        animations[1] = AnimationUtils.loadAnimation(this, R.anim.left_out);
        animations[2] = AnimationUtils.loadAnimation(this, R.anim.right_in);
        animations[3] = AnimationUtils.loadAnimation(this, R.anim.right_out);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.act_question_back:
                if (select_list.size()<bean_list.size()){
                    AlertDialog.Builder builder=new AlertDialog.Builder(GoQuestionActivity.this);
                    builder.setCancelable(false);
                    builder.setTitle("提示:");
                    builder.setMessage("题目还没有做完,你确定要离开?");
                    builder.setPositiveButton("确定",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            if (!select_answer.equals("")){
                                select_answer="";
                                View view=viewFlipper.getChildAt(viewFlipper.getDisplayedChild());
                                TextView textView= (TextView) view.findViewById(R.id.act_question_viewflipper_item_select_tv);
                                textView.setText("您选择的答案是:" + select_answer);
                            }
                            while (select_list.size()<bean_list.size()){
                                select_list.add("");
                            }
                            chronometer.stop();

                            for (int i=0;i<bean_list.size();i++){
                                View view_child=viewFlipper.getChildAt(i);
                                TextView correct= (TextView) view_child.findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
                                TextView description= (TextView) view_child.findViewById(R.id.act_question_viewflipper_item_description);
                                if (correct.getVisibility()==View.INVISIBLE&&description.getVisibility()==View.INVISIBLE){
                                    correct.setVisibility(View.VISIBLE);
                                    correct.setText("正确答案为:"+bean_list.get(i).getAnswer());
                                    //设置正确选项的颜色
                                    int correct_answer_tv_size=correct.getText().toString().length();
                                    SpannableStringBuilder builder=new SpannableStringBuilder(correct.getText().toString());
                                    ForegroundColorSpan greencolor=new ForegroundColorSpan(getResources().getColor(R.color.green));//设置前景色
                                    builder.setSpan(greencolor,correct_answer_tv_size-1,correct_answer_tv_size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                                    correct.setText(builder);
                                    description.setVisibility(View.VISIBLE);
                                    description.setText("解析"+bean_list.get(i).getDeQuestion());
                                }
                            }
                            Intent intent=new Intent(GoQuestionActivity.this,ResultActivity.class);
                            intent.putStringArrayListExtra("select_list",select_list);
                            intent.putStringArrayListExtra("correct_list",correct_list);
                            Log.i("select_list.size",select_list.size()+"");
                            Log.i("correct_list.size",correct_list.size()+"");
                            intent.putExtra("language",kind_of_language);
                            intent.putExtra("total_time",chronometer.getText().toString());
                            startActivityForResult(intent,1);//1是请求码
                        }
                    });
                    builder.setNegativeButton("取消",new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }else{
                    finish();
                }

                break;
            case R.id.act_question_addtoctb:
                //添加到错题本的方法
                AddErrorQuestion();
                Toast.makeText(GoQuestionActivity.this,"添加错题的方法!!!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.act_question_previous://点击上一题触发的方法
                View child_view_previous=viewFlipper.getChildAt(viewFlipper.getDisplayedChild());
                if (child_view_previous==null){
                    Toast.makeText(GoQuestionActivity.this,"目前还没有题!!!!",Toast.LENGTH_SHORT).show();
                }else{
                    if (viewFlipper.getDisplayedChild() == 0) {
                        ShowBuilder("已经是第一道题了");
                    } else {
                        viewFlipper.setInAnimation(animations[2]);
                        viewFlipper.setOutAnimation(animations[3]);
                        viewFlipper.showPrevious();
                    }
                }

                break;
            case R.id.act_question_next://点击下一题触发的方法

                View child_view_next = viewFlipper.getChildAt(viewFlipper.getDisplayedChild());
                if (child_view_next==null){
                        Toast.makeText(GoQuestionActivity.this,"目前还没有题!!!!",Toast.LENGTH_SHORT).show();
                }else {
                    TextView correct_answer_tv = (TextView) child_view_next.findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
                    TextView question_description_tv = (TextView) child_view_next.findViewById(R.id.act_question_viewflipper_item_description);
                    if (correct_answer_tv.getVisibility() == View.VISIBLE || question_description_tv.getVisibility() == View.VISIBLE) {
                        if (viewFlipper.getDisplayedChild() == question_list.size() - 1) {//查询答题集合的结果
                            ShowBuilder("已经是最后一道题了");
                            //ShowCorrectAnswerAndDes();
                        } else if(viewFlipper.getDisplayedChild()==bean_list.size()-1){//查询考试集合的结果
                            ShowBuilder("已经是最后一道题了");
                            //ShowCorrectAnswerAndDes();
                        }else {
                            viewFlipper.setInAnimation(animations[0]);
                            viewFlipper.setOutAnimation(animations[1]);
                            viewFlipper.showNext();
                        }
                    } else {
                        if (viewFlipper.getDisplayedChild() == question_list.size() - 1) {//答题集合的最后一道题
                            if (select_answer.equals("")){
                                ShowBuilder("您还没有选择答案!!!");
                            }else{
                                select_list.add(select_answer);
                                Toast.makeText(GoQuestionActivity.this,select_list.size()+"",Toast.LENGTH_SHORT).show();
                                Toast.makeText(GoQuestionActivity.this,correct_list.size()+"",Toast.LENGTH_SHORT).show();
                                ShowCorrectAnswerAndDes();
                                if (chronometer.getVisibility()==View.VISIBLE){
                                    chronometer.stop();
                                }
                                Intent intent=new Intent(GoQuestionActivity.this,ResultActivity.class);
                                intent.putStringArrayListExtra("select_list",select_list);
                                intent.putStringArrayListExtra("correct_list",correct_list);
                                Log.i("select_list.size",select_list.size()+"");
                                Log.i("correct_list.size",correct_list.size()+"");
                                intent.putExtra("language",kind_of_language);
                                intent.putExtra("total_time",chronometer.getText().toString());
                                startActivityForResult(intent,1);//1是请求码
                            }
                        } else if(viewFlipper.getDisplayedChild()==bean_list.size()-1){//考试集合的最后一道题
                            if (select_answer.equals("")){
                                ShowBuilder("您还没有选择答案!!!");
                            }else{
                                select_list.add(select_answer);
                                Toast.makeText(GoQuestionActivity.this,select_list.size()+"",Toast.LENGTH_SHORT).show();
                                Toast.makeText(GoQuestionActivity.this,correct_list.size()+"",Toast.LENGTH_SHORT).show();
                                ShowCorrectAnswerAndDes();
                                if (chronometer.getVisibility()==View.VISIBLE){
                                    chronometer.stop();
                                }
                                Intent intent=new Intent(GoQuestionActivity.this,ResultActivity.class);
                                intent.putStringArrayListExtra("select_list",select_list);
                                intent.putStringArrayListExtra("correct_list",correct_list);
                                Log.i("select_list.size",select_list.size()+"");
                                Log.i("correct_list.size",correct_list.size()+"");
                                intent.putExtra("language",kind_of_language);
                                intent.putExtra("total_time",chronometer.getText().toString());
                                startActivityForResult(intent,1);//1是请求码
                            }
                        } else{
                            if (select_answer.equals("")) {
                                ShowBuilder("您还没有选择答案!!!");
                            } else {
                                select_list.add(select_answer);
                                ShowCorrectAnswerAndDes();
                                viewFlipper.setInAnimation(animations[0]);
                                viewFlipper.setOutAnimation(animations[1]);
                                viewFlipper.showNext();
                                select_answer = "";
                            }
                        }
                    }
                }

                break;
        }
    }

    //添加到错题本的方法
    private void AddErrorQuestion() {
        int econtent;//用来表示错误的题的ID
        int uid;//用来表示用户的id
        //获取题目id
        if (dati_or_kaoshi.equals("答题")){
           econtent= question_list.get(viewFlipper.getDisplayedChild()).getQuestionID();
        }else{
            econtent=bean_list.get(viewFlipper.getDisplayedChild()).getQuestionID();
        }
        //获取用户id
        uid=0;
    }


    //显示正确答案和问题解析的方法
    public void ShowCorrectAnswerAndDes() {
        if (dati_or_kaoshi.equals("考试")){
            question_bean = bean_list.get(viewFlipper.getDisplayedChild());
        }else{
            question_bean = question_list.get(viewFlipper.getDisplayedChild());
        }
        View child_view = viewFlipper.getChildAt(viewFlipper.getDisplayedChild());
        TextView correct_answer_tv = (TextView) child_view.findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
        TextView question_description_tv = (TextView) child_view.findViewById(R.id.act_question_viewflipper_item_description);
        correct_answer_tv.setVisibility(View.VISIBLE);
        question_description_tv.setVisibility(View.VISIBLE);
        correct_answer_tv.setText("正确答案为:" + question_bean.getAnswer());
        //设置正确选项的颜色
        int correct_answer_tv_size=correct_answer_tv.getText().toString().length();
        SpannableStringBuilder builder=new SpannableStringBuilder(correct_answer_tv.getText().toString());
        ForegroundColorSpan greencolor=new ForegroundColorSpan(getResources().getColor(R.color.green));//设置前景色
        builder.setSpan(greencolor,correct_answer_tv_size-1,correct_answer_tv_size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        correct_answer_tv.setText(builder);
        //设置答案描述的文字内容
        question_description_tv.setText("解析:"+question_bean.getDeQuestion());
    }

    //弹出对话框的方法
    public void ShowBuilder(String string) {
        AlertDialog.Builder builder = new AlertDialog.Builder(GoQuestionActivity.this);
        builder.setTitle("提示信息");
        builder.setMessage(string);
        builder.setCancelable(false);
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.show();
    }

    //用来接收返回来的数据
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==1&&resultCode==RESULT_OK){
            position=data.getIntExtra("data_return",1);
            viewFlipper.setDisplayedChild(position);

        }
    }
}
