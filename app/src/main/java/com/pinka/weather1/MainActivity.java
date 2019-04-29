package com.pinka.weather1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView btnGo;
    private EditText cityName;
    private String stringCityName;
    private CheckBox temperBox;
    private CheckBox pressBox;
    private CheckBox cloudBox;
    private boolean []checkBoxes=new boolean[3];
    static final String keyToCityName="keyToCityName";
    static final String keyToCheckBox="keyToCheckBox";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewMethod();
        listenersMethod();
    }

    private void findViewMethod (){
        btnGo=findViewById(R.id.btnGo);
        cityName=findViewById(R.id.cityName);
        temperBox=findViewById(R.id.temperBox);
        pressBox=findViewById(R.id.pressBox);
        cloudBox=findViewById(R.id.cloudBox);
    }

    private void listenersMethod(){
        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringCityName = cityName.getText().toString();
                checkBoxes[0]=temperBox.isChecked();
                checkBoxes[1]=pressBox.isChecked();
                checkBoxes[2]=cloudBox.isChecked();
                Intent intent = new Intent(MainActivity.this,SecondActivity.class);
                intent.putExtra(keyToCityName,stringCityName);
                intent.putExtra(keyToCheckBox,checkBoxes);
                startActivity(intent);
            }
        });
    }

}
