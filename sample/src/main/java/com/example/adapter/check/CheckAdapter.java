package com.example.adapter.check;

import com.example.adapter.MainActivity.Man;
import com.example.adapter.R;
import com.lhalcyon.adapter.BasicAdapter;
import com.lhalcyon.adapter.base.BaseViewHolder;
import com.lhalcyon.adapter.helper.BasicController.BasicParams;

import java.util.List;

/**
 * ©2016-2017 kmhealthcloud.All Rights Reserved <p/>
 * Created by: L  <br/>
 * Description:
 */

public class CheckAdapter extends BasicAdapter<Man> {

    public CheckAdapter(BasicParams params, List<Man> data) {
        super(params, data);
    }

    @Override
    protected void convert(BaseViewHolder holder, int position, Man man) {
        holder.setText(R.id.tv_name, man.name);
    }
}
