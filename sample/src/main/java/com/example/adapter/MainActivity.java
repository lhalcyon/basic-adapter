package com.example.adapter;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ListView;

import com.lhalcyon.adapter.BasicAdapter;
import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    List<Man> mManList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AlertDialog.Builder eee = null;
        ListView listView = null;
        RecyclerView recyclerView = null;
        SwipeRefreshLayout refreshLayout;
        View header = View.inflate(this,R.layout.header,null);
        View header2 = View.inflate(this,R.layout.header2,null);
        View footer = View.inflate(this,R.layout.footer,null);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        BasicParams p = new BasicController.Builder()
                .layoutRes(R.layout.item)
                .addHeaderView(header)
                .addHeaderView(header2)
                .addFooterView(footer)
                .build();
        //normal item data init
        for (int i = 0; i < 12; i++) {
            mManList.add(new Man("name"+i));
        }

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MyAdapter(p,mManList));


    }

    public static class Man{
        public String name;
        public boolean isSingle;

        public Man(String name) {
            this.name = name;
        }

        public Man(String name, boolean isSingle) {
            this.name = name;
            this.isSingle = isSingle;
        }
    }

    class MyAdapter extends BasicAdapter<Man>{

        public MyAdapter(BasicParams params, List<Man> data) {
            super(params, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, int position, Man man) {
            holder.setText(R.id.tv_name,man.name);
        }
    }
}
