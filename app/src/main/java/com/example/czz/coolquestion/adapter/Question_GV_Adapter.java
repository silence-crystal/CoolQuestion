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
import com.example.czz.coolquestion.url.URLConfig;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

/**
 * Created by dell on 2017/3/1.
 */

public class Question_GV_Adapter extends BaseAdapter {
    private List<Language.TypeListBean> list;
    private Context context;
    private ImageLoader imageLoader;
    private DisplayImageOptions options;

    public void setList(List<Language.TypeListBean> list) {
        this.list = list;
    }

    public Question_GV_Adapter(Context context, ImageLoader imageLoader, DisplayImageOptions options) {
        this.context = context;
        this.imageLoader = imageLoader;
        this.options = options;
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

        if (list.get(position).isLanguage_select()==true){
            viewHolder.language_relativeLayout.setBackgroundColor(Color.rgb(248,248,248));
            //viewHolder.language_relativeLayout.setBackgroundColor(Color.BLUE);
        }else{
            viewHolder.language_relativeLayout.setBackgroundColor(Color.rgb(255,255,255));
        }
        viewHolder.language_name.setText(list.get(position).getTypeName());
        imageLoader.displayImage(URLConfig.MAIN_URL+list.get(position).getTypePic(),viewHolder.language_img,options);

        return convertView;
    }

    class ViewHolder{
        RelativeLayout language_relativeLayout;
        ImageView language_img;
        TextView language_name;
    }
}
