package com.example.czz.coolquestion.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czz.coolquestion.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/3/3.
 */

public class Result_GV_Adapter extends BaseAdapter {
    private ArrayList<String> select_list;//用户选择的答案
    private ArrayList<String> correct_list;//正确的答案
    private Context context;

    public void setSelect_list(ArrayList<String> select_list) {
        this.select_list = select_list;
    }

    public void setCorrect_list(ArrayList<String> correct_list) {
        this.correct_list = correct_list;
    }

    public Result_GV_Adapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return select_list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder=null;
        if (convertView==null){
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.act_result_gv_item,null);
            viewHolder.img= (ImageView) convertView.findViewById(R.id.act_result_gv_item_img);
            viewHolder.tv= (TextView) convertView.findViewById(R.id.act_result_gv_item_tv);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }


            if (select_list.get(position).equals(correct_list.get(position))){//选择的答案和正确答案一致
                viewHolder.img.setImageResource(R.mipmap.result_correct);
            }else{//选择的答案和正确答案不一致
                viewHolder.img.setImageResource(R.mipmap.result_error);
            }
            viewHolder.tv.setText((position+1)+"");



        return convertView;
    }

    class ViewHolder{
        ImageView img;
        TextView tv;
    }
}
