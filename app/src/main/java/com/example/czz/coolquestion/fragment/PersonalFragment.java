package com.example.czz.coolquestion.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.CollectionActivity;
import com.example.czz.coolquestion.activity.KnowledgeActivity;
import com.example.czz.coolquestion.activity.TopicActivity;


/**
 * Created by dell on 2017/2/28.
 */

public class PersonalFragment extends Fragment {
    private ImageView head_img,drop_img;
    private TextView name_tv,collect_tv,errorbook_tv,knowledge_tv;
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.personal_fra,null);
        initView();
        return  view;
    }
    private void initView(){
        head_img= (ImageView) view.findViewById(R.id.head_img);
        drop_img= (ImageView) view.findViewById(R.id.drop_img);
        name_tv= (TextView) view.findViewById(R.id.name_tv);
        collect_tv= (TextView) view.findViewById(R.id.collect_tv);
        collect_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), CollectionActivity.class);
                startActivity(intent);
            }
        });
        errorbook_tv= (TextView) view.findViewById(R.id.errorbook_tv);
        errorbook_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), TopicActivity.class);
                startActivity(intent);
            }
        });
        knowledge_tv= (TextView) view.findViewById(R.id.knowledge_tv);
        knowledge_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getActivity(), KnowledgeActivity.class);
                startActivity(intent);
            }
        });
    }
}
