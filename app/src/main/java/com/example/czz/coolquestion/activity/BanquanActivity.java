package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/3.
 */

public class BanquanActivity extends Activity{
    private ImageView banquan_fanhui_img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banquan);
        banquan_fanhui_img= (ImageView) findViewById(R.id.banquan_fanhui_img);
        banquan_fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
