package com.example.czz.coolquestion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ErrorQuestion;
import com.example.czz.coolquestion.bean.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dell on 2017/3/7.
 */

public class CTB_Adapter extends BaseAdapter {
    private Context context;
    private List<ErrorQuestion.ErrorQuestionlistBean> list=new ArrayList<ErrorQuestion.ErrorQuestionlistBean>();//用来存放集错题的集合

    public CTB_Adapter(Context context) {
        this.context = context;
    }

    public void setList(List<ErrorQuestion.ErrorQuestionlistBean> list) {
        this.list = list;
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
        if (convertView==null) {
            viewHolder=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.card_lv_item,null);
            viewHolder.question_language_tv= (TextView) convertView.findViewById(R.id.card_lv_item_question_language);
            viewHolder.question_title_tv= (TextView) convertView.findViewById(R.id.card_lv_item_question_title);
            convertView.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) convertView.getTag();
        }
        viewHolder.question_language_tv.setText("专项练习:"+list.get(position).getEContent().getTid());
        viewHolder.question_title_tv.setText("题目："+list.get(position).getEContent().getQuestionContent());
        return convertView;
    }

    class ViewHolder{
        TextView question_language_tv;
        TextView question_title_tv;
    }
}
