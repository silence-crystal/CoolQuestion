package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.GoQuestionActivity;
import com.example.czz.coolquestion.activity.MainActivity;
import com.example.czz.coolquestion.adapter.Question_GV_Adapter;
import com.example.czz.coolquestion.bean.Language;
import com.slidingmenu.lib.SlidingMenu;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell on 2017/2/28.
 */

public class QuestionFragment extends Fragment implements View.OnClickListener {

    private ImageView left_slidingmenu_img,right_error_collect_img;//侧滑页面和错题本页面的跳转按钮
    private MainActivity mainactivity;//为了拿到Activity的slidingmenu
    private List<Language> list;
    private GridView gridView;//显计算机语言的网格视图
    private Question_GV_Adapter question_gv_adapter;//显示计算机语言的网格视图的适配器
    private Button go_question_btn;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.question_fra,null);
        left_slidingmenu_img= (ImageView) view.findViewById(R.id.question_left_slidingmenu);
        right_error_collect_img= (ImageView) view.findViewById(R.id.question_right_error_collect);
        left_slidingmenu_img.setOnClickListener(this);
        right_error_collect_img.setOnClickListener(this);
        mainactivity= (MainActivity) getActivity();
        list=new ArrayList<Language>();
        gridView= (GridView) view.findViewById(R.id.question_gridview);
        question_gv_adapter=new Question_GV_Adapter(getActivity());
        go_question_btn= (Button) view.findViewById(R.id.question_go_btn);
        go_question_btn.setOnClickListener(this);
        return  view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.question_left_slidingmenu:
                Toast.makeText(getActivity(),"侧滑页面!",Toast.LENGTH_SHORT).show();
                if (mainactivity.slidingmenu.isMenuShowing()){//侧滑页面当前显示
                    mainactivity.slidingmenu.showContent();;//显示主页面
                }else{//侧滑页面当前没显示
                    mainactivity.slidingmenu.showMenu();
                }
                break;
            case R.id.question_right_error_collect:
                Toast.makeText(getActivity(),"错题本页面出来了!",Toast.LENGTH_SHORT).show();
                break;
            case R.id.question_go_btn:
                Toast.makeText(getActivity(),"前往做题页面!",Toast.LENGTH_SHORT).show();
                Intent to_question_intent =new Intent(getActivity(), GoQuestionActivity.class);
                startActivity(to_question_intent);

                break;
        }
    }
}
