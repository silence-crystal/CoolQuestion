package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/1.
 * 知识点详情
 */

public class KnowDetailsActivity extends Activity{
    private ImageView know_details_hand_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_know_details);
        know_details_hand_img= (ImageView) findViewById(R.id.know_details_hand_img);
        know_details_hand_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
