package com.pinka.weather1;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import java.util.Objects;

public class SettingsFragment extends Fragment {

    private ImageView btnOk;
    private CheckBox imageBox;
    private CheckBox pressBox;
    private CheckBox cloudBox;
    public static boolean []checkBoxes=new boolean[3];

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        findViewMethod(view);
        listenersMethod();
    }

    private void findViewMethod (View view){
        btnOk=view.findViewById(R.id.btnOk);
        imageBox=view.findViewById(R.id.imageBox);
        if(checkBoxes[0]) imageBox.setChecked(true);
        pressBox=view.findViewById(R.id.pressBox);
        if(checkBoxes[1]) pressBox.setChecked(true);
        cloudBox=view.findViewById(R.id.cloudBox);
        if(checkBoxes[2]) cloudBox.setChecked(true);
    }

    private void listenersMethod(){
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkBoxes[0]=imageBox.isChecked();
                checkBoxes[1]=pressBox.isChecked();
                checkBoxes[2]=cloudBox.isChecked();
                Objects.requireNonNull(getActivity()).finish();
            }
        });
    }
}
