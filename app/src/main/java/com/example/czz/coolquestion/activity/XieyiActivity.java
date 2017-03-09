package com.example.czz.coolquestion.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czz.coolquestion.R;

/**
 * Created by Administrator on 2017/3/3.
 */

public class XieyiActivity extends Activity{
    private ImageView xieyi_fanhui_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xieyi);
        xieyi_fanhui_img= (ImageView) findViewById(R.id.xieyi_fanhui_img);
        xieyi_fanhui_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
