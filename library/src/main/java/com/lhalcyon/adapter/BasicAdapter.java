package com.lhalcyon.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;
import com.lhalcyon.adapter.helper.OnItemClickListener;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

public abstract class BasicAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    public static final int NORMAL_VIEW = 0;//normal item
    public static final int HEADER_VIEW = 1;//header part
    public static final int LOAD_VIEW = 2;//loading/loaded part
    public static final int FOOTER_VIEW = 3;//footer part
    public static final int EMPTY_VIEW = 4;//empty part


    BasicParams mParams;
    protected Context mContext;
    protected List<T> mData;
    protected boolean isLoadEnable = true;
    /**
     * container of headers
     */
    private LinearLayout mHeaderLayout;
    /**
     * container of footers
     */
    private LinearLayout mFooterLayout;
    /**
     * container of empty view
     */
    private LinearLayout mEmptyLayout;
    /**
     * container of loading or loaded view
     */
    private LinearLayout mLoadLayout;

    private OnItemClickListener mOnItemClickListener;

    public BasicAdapter(BasicParams params, List<T> data) {
        mParams = params;
        this.mData = data;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }



    public boolean isItemChecked(T t, int position) {
        return false;
    }

    public void itemToggle(ViewHolder holder) {
        if (holder instanceof BaseViewHolder && mParams.checkId != -1) {
            BaseViewHolder vh = (BaseViewHolder) holder;
        }
    }

    public int getHeadersCount() {
        return mParams.headers.size();
    }

    public int getFootersCount() {
        return mParams.footers.size();
    }

    @Override
    public int getItemViewType(int position) {
        int empty = mData.size() == 0 ? 1 : 0;

        if(empty == 1 && position == 1){
            return EMPTY_VIEW;
        }
        if (position == 0) {
            return HEADER_VIEW;
        } else if (position == mData.size() + 1 + empty) {
            return LOAD_VIEW;
        } else if (position == mData.size() + 2 + empty) {
            return FOOTER_VIEW;
        } else {
            return NORMAL_VIEW;
        }

    }


    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        BaseViewHolder vh = null;
        switch (viewType) {
            case EMPTY_VIEW:
                vh = onCreateEmptyHolder(parent);
                break;
            case NORMAL_VIEW:
                vh = onCreateDefViewHolder(parent);
                break;
            case HEADER_VIEW:
                vh = onCreateHeaderHolder(parent);
                break;
            case FOOTER_VIEW:
                vh = onCreateFooterHolder(parent);
                break;
            case LOAD_VIEW:
                vh = onCreateLoadHolder(parent);
                break;

        }
        return vh;
    }

    private BaseViewHolder onCreateLoadHolder(ViewGroup parent) {
        mLoadLayout = new LinearLayout(parent.getContext());
        mLoadLayout.setOrientation(LinearLayout.VERTICAL);
        mLoadLayout.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        if (mParams.loading != null) {
            mLoadLayout.addView(mParams.loading);
            mParams.loading.setVisibility(View.GONE);
        }
        if (mParams.loaded != null) {
            mLoadLayout.addView(mParams.loaded);
            mParams.loaded.setVisibility(View.GONE);
        }
        return new BaseViewHolder(mLoadLayout);
    }

    private BaseViewHolder onCreateEmptyHolder(ViewGroup parent) {
        mEmptyLayout = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.empty_container,parent,false);
        if (mParams.empty != null) {
            mEmptyLayout.addView(mParams.empty,0,new LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.MATCH_PARENT));
        }
        return new BaseViewHolder(mEmptyLayout);
    }

    private BaseViewHolder onCreateFooterHolder(ViewGroup parent) {
        mFooterLayout = new LinearLayout(parent.getContext());
        mFooterLayout.setOrientation(LinearLayout.VERTICAL);
        mFooterLayout.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        for (View footer : mParams.footers) {
            mFooterLayout.addView(footer);
        }
        return new BaseViewHolder(mFooterLayout);
    }

    private BaseViewHolder onCreateHeaderHolder(ViewGroup parent) {
        mHeaderLayout = new LinearLayout(parent.getContext());
        mHeaderLayout.setOrientation(LinearLayout.VERTICAL);
        mHeaderLayout.setLayoutParams(new LayoutParams(MATCH_PARENT, WRAP_CONTENT));
        for (View header : mParams.headers) {
            mHeaderLayout.addView(header);
        }
        return new BaseViewHolder(mHeaderLayout);
    }

    protected BaseViewHolder onCreateDefViewHolder(ViewGroup parent) {
        return createBaseViewHolder(parent, mParams.layoutId);
    }

    protected BaseViewHolder createBaseViewHolder(ViewGroup parent, int layoutResId) {
        return new BaseViewHolder(getItemView(layoutResId, parent));
    }

    protected View getItemView(int layoutResId, ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(layoutResId, parent, false);
        return process(itemView);
    }

    /**
     * @param itemView view of normal item
     * @return processed view of normal item ;do nothing by default
     */
    private View process(View itemView) {
        return itemView;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        int viewType = holder.getItemViewType();
        BaseViewHolder vh = (BaseViewHolder) holder;
        switch (viewType) {
            case LOAD_VIEW:
                configLoad(holder);
                break;
            case HEADER_VIEW:
                //do nothing
                break;
            case EMPTY_VIEW:
                //do nothing
                break;
            case FOOTER_VIEW:
                //do nothing
                break;
            case NORMAL_VIEW:
                int realPosition = holder.getAdapterPosition() - 1;
                T t = mData.get(realPosition);
                convert(vh, realPosition, t);
                if (mParams.checkId != -1) {
                    vh.setChecked(mParams.checkId, isItemChecked(t, position));
                }
                break;

        }

    }


    private void configLoad(ViewHolder holder) {
        //if size of data is 0,empty view shows if exists and loading/loaded view  hides.
        if (mData.size() == 0) {
            return;
        }
        if (mParams.loading != null && isLoadEnable()) {
            mParams.loading.setVisibility(View.VISIBLE);
            if(mParams.onLoadMoreListener == null){
                throw new RuntimeException("OnLoadMoreListener should be init when build loading view !");
            }
            mParams.onLoadMoreListener.onLoad();
        }
    }

    private boolean isLoadEnable() {
        return isLoadEnable;
    }

    public void finishLoad(){
        if(mParams.loading != null){
            mParams.loading.setVisibility(View.GONE);
        }
    }

    public void doneLoad(){
        isLoadEnable = false;
        mParams.loading.setVisibility(View.GONE);
        mParams.loaded.setVisibility(View.VISIBLE);
    }

    protected abstract void convert(BaseViewHolder holder, int position, T t);




    /**
     * When set to true, the item will layout using all span area. That means, if orientation
     * is vertical, the view will have full width; if orientation is horizontal, the view will
     * have full height.
     * if the hold view use StaggeredGridLayoutManager they should using all span area
     *
     * @param holder True if this item should traverse all spans.
     */
    protected void setFullSpan(RecyclerView.ViewHolder holder) {
        if (holder.itemView.getLayoutParams() instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
            params.setFullSpan(true);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    int type = getItemViewType(position);
                    return (type == EMPTY_VIEW || type == HEADER_VIEW || type == FOOTER_VIEW || type == LOAD_VIEW) ? gridManager.getSpanCount() : 1;
                }
            });
        }
    }



    @Override
    public int getItemCount() {
        //header container + list size + empty + loading/loaded + footer container
        int empty = mData.size() == 0 ? 1 : 0;
        Log.i("halcyon","count:" +empty+mData.size()+1+1+1);
        return 1 + mData.size() + 1 + 1 + empty;
    }
}
