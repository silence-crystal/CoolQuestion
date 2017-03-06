package com.example.czz.coolquestion.bean;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dell on 2017/3/1.
 */

public class Language {
    private String language_img;
    private String language_name;
    private String language_description;

    public String getLanguage_img() {
        return language_img;
    }

    public void setLanguage_img(String language_img) {
        this.language_img = language_img;
    }

    public String getLanguage_description() {
        return language_description;
    }

    public void setLanguage_description(String language_description) {
        this.language_description = language_description;
    }

    public String getLanguage_name() {
        return language_name;
    }

    public void setLanguage_name(String language_name) {
        this.language_name = language_name;
    }

    public Language(String language_img, String language_description, String language_name) {
        this.language_img = language_img;
        this.language_description = language_description;
        this.language_name = language_name;
    }
}
