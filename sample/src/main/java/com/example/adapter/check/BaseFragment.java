package com.example.adapter.check;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.adapter.MainActivity.Man;
import com.example.adapter.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

public abstract class BaseFragment extends Fragment {
    protected RecyclerView mRecyclerView;
    protected View mContentView;
    protected List<Man> mManList = new ArrayList<>();
    protected CheckAdapter mAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContentView = inflater.inflate(R.layout.fragment_check,container,false);
        mRecyclerView = (RecyclerView) mContentView.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        initView();
        return mContentView;
    }

    protected abstract void initView();
}
