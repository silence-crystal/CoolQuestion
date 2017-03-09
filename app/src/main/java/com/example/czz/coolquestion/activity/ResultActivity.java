package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.adapter.Result_GV_Adapter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/3/3.
 */

public class ResultActivity extends Activity{
    private ImageView act_result_back;//
    private GridView act_result_gridview;//
    private TextView left_language_tv,right_correct_tv;//
    private ArrayList<String> select_list;//存放用户选择的答案
    private ArrayList<String> correct_list;//存放正确的答案
    private int correct_num;//用来记录用户选择正确的题目的个数
    private String kind_of_language;
    private Intent intent;//接受上一个传递过来的intnt
    private Result_GV_Adapter adapter;//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result_activity);
        InitData();
        act_result_gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent=new Intent();
                intent.putExtra("data_return",position);
                Toast.makeText(ResultActivity.this,position+"",Toast.LENGTH_SHORT).show();;
                setResult(RESULT_OK,intent);
                finish();
            }
        });
    }

    private void InitData() {
        act_result_back= (ImageView) findViewById(R.id.act_result_back);
        act_result_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        act_result_gridview= (GridView) findViewById(R.id.act_result_gridview);
        left_language_tv= (TextView) findViewById(R.id.act_result_left_language);
        right_correct_tv= (TextView) findViewById(R.id.act_result_right_correct);
        select_list=new ArrayList<String>();
        correct_list=new ArrayList<String>();
        intent=getIntent();
        select_list=intent.getStringArrayListExtra("select_list");
        correct_list=intent.getStringArrayListExtra("correct_list");
        kind_of_language=intent.getStringExtra("language");
        left_language_tv.setText(kind_of_language);
        //计算用户选择正确的题目的方法
        UserCorrect();
        right_correct_tv.setText("正确 "+correct_num+"/"+select_list.size());
        adapter=new Result_GV_Adapter(ResultActivity.this);
        adapter.setSelect_list(select_list);
        adapter.setCorrect_list(correct_list);
        act_result_gridview.setAdapter(adapter);
    }

    private void UserCorrect() {
        for (int i=0;i<select_list.size();i++){
            if (!select_list.get(i).equals(correct_list.get(i))){
                continue;
            }else{
                correct_num++;
            }
        }
    }
}
