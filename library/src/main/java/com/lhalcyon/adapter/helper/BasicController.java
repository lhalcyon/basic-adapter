package com.lhalcyon.adapter.helper;

import android.util.SparseIntArray;
import android.view.View;

import com.lhalcyon.adapter.error.IllegalArgumentError;

import java.util.ArrayList;
import java.util.List;


public class BasicController {

    /**
     * Normal list that does not indicate choices
     */
    public static final int CHOICE_MODE_NONE = 0;

    /**
     * The list allows up to one choice
     */
    public static final int CHOICE_MODE_SINGLE = 1;

    /**
     * The list allows multiple choices
     */
    public static final int CHOICE_MODE_MULTIPLE = 2;

    public static class Builder{

        private final BasicParams p;

        public Builder() {
            this.p = new BasicParams();
        }

        public Builder layoutRes(int layoutId){
            p.layoutResArray.put(0,layoutId);
            return this;
        }

        public Builder layoutRes(int layoutId,int viewType){
            if(viewType <0){
                throw new IllegalArgumentError("illegal view type.[0,+)");
            }
            p.layoutResArray.put(viewType,layoutId);
            return this;
        }

        public Builder choiceMode(int choiceMode){
            p.choiceMode = choiceMode;
            return this;
        }

        public Builder checkId(int checkId){
            p.checkId = checkId;
            return this;
        }

        public Builder header(View header){
            p.headers.add(header);
            return this;
        }

        public Builder footer(View footer){
            p.footers.add(footer);
            return this;
        }

        public Builder loading(View loadMore){
            p.loading = loadMore;
            return this;
        }

        public Builder onLoadMore(OnLoadMoreListener listener){
            p.onLoadMoreListener = listener;
            return this;
        }

        public Builder loaded(View loaded){
            p.loaded = loaded;
            return this;
        }

        public Builder empty(View empty){
            p.empty = empty;
            return this;
        }

        public BasicParams build(){
            return p;
        }


    }

    public static class BasicParams{
        /**
         * choice mode of RecyclerView
         */
        public int choiceMode = CHOICE_MODE_NONE;
        /**
         * the id of the checkable component
         */
        public int checkId = -1;
        /**
         * headers container
         */
        public List<View> headers = new ArrayList<>();
        /**
         * footers container
         */
        public List<View> footers = new ArrayList<>();
        /**
         * loading view
         */
        public View loading;
        /**
         * loaded view
         */
        public View loaded;
        /**
         * empty view
         */
        public View empty;
        /**
         * listener action when {@link #loading} view shows
         */
        public OnLoadMoreListener onLoadMoreListener;

        /**
         * layout resource array
         */
        public SparseIntArray layoutResArray = new SparseIntArray();

    }
}
