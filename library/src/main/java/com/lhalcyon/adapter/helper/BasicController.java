package com.lhalcyon.adapter.helper;

import android.support.annotation.LayoutRes;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

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
            p.layoutId = layoutId;
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
        public int choiceMode = CHOICE_MODE_NONE;
        public int checkId = -1;
        public List<View> headers = new ArrayList<>();
        public List<View> footers = new ArrayList<>();
        public View loading;
        public View loaded;
        public View empty;
        @LayoutRes public int layoutId;

    }
}
