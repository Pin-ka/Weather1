package com.pinka.weather1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private String stringCityName;
    private boolean []checkBoxes=new boolean[3];
    private TextView cityName;
    private LinearLayout temperLayout;
    private LinearLayout pressLayout;
    private LinearLayout cloudLayout;

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        findViewMethod();
        setSityFromIntent();
    }

    private void findViewMethod (){
        cityName=findViewById(R.id.cityName);
        temperLayout=findViewById(R.id.temperLayout);
        pressLayout=findViewById(R.id.pressLayout);
        cloudLayout=findViewById(R.id.cloudLayout);
    }

    private void setSityFromIntent (){
       stringCityName=getIntent().getStringExtra(MainActivity.keyToCityName);
       cityName.setText(stringCityName);
       checkBoxes=getIntent().getBooleanArrayExtra(MainActivity.keyToCheckBox);
       if(!checkBoxes[0]) temperLayout.setVisibility(View.GONE);
       if(!checkBoxes[1]) pressLayout.setVisibility(View.GONE);
       if(!checkBoxes[2]) cloudLayout.setVisibility(View.GONE);
    }
}
