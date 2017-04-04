package com.example.czz.coolquestion.activity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;


import com.example.czz.coolquestion.R;
import com.example.czz.coolquestion.adapter.ProgrammerAdapter;
import com.example.czz.coolquestion.bean.ProgrammerNews;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageView iv_search;
    private EditText et_search;
    private ListView lv;
    private List<ProgrammerNews> ll;//接收传递过来的list集合
    private List<ProgrammerNews> search_list;//匹配后的List集合
    private ProgrammerAdapter adapter;//显示数据的适配器

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        //Log.i("news_list",ll.size()+"");
        InitView();

    }

    //各种控件
    public void InitView(){

        lv= (ListView) findViewById(R.id.listview_search);
        lv.setOnItemClickListener(this);
        //输入框
        et_search= (EditText) findViewById(R.id.editText_search);
        et_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                    if (editable.length()==0){
                        List<ProgrammerNews> list=new ArrayList<ProgrammerNews>();
                        adapter.setList(list);
                        adapter.notifyDataSetChanged();
                    }
            }
        });
        //搜索按钮
        iv_search= (ImageView) findViewById(R.id.imageView_search_cai);
        iv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(et_search.getText().toString().trim())){
                    Toast.makeText(SearchActivity.this,"请输入搜索内容",Toast.LENGTH_SHORT).show();
                }else {
                   search_list=ShowListView();
                    if (search_list.size()==0){
                        Toast.makeText(SearchActivity.this,"没有与内容相匹配的新闻",Toast.LENGTH_SHORT).show();
                    }else{
                        adapter.setList(search_list);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

        search_list=new ArrayList<ProgrammerNews>();
        adapter=new ProgrammerAdapter(SearchActivity.this);
        ll=(List<ProgrammerNews>)getIntent().getSerializableExtra("news_list");
        if (ll==null){
            Toast.makeText(SearchActivity.this,"传递过来的集合weinull",Toast.LENGTH_SHORT).show();
        }else{
            if (ll.size()==0){
                Toast.makeText(SearchActivity.this,ll.size()+"传递过来的集合没有数据",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(SearchActivity.this,ll.size()+"",Toast.LENGTH_SHORT).show();
            }
        }
        lv.setAdapter(adapter);


    }


    public List<ProgrammerNews> ShowListView(){
        List<ProgrammerNews> list=new ArrayList<ProgrammerNews>();
            String str=et_search.getText().toString().trim();
            for (int i=0;i<ll.size();i++){
                if (ll.get(i).getNewsTitle().contains(str)){
                    list.add(ll.get(i));
                }else{
                    continue;
                }
            }
        return list;
    }




    //点击空白处隐藏键盘
    public boolean onTouchEvent(MotionEvent event) {
        if(null != this.getCurrentFocus()){
            /**
             * 点击空白位置 隐藏软键盘
             */
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super .onTouchEvent(event);
    }


    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Intent intent=new Intent(SearchActivity.this,NewsInfoActivity.class);
        intent.putExtra("info",search_list.get(i));
        startActivity(intent);
    }
}
