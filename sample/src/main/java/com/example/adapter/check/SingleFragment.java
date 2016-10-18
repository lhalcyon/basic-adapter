package com.example.adapter.check;

import android.widget.Toast;

import com.example.adapter.MainActivity.Man;
import com.example.adapter.R;
import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;
import com.lhalcyon.adapter.helper.OnItemClickListener;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

public class SingleFragment extends BaseFragment {

    @Override
    protected void initView() {
        for (int i = 0; i < 1; i++) {
            mManList.add(new Man("man"+i,true));
        }
        for (int i = 1; i < 20; i++) {
            mManList.add(new Man("man"+i));
        }

        BasicParams params = new BasicController.Builder()
                .checkId(R.id.checkbox)
                .choiceMode(BasicController.CHOICE_MODE_SINGLE)
                .layoutRes(R.layout.item_check)
                .build();
        mRecyclerView.setAdapter(mAdapter = new CheckAdapter(params,mManList){
            @Override
            public boolean isItemChecked(Man man, int position) {
                return man.isSingle;
            }
        });
        mAdapter.setOnItemClickListener(mRecyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder vh, int position) {
                Toast.makeText(getActivity(), "position " +position +" clicked!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
