package com.lhalcyon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.ViewGroup;

import com.lhalcyon.adapter.helper.BasicController.BasicParams;

import java.util.List;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

public class BasicAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    BasicParams mParams;
    protected Context mContext;
    protected List<T> mData;

    public BasicAdapter<T> setData(List<T> data) {
        mData = data;
        return this;
    }

    public BasicAdapter<T> setParams(BasicParams params) {
        mParams = params;
        return this;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
