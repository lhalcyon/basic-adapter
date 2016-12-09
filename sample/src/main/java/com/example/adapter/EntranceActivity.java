package com.example.adapter;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.adapter.check.CheckActivity;
import com.example.adapter.multi.MultiTypeActivity;

public class EntranceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entrance);
    }

    public void normal(View v){
        startActivity(new Intent(this,MainActivity.class));
    }

    public void checkable(View v){
        startActivity(new Intent(this,CheckActivity.class));
    }

    public void multi(View v){
        startActivity(new Intent(this,MultiTypeActivity.class));
    }
}
