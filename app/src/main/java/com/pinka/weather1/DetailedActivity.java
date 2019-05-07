package com.pinka.weather1;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


public class DetailedActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            finish();
            return;
        }
        if (savedInstanceState == null) {
            DetailedFragment details=new DetailedFragment();
            details.setArguments(getIntent().getExtras());

            getSupportFragmentManager().beginTransaction().
                    replace(R.id.fragment_detailed_container, details).commit();
        }
    }
}
