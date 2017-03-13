package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.CollectionActivity;
import com.example.czz.coolquestion.activity.KnowledgeActivity;
import com.example.czz.coolquestion.activity.LoginActivity;
import com.example.czz.coolquestion.activity.MainActivity;
import com.example.czz.coolquestion.activity.PersonalInfoActivity;
import com.example.czz.coolquestion.activity.SetActivity;
import com.example.czz.coolquestion.activity.CTBActivity;


/**
 * Created by dell on 2017/2/28.
 */

public class PersonalFragment extends Fragment {
    private View name_layout,collect_layout,errorbook_layout,knowledge_layout,about_layout;
    private View view;
    private ImageView touxiang_img,personal_img;
    private TextView personal_info_nichen;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.personal_fra,null);
        initView();
        personal_img= (ImageView) view.findViewById(R.id.personal_img);
        personal_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) getActivity()).slidingmenu.isMenuShowing()){
                    ((MainActivity) getActivity()).slidingmenu.showContent();
                }else {
                    ((MainActivity) getActivity()).slidingmenu.showMenu();
                }
            }
        });
        return  view;
    }
    private void initView(){
        name_layout=view.findViewById(R.id.name_layout);
        name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isLogin()==true){
                    //跳转到个人信息界面
                    Intent intent=new Intent(getActivity(), PersonalInfoActivity.class);
                    startActivity(intent);
                }else{
                    //跳转到登录界面
                    Intent intent=new Intent(getActivity(), LoginActivity.class);
                    startActivity(intent);
                }

            }
        });
        collect_layout= view.findViewById(R.id.collect_layout);
        collect_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });
        errorbook_layout= view.findViewById(R.id.errorbook_layout);
        errorbook_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CTBActivity.class);
                startActivity(intent);
            }
        });
        knowledge_layout= view.findViewById(R.id.knowledges_layout);
        knowledge_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), KnowledgeActivity.class);
                startActivity(intent);
            }
        });
        about_layout= view.findViewById(R.id.about_layout);
        about_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), SetActivity.class);
                startActivity(intent);
            }
        });
        touxiang_img= (ImageView) view.findViewById(R.id.touxiang_img);
        personal_info_nichen= (TextView) view.findViewById(R.id.personal_info_nichen);

    }
    //自定义登录验证方法
    public boolean isLogin(){
        return true;
    }
}
