package com.example.czz.coolquestion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ProgrammerNews;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/1.
 */

public class ProgrammerAdapter extends BaseAdapter {
    private Context context;
    private LayoutInflater inflater;
    private List<ProgrammerNews> list=new ArrayList<ProgrammerNews>();

    public List<ProgrammerNews> getList() {
        return list;
    }

    public void setList(List<ProgrammerNews> list) {
        this.list = list;
    }



    public ProgrammerAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vh=new ViewHolder();
        if (view==null){
            view=inflater.inflate(R.layout.host_fra_lv_item,null);
            vh.tv_title= (TextView) view.findViewById(R.id.textView_hp_newstitle);
            vh.tv_describe= (TextView) view.findViewById(R.id.textView_hp_newsdescribe);
            vh.tv_publisher= (TextView) view.findViewById(R.id.textView_hp_newspublisher);
            vh.tv_time= (TextView) view.findViewById(R.id.textView_hp_newspublishtime);
            view.setTag(vh);
        }else{
            vh= (ViewHolder) view.getTag();
        }
        vh.tv_title.setText(list.get(i).getNewstitle());
        vh.tv_describe.setText(list.get(i).getNewsdescribe());
        vh.tv_time.setText(list.get(i).getNewspublishtime());
        vh.tv_publisher.setText(list.get(i).getNewspublisher());

        return view;
    }

    class  ViewHolder{
        ImageView img;
        TextView tv_title,tv_publisher,tv_time,tv_describe;
    }
}
