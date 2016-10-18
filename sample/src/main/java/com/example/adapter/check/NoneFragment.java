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

public class NoneFragment extends BaseFragment {

    @Override
    protected void initView() {
        BasicParams params = new BasicController.Builder()
                .layoutRes(R.layout.item)
                .build();

        for (int i = 0; i < 6; i++) {
            mManList.add(new Man("man"+i));
        }
        mRecyclerView.setAdapter(mAdapter = new CheckAdapter(params,mManList));

        mAdapter.setOnItemClickListener(mRecyclerView, new OnItemClickListener() {
            @Override
            public void onItemClick(BaseViewHolder vh, int position) {
                Toast.makeText(getActivity(), "name:"+mManList.get(position).name, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
