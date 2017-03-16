package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ErrorQuestion;

/**
 * Created by dell on 2017/3/6.
 */

public class QuestionDetailActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView back_img;
    private Intent intent;//接受传递过来intent
    private ErrorQuestion.ErrorQuestionlistBean errorQuestion;//点击错题传递过来的错题
    private TextView question_language_tv,question_question_title,answerA_tv,answerB_tv,
            answerC_tv,answerD_tv,select_answer_tv,correct_answer_tv,question_description_tv;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_questiondetail);
        IniteView();
        AddData();
    }

    private void AddData() {
        question_language_tv.setText(errorQuestion.getEContent().getTid()+"专项练习");
        question_question_title.setText("题目:"+errorQuestion.getEContent().getQuestionContent());
        answerA_tv.setText(errorQuestion.getEContent().getAnsA());
        answerB_tv.setText(errorQuestion.getEContent().getAnsB());
        answerC_tv.setText(errorQuestion.getEContent().getAnsC());
        answerD_tv.setText(errorQuestion.getEContent().getAnsD());
        select_answer_tv.setText("您选择的答案是:"+errorQuestion.getEAnswer());
        correct_answer_tv.setVisibility(View.VISIBLE);
        correct_answer_tv.setText("正确答案为:"+errorQuestion.getEContent().getAnswer());
        //设置正确选项的颜色
        int correct_answer_tv_size=correct_answer_tv.getText().toString().length();
        SpannableStringBuilder builder=new SpannableStringBuilder(correct_answer_tv.getText().toString());
        ForegroundColorSpan greencolor=new ForegroundColorSpan(getResources().getColor(R.color.green));//设置前景色
        builder.setSpan(greencolor,correct_answer_tv_size-1,correct_answer_tv_size, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        correct_answer_tv.setText(builder);

        question_description_tv.setVisibility(View.VISIBLE);
        question_description_tv.setText("解析:"+errorQuestion.getEContent().getDeQuestion());
    }

    private void IniteView() {
        back_img= (ImageView) findViewById(R.id.act_questiondetail_back);
        back_img.setOnClickListener(this);
        intent=getIntent();
        errorQuestion= (ErrorQuestion.ErrorQuestionlistBean) intent.getSerializableExtra("errorquestion");

         question_language_tv= (TextView) findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_language_tv);
         question_question_title = (TextView) findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_question_title);
         answerA_tv = (TextView) findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_answerA);
         answerB_tv = (TextView) findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_answerB);
         answerC_tv = (TextView)  findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_answerC);
         answerD_tv = (TextView)  findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_answerD);
         select_answer_tv = (TextView)  findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_select_tv);
         correct_answer_tv = (TextView)  findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_correct_answer_tv);
         question_description_tv = (TextView)  findViewById(R.id.questiondetail_layout).findViewById(R.id.act_question_viewflipper_item_description);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.act_questiondetail_back:
                finish();
                break;
        }
    }
}
