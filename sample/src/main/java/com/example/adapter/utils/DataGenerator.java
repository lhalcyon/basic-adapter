package com.example.adapter.utils;

import com.example.adapter.MainActivity.Man;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lh_ha on 2016/12/9.
 */

public class DataGenerator {

    public static List<Man> generate(int start, int count){
        List<Man> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            list.add(new Man("name"+(start+i)));
        }
        return list;
    }
}
