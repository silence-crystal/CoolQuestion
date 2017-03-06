package com.example.czz.coolquestion.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;
import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.fragment.FirstFragment;
import com.example.czz.coolquestion.fragment.ForthFragment;
import com.example.czz.coolquestion.fragment.SecondFragment;
import com.example.czz.coolquestion.fragment.ThirdFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/2.
 * 引导页activity
 */

public class GuideActivity extends FragmentActivity{
    private ViewPager vp;
    private ImageView img1,img2,img3,img4;
    private Fragment f1,f2,f3,f4;
    private SharedPreferences sp;
    private List<Fragment> list=new ArrayList<Fragment>();
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        sp=getSharedPreferences("mode",MODE_PRIVATE);
        int number = sp.getInt("flag", 0);
        if (number == 0) {
            initView();
        } else if (number == 1) {
            Intent intent = new Intent(GuideActivity.this, FlashActivity.class);
            startActivity(intent);
            finish();
        }
    }
    private void initView(){
        vp= (ViewPager) findViewById(R.id.guide_viewpage);
        f1=new FirstFragment();
        f2=new SecondFragment();
        f3=new ThirdFragment();
        f4=new ForthFragment();
        img1= (ImageView) findViewById(R.id.iv1);
        img2= (ImageView) findViewById(R.id.iv2);
        img3= (ImageView) findViewById(R.id.iv3);
        img4= (ImageView) findViewById(R.id.iv4);
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        img1.setAlpha(255);
        img2.setAlpha(10);
        img3.setAlpha(10);
        img4.setAlpha(10);
        GuideFragmentAdapter adapter=new GuideFragmentAdapter(getSupportFragmentManager());
        vp.setAdapter(adapter);
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (position==0){
                    img1.setAlpha(255);
                    img2.setAlpha(10);
                    img3.setAlpha(10);
                    img4.setAlpha(10);
                }else if (position==1){
                    img1.setAlpha(10);
                    img2.setAlpha(255);
                    img3.setAlpha(10);
                    img4.setAlpha(10);
                }else if (position==2){
                    img1.setAlpha(10);
                    img2.setAlpha(10);
                    img3.setAlpha(255);
                    img4.setAlpha(10);
                }else if (position==3){
                    img1.setAlpha(10);
                    img2.setAlpha(10);
                    img3.setAlpha(10);
                    img4.setAlpha(255);
                    Intent intent=new Intent(GuideActivity.this,FlashActivity.class);
                    startActivity(intent);
                    SharedPreferences.Editor editor=sp.edit();
                    editor.putInt("flag",1);
                    editor.commit();
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    class GuideFragmentAdapter extends FragmentPagerAdapter {

        public GuideFragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return list.get(position);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
