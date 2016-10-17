package com.example.adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.adapter.MainActivity.Man;
import com.lhalcyon.adapter.BasicAdapter;
import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;

import java.util.ArrayList;
import java.util.List;

public class CheckActivity extends AppCompatActivity {
    RecyclerView mRecyclerView;
    CheckAdapter mAdapter;
    List<Man> mManList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));


        for (int i = 0; i < 2; i++) {
            mManList.add(new Man("name"+i,true));
        }
        for (int i = 0; i < 8; i++) {
            mManList.add(new Man("name"+i,false));
        }


        BasicParams params = new BasicController.Builder()
                .checkId(R.id.checkbox)
                .choiceMode(BasicController.CHOICE_MODE_MULTIPLE)
                .layoutRes(R.layout.item_check)
                .build();
        mRecyclerView.setAdapter(mAdapter = new CheckAdapter(params,mManList));
    }

    class CheckAdapter extends BasicAdapter<Man>{

        public CheckAdapter(BasicParams params, List<Man> data) {
            super(params, data);
        }



        @Override
        protected void convert(BaseViewHolder holder, int position, Man man) {
            holder.setText(R.id.tv_name,man.name);
        }
    }
}
