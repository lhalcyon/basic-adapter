package com.lhalcyon.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.LayoutParams;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;

import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

public abstract class BasicAdapter<T> extends RecyclerView.Adapter<ViewHolder> {
    public static final int NORMAL_VIEW = 0;
    public static final int HEADER_VIEW = 1;
    public static final int LOADING_VIEW = 2;
    public static final int FOOTER_VIEW = 3;
    public static final int EMPTY_VIEW = 4;


    BasicParams mParams;
    protected Context mContext;
    protected List<T> mData;
    private LinearLayout mHeaderLayout;
    private LinearLayout mFooterLayout;

    public BasicAdapter(BasicParams params,List<T> data) {
        mParams = params;
        this.mData = data;
    }


    public boolean isItemChecked(T t, int position) {
        return false;
    }

    public void itemToggle() {

    }

    public int getHeaderViewCount() {
        return mParams.headers.size();
    }

    public int getFooterViewCount() {
        return mParams.footers.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (mData.size() > 0) {
            if (position == 0) { //position 0 is headers container
                return HEADER_VIEW;
            } else if (position == mData.size() + 1) {//position mData.size()+1 is loading
                return LOADING_VIEW;
            } else if (position == mData.size() + 2) {//position mData.size()+2 is footers container
                return FOOTER_VIEW;
            } else {//otherwise  normal
                return NORMAL_VIEW;
            }
        } else { // mData.size() == 0 , empty view should hold a position, loading view is not necessary
            if (position == 0) {//position 0 is headers container
                return HEADER_VIEW;
            } else if (position == 1) {//position 1 is empty
                return EMPTY_VIEW;
            } else if (position == 2) {//position 2 is footers container
                return FOOTER_VIEW;
            } else {//no this circumstance
                return super.getItemViewType(position);
            }
        }


    }



    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        mContext = parent.getContext();

        BaseViewHolder vh = null;
        switch (viewType) {
            case EMPTY_VIEW:
                vh = new BaseViewHolder(mParams.empty);
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
            case LOADING_VIEW:
                vh = new BaseViewHolder(mParams.loadMore);
                break;

        }
        return vh;
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
            case LOADING_VIEW:
//                addLoadMore(holder);
                //todo load more
                break;
            case HEADER_VIEW:
                break;
            case EMPTY_VIEW:
                break;
            case FOOTER_VIEW:
                break;
            case NORMAL_VIEW:
            default:
                int realPosition = holder.getLayoutPosition() - 1;
                T t = mData.get(realPosition);
                convert(vh, realPosition,t);
                if (mParams.checkId != -1) {
                    vh.setChecked(mParams.checkId, isItemChecked(t, position));
                }
                break;

        }

    }

    protected abstract void convert(BaseViewHolder holder, int position, T t);

    @Override
    public int getItemCount() {
        int count = 1+1;//header container count + footer container count
        if(mData.size() == 0){
            count = mParams.empty == null?count:count+1;//count++ if there is a empty view
        }else{
            count += mData.size();
        }
        count = mParams.loadMore == null?count:count+1;
        return count;
    }
}
