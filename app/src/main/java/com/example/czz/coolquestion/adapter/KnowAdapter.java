package com.example.czz.coolquestion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.Know;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class KnowAdapter extends BaseAdapter{
    private LayoutInflater inflater;
    private List<Know> list=new ArrayList<>();
    private Context context;
    public KnowAdapter(Context context){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }
    public List<Know> getList() {
        return list;
    }
    public void setList(List<Know> list) {
        this.list.clear();
        this.list.addAll(list);
    }
    //添加数据的方法
    public void addKnowList(List<Know> list){
        this.list=list;
    }
    //上啦加载数据的方法
    public void addDataToFooter(List<Know> list){
        this.list.addAll(list);
    }
    //下拉加载数据的方法
    public void addDataToHeader(List<Know> list){
        this.list.addAll(0,list);
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
        ViewHolder vh = null;
        if (convertView==null){
            vh = new ViewHolder();
            convertView = inflater.inflate(R.layout.know_lv,null);
            vh.know_lv_tv = (TextView) convertView.findViewById(R.id.know_lv_tv);
            convertView.setTag(vh);
        }else {
            vh = (ViewHolder) convertView.getTag();
        }
        //
        vh.know_lv_tv.setText(list.get(position).getTimu());
        return convertView;
    }
    class ViewHolder{
        TextView know_lv_tv;
    }
}
