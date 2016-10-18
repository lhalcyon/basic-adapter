package com.example.adapter.check;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.adapter.R;

public class CheckActivity extends AppCompatActivity {





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);
        show(0);




    }

    private void show(int index){
        BaseFragment fragment = null;
        switch (index){
            case 0:
                fragment = new NoneFragment();
                break;
            case 1:
                fragment = new SingleFragment();
                break;
            case 2:
                fragment = new MultipleFragment();
                break;
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,fragment)
                .commit();
    }

    public void none(View v) {
        show(0);
    }

    public void single(View v) {
        show(1);
    }

    public void multiple(View v) {
        show(2);
    }


}
