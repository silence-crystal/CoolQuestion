package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.Element;
import android.support.v4.view.ViewPager;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/3/1.
 */

public class GoQuestionActivity extends Activity implements View.OnClickListener {

    private ImageView act_question_back;//返回上一层的按钮
    private ImageView act_question_addtoctb;//点击添加错题本按钮触发的事件
    private TextView goPrevious_tv, goNext_tv;//触发上一题和下一题
    private ViewFlipper viewFlipper;//图片播放
    Animation[] animations;//定义一个动画数组，用于为ViewFilpper指定切换动画效果。
    private List<Question> question_list;//数据源
    private ArrayList<String> correct_list;//用来存放正确答案的集合
    private ArrayList<String> select_list;//用来存放用户选择的集合
    private String kind_of_language="";
    private String select_answer = ""; //用来表示用户选择的答案
    private int position;//用来表示传递回来的位置坐标


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goquestion_activity);
        InitData();//初始化数据的方法
        AddAnimation(animations);//给动画数组中添加元素
        AddData();//添加数据源
        AddViewForViewFlipper(question_list);//给ViewFlipper添加视图的方法
    }

    private void AddData() {
        question_list.add(new Question("Java", "郝志雄是傻×嘛?", "是!", "就是!", "绝逼是!", "那必须是!", "A", "二逼，这道题你也能错？？？"));
        question_list.add(new Question("Java", "赵世祺是男神嘛?", "是!", "就是!", "绝逼是!", "那必须是!", "A", "二逼，这道题你也能错？？？"));
        question_list.add(new Question("Java", "编写的第san个Java程序是什么?", "Hello 3World!", "我去年买了3个表啊!", "3你瞅啥，瞅你咋滴!", "3我快抵制不住体内的洪荒之力了啊!", "A", "3这道题你也能错？？？"));
        question_list.add(new Question("Java", "编写的第si个Java程序是什么?", "Hello  4 World!", "我去年买了4个表啊!", "4你瞅啥，瞅你咋滴!", "4我快抵制不住体内的洪荒之力了啊!", "A", "4这道题你也能错？？？"));
        question_list.add(new Question("Java", "编写的第wu个Java程序是什么?", "Hello  5World!", "我去年买了5个表啊!", "5你瞅啥，瞅你咋滴!", "5我快抵制不住体内的洪荒之力了啊!", "A", "5这道题你也能错？？？"));
        question_list.add(new Question("Java", "编写的第liu个Java程序是什么?", "Hello 6 World!", "我去年买了6个表啊!", "6你瞅啥，瞅你咋滴!", "6我快抵制不住体内的洪荒之力了啊!", "A", "6这道题你也能错？？？"));
        correct_list.add("A");
        correct_list.add("A");
        correct_list.add("A");
        correct_list.add("A");
        correct_list.add("A");
        correct_list.add("A");
        kind_of_language=question_list.get(0).getQuestion_language();
    }

    private void AddViewForViewFlipper(List<Question> list) {


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
            question_language_tv.setText(list.get(i).getQuestion_language() + "专项练习");
            String current_question=Integer.toString(i+1);
            question_num_tv.setText(current_question + "/" + list.size());
            question_question_title.setText(list.get(i).getQuestion_title());
            answerA_tv.setText(list.get(i).getAnswerA());
            answerB_tv.setText(list.get(i).getAnswerB());
            answerC_tv.setText(list.get(i).getAnswerC());
            answerD_tv.setText(list.get(i).getAnswerD());

            viewFlipper.addView(view);
        }


    }

    private void InitData() {
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
        question_list = new ArrayList<Question>();

        correct_list=new ArrayList<String>();
        select_list=new ArrayList<String>();


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
                finish();
                break;
            case R.id.act_question_addtoctb:
                Toast.makeText(GoQuestionActivity.this,"加到错题本列表!",Toast.LENGTH_SHORT).show();

                break;
            case R.id.act_question_previous://点击上一题触发的方法
                if (viewFlipper.getDisplayedChild() == 0) {
                    ShowBuilder("已经是第一道题了");
                } else {
                    viewFlipper.setInAnimation(animations[2]);
                    viewFlipper.setOutAnimation(animations[3]);
                    viewFlipper.showPrevious();
                }
                break;
            case R.id.act_question_next://点击下一题触发的方法

                View child_view = viewFlipper.getChildAt(viewFlipper.getDisplayedChild());
                TextView correct_answer_tv = (TextView) child_view.findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
                TextView question_description_tv = (TextView) child_view.findViewById(R.id.act_question_viewflipper_item_description);
                if (correct_answer_tv.getVisibility() == View.VISIBLE || question_description_tv.getVisibility() == View.VISIBLE) {
                    if (viewFlipper.getDisplayedChild() == question_list.size() - 1) {
                        ShowBuilder("已经是最后一道题了");
                        //ShowCorrectAnswerAndDes();
                    } else {
                        viewFlipper.setInAnimation(animations[0]);
                        viewFlipper.setOutAnimation(animations[1]);
                        viewFlipper.showNext();
                    }
                } else {
                    if (viewFlipper.getDisplayedChild() == question_list.size() - 1) {
                        if (select_answer.equals("")){
                            ShowBuilder("您还没有选择答案!!!");
                        }else{
                            select_list.add(select_answer);
                            Toast.makeText(GoQuestionActivity.this,select_list.size()+"",Toast.LENGTH_SHORT).show();
                            Toast.makeText(GoQuestionActivity.this,correct_list.size()+"",Toast.LENGTH_SHORT).show();
                            ShowCorrectAnswerAndDes();
                            Intent intent=new Intent(GoQuestionActivity.this,ResultActivity.class);
                            intent.putStringArrayListExtra("select_list",select_list);
                            intent.putStringArrayListExtra("correct_list",correct_list);
                            intent.putExtra("language",kind_of_language);
                            startActivityForResult(intent,1);//1是请求码
                        }
                    } else {
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

                break;
        }
    }


    //显示正确答案和问题解析的方法
    public void ShowCorrectAnswerAndDes() {
        Question question = question_list.get(viewFlipper.getDisplayedChild());
        View child_view = viewFlipper.getChildAt(viewFlipper.getDisplayedChild());
        TextView correct_answer_tv = (TextView) child_view.findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
        TextView question_description_tv = (TextView) child_view.findViewById(R.id.act_question_viewflipper_item_description);
        correct_answer_tv.setVisibility(View.VISIBLE);
        question_description_tv.setVisibility(View.VISIBLE);
        correct_answer_tv.setText("正确答案为:" + question.getAnswer_correct());
        //设置正确选项的颜色
        int correct_answer_tv_size=correct_answer_tv.getText().toString().length();
        SpannableStringBuilder builder=new SpannableStringBuilder(correct_answer_tv.getText().toString());
        ForegroundColorSpan greencolor=new ForegroundColorSpan(getResources().getColor(R.color.green));//设置前景色
        builder.setSpan(greencolor,correct_answer_tv_size-1,correct_answer_tv_size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        correct_answer_tv.setText(builder);
        //设置答案描述的文字内容
        question_description_tv.setText(question.getAnswer_description());
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
