package com.example.czz.coolquestion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/2.
 */

public class ForthFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.forth_item,null);
        return view;
    }
}