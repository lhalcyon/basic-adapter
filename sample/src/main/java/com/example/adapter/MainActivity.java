package com.example.adapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.lhalcyon.adapter.BasicAdapter;
import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;
import com.lhalcyon.adapter.helper.OnLoadMoreListener;
import com.lhalcyon.adapter.helper.OnRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements OnRefreshListener {
    RecyclerView mRecyclerView;
    MyAdapter mAdapter;
    List<Man> mManList = new ArrayList<>();
    private Handler mHandler = new Handler();
    int loadCount = 0;
    SwipeRefreshLayout mRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView;

        View header = View.inflate(this,R.layout.header,null);
        View header2 = View.inflate(this,R.layout.header2,null);
        View footer = View.inflate(this,R.layout.footer,null);
        View loading = View.inflate(this,R.layout.loading,null);
        View loaded = View.inflate(this,R.layout.loaded,null);
        View empty = View.inflate(this,R.layout.empty,null);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);
        BasicParams p = new BasicController.Builder()
                .layoutRes(R.layout.item)
                .header(header)
                .header(header2)
                .footer(footer)
                .empty(empty)
                .loaded(loaded)
                .loading(loading)
                .onLoadMore(new OnLoadMoreListener() {
                    @Override
                    public void onLoad() {
                        mHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                if(loadCount <2){
                                    mManList.addAll(random(mManList.size(),3));
                                    mAdapter.notifyDataSetChanged();
                                    mAdapter.finishLoad();
                                    loadCount++;
                                }else{
                                    mManList.addAll(random(mManList.size(),3));
                                    mAdapter.doneLoad();
                                }

                            }
                        },2000);
                    }
                })
                .build();
        //normal item data init
//        mManList.addAll(random(0,10));

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter = new MyAdapter(p,mManList));
        mRecyclerView.addOnItemTouchListener(new OnRecyclerItemClickListener(mRecyclerView){

            @Override
            public void onItemClick(ViewHolder vh) {
                int position = vh.getAdapterPosition();
                Toast.makeText(getApplicationContext(), "position "+position + " clicked", Toast.LENGTH_SHORT).show();
                Log.i("halcyon","position " +position + " click");
            }
        });
        mRefreshLayout.setOnRefreshListener(this);
        mRefreshLayout.post(new Runnable() {
            @Override
            public void run() {
                mRefreshLayout.setRefreshing(true);
                onRefresh();
            }
        });
    }

    private List<Man> random(int start,int count){
        List<Man> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Man("name"+(start+i)));
        }
        return list;
    }

    @Override
    public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mManList.clear();
                mManList.addAll(random(mManList.size(),5));
                mAdapter.notifyDataSetChanged();
                mRefreshLayout.setRefreshing(false);
            }
        },1500);
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
            Log.w("halcyon","设置position-数据"+position+"-"+""+man.name);
            holder.setText(R.id.tv_name,man.name);
        }
    }
}
