package com.pinka.weather1;

import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class DetailedFragment extends Fragment {
    TextView temperText;
    TextView cityName;
    ImageView image;

    public static DetailedFragment create(int index) {
        DetailedFragment f = new DetailedFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public int getIndex() {
        int index = getArguments().getInt("index", 0);
        return index;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setViews();
    }

    private void setViews() {
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            String[] cityNames = getResources().getStringArray(R.array.Cities);
            String currentName=cityNames[getIndex()];
            cityName.setText(currentName);
        }

        String[] tempers=getResources().getStringArray(R.array.Tempers);
        String currentTemper= tempers[getIndex()];
        temperText.setText(currentTemper);

        TypedArray imgs = getResources().obtainTypedArray(R.array.weather_imgs);
        image.setImageResource(imgs.getResourceId(getIndex(), -1));
    }

    private void initViews(View view) {
        temperText=view.findViewById(R.id.temperTextView);
        cityName=view.findViewById(R.id.cityName);
        image=view.findViewById(R.id.weatherImage);
    }
}
