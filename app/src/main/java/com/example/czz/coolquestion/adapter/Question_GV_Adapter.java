package com.example.czz.coolquestion.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.Language;

import java.util.List;

/**
 * Created by dell on 2017/3/1.
 */

public class Question_GV_Adapter extends BaseAdapter {
    private List<Language> list;
    private Context context;

    public void setList(List<Language> list) {
        this.list = list;
    }

    public Question_GV_Adapter(Context context) {
        this.context = context;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {

        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            convertView= LayoutInflater.from(context).inflate(R.layout.question_gv_item,null);
            viewHolder=new ViewHolder();
            viewHolder.language_img= (ImageView) convertView.findViewById(R.id.question_gv_item_img);
            viewHolder.language_name= (TextView) convertView.findViewById(R.id.question_gv_item_tv);
            viewHolder.language_relativeLayout= (RelativeLayout) convertView.findViewById(R.id.question_gv_item_layout);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }

        for (Language language:list) {
            if (language.isLanguage_select()==true){
                viewHolder.language_relativeLayout.setBackgroundColor(Color.rgb(248,248,248));
            }else{
                viewHolder.language_relativeLayout.setBackgroundColor(Color.rgb(255,255,255));
            }
        }


        return convertView;
    }

    class ViewHolder{
        RelativeLayout language_relativeLayout;
        ImageView language_img;
        TextView language_name;
    }
}
