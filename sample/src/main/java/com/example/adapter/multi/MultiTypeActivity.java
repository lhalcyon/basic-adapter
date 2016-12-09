package com.example.adapter.multi;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.adapter.MainActivity.Man;
import com.example.adapter.R;
import com.example.adapter.utils.DataGenerator;
import com.lhalcyon.adapter.BasicAdapter;
import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;

import java.util.ArrayList;
import java.util.List;

public class MultiTypeActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_type);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refresh);

        BasicParams p = new BasicController.Builder()
                .layoutRes(R.layout.item)
                .layoutRes(R.layout.item2,1)
                .layoutRes(R.layout.item3,2)
                .build();


        List<Man> dataList = new ArrayList<>();
        dataList.addAll(DataGenerator.generate(0,10));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(new MyAdapter(p,dataList));
    }

    class MyAdapter extends BasicAdapter<Man> {

        public MyAdapter(BasicParams params, List<Man> data) {
            super(params, data);
        }

        @Override
        protected void convert(BaseViewHolder holder, int position, Man man) {
            Log.w("halcyon","设置position-数据"+position+"-"+""+man.name);
//            holder.setText(R.id.tv_name,man.name);
        }
    }
}
