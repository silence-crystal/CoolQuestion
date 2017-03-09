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
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.CollectionActivity;
import com.example.czz.coolquestion.activity.KnowledgeActivity;
<<<<<<< HEAD
=======
import com.example.czz.coolquestion.activity.PersonalInfoActivity;
import com.example.czz.coolquestion.activity.SetActivity;
>>>>>>> d463e81687bedc919e1a5882c2edb32c6eb41ebc
import com.example.czz.coolquestion.activity.CTBActivity;


/**
 * Created by dell on 2017/2/28.
 */

public class PersonalFragment extends Fragment {
    private View name_layout,collect_layout,errorbook_layout,knowledge_layout,about_layout;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.personal_fra,null);
        initView();
        return  view;
    }
    private void initView(){
        name_layout=view.findViewById(R.id.name_layout);
        name_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), PersonalInfoActivity.class);
                startActivity(intent);
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
    }
}
