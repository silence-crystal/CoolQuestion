package com.example.czz.coolquestion.application;

import android.app.Application;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.io.File;

/**
 * Created by dell on 2017/3/9.
 */

public class FirstApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化ImageLoader
        ImageLoaderConfiguration configuration=new ImageLoaderConfiguration.Builder(getApplicationContext())
                .threadPoolSize(4)
                .threadPriority(10)
                .diskCache(new UnlimitedDiskCache(new File("/mnt/sdcard/imageloader")))
                .memoryCacheExtraOptions(200,200)
                .build();
        ImageLoader.getInstance().init(configuration);
    }
}
