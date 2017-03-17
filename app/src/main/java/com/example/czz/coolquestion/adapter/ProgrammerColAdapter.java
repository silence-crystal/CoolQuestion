package com.example.czz.coolquestion.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.bean.ProgrammerNewsCol;
import com.example.czz.coolquestion.url.URLConfig;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/3/11.
 */

public class ProgrammerColAdapter extends BaseAdapter {
    private List<ProgrammerNewsCol.NewsCollectListBean> list=new ArrayList<ProgrammerNewsCol.NewsCollectListBean>();
    private Context context;
    private LayoutInflater inflater;
    private ImageLoader imageLoader;

    public ProgrammerColAdapter(Context context) {
        this.context = context;
        inflater=LayoutInflater.from(context);
    }

    public List<ProgrammerNewsCol.NewsCollectListBean> getList() {
        return list;
    }

    public void setList(List<ProgrammerNewsCol.NewsCollectListBean> list) {
        this.list = list;
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
            vh.img_pic= (ImageView) view.findViewById(R.id.imageView_hp_newspic);
            view.setTag(vh);
        }else {
            vh= (ViewHolder) view.getTag();
        }
        imageLoader=ImageLoader.getInstance();
        DisplayImageOptions options=new DisplayImageOptions.Builder()
                .showImageOnFail(R.mipmap.ic_launcher)
                .showImageOnLoading(R.mipmap.ic_launcher)
                .cacheOnDisk(true)
                .cacheInMemory(true)
                .build();
        imageLoader.displayImage("http://"+URLConfig.MAIN_URL+":8080/"+list.get(i).getNewscontent().getNewspic(),vh.img_pic,options);
        vh.tv_title.setText(list.get(i).getNewscontent().getNewsTitle());
        vh.tv_time.setText(list.get(i).getNewscontent().getNewspublishtime());
        vh.tv_publisher.setText(list.get(i).getNewscontent().getNewspublisher());
        vh.tv_describe.setText(list.get(i).getNewscontent().getNewsdescribe());

        return view;
    }

    class  ViewHolder{
        ImageView img_pic;
        TextView tv_title,tv_time,tv_describe,tv_publisher;
    }

}
