package com.example.czz.coolquestion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.activity.MainActivity;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dell on 2017/2/28.
 */

public class KnowledgeFragment extends Fragment {
    private FragmentPagerItemAdapter adapter;
    private List<String> list;
    private FragmentPagerItems.Creator creator;
    private ViewPager vp_know;
    private View view;
    private ImageView knowledge_fra_hand_img;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.knowledge_fra,null);
        InitData();
        InitPager();
        knowledge_fra_hand_img= (ImageView) view.findViewById(R.id.knowledge_fra_hand_img);
        knowledge_fra_hand_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) getActivity()).slidingmenu.isMenuShowing()){
                    ((MainActivity) getActivity()).slidingmenu.showContent();
                }else {
                    ((MainActivity) getActivity()).slidingmenu.showMenu();
                }
            }
        });
        return view;
    }
    private void InitData(){
        creator=FragmentPagerItems.with(getActivity());
        list=new ArrayList<>();
        list.add("  Java语言  ");
        list.add("   C语言    ");
        list.add("  C++语言   ");
        list.add(" Android语言");
        list.add("  Object-C语言   ");
        list.add(" PHP语言 ");
//        list.add("  .net语言   ");
        for (int i=0;i<list.size();i++){
            creator.add(list.get(i), KnowVpFragment.class);
        }
    }
    private void InitPager(){
        vp_know= (ViewPager) view.findViewById(R.id.vp_know);
        adapter=new FragmentPagerItemAdapter(getFragmentManager(),creator.create());
        vp_know.setAdapter(adapter);
        SmartTabLayout smartTabLayou= (SmartTabLayout) view.findViewById(R.id.smarttablayout);
        smartTabLayou.setViewPager(vp_know);
    }
}
