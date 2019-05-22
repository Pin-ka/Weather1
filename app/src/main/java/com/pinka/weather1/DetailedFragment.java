package com.pinka.weather1;

import android.annotation.SuppressLint;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;
import java.util.Random;


public class DetailedFragment extends Fragment {
    private TextView temperText, temperName, pressText, cloudText, cityName;
    private ImageView image,btnForRecycler;
    private LinearLayout pressLayout,cloudLayout;
    private RecyclerView recyclerView;
    private ArrayList<ForecastData> data=new ArrayList<>();
    private boolean isAddElement=true;
    private GregorianCalendar gc=new GregorianCalendar();
    private Random random=new Random();
    private RVAdapter adapter;

    public static DetailedFragment create(int index) {
        DetailedFragment f = new DetailedFragment();

        Bundle args = new Bundle();
        args.putInt("index", index);
        f.setArguments(args);
        return f;
    }

    public int getIndex() {
        return Objects.requireNonNull(getArguments()).getInt("index", 0);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detailed,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        setViews();
        getData();
        initListeners();
        LinearLayoutManager layoutManager=new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);
        recyclerView.setLayoutManager(layoutManager);
        adapter=new RVAdapter(data);
        recyclerView.setAdapter(adapter);
    }

    private void getData() {
        for (int i=0;i<3;i++){
            gc.add(Calendar.DATE,1);
            int month=gc.get(Calendar.MONTH);
            int day=gc.get(Calendar.DAY_OF_MONTH);
            int temper=random.nextInt(20);
            String dateTerminate;
            if (month<10){
                dateTerminate=".0";
            }
            else {
                dateTerminate=".";
            }
            data.add(new ForecastData(day+dateTerminate+month,temper+"..."+(temper+random.nextInt(5))));
        }

    }

    private void setViews() {
        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_PORTRAIT) {
            String[] cityNames = getResources().getStringArray(R.array.Cities);
            String currentName=cityNames[getIndex()];
            cityName.setText(currentName);
        }

        if (getResources().getConfiguration().orientation== Configuration.ORIENTATION_LANDSCAPE) {
            temperText.setTextSize(30);
            temperName.setTextSize(30);
        }

        String[] tempers=getResources().getStringArray(R.array.Tempers);
        String currentTemper= tempers[getIndex()];
        temperText.setText(currentTemper);

        @SuppressLint("Recycle") TypedArray images = getResources().obtainTypedArray(R.array.weather_images);
        image.setImageResource(images.getResourceId(getIndex(), -1));

        String[] presses=getResources().getStringArray(R.array.press);
        String currentPress= presses[getIndex()];
        pressText.setText(currentPress);

        String[] clouds=getResources().getStringArray(R.array.cloud);
        String currentCloud= clouds[getIndex()];
        cloudText.setText(currentCloud);

        if(!SettingsFragment.checkBoxes[0]) image.setVisibility(View.GONE);
        if(!SettingsFragment.checkBoxes[1]) pressLayout.setVisibility(View.GONE);
        if(!SettingsFragment.checkBoxes[2]) cloudLayout.setVisibility(View.GONE);
    }

    private void initViews(View view) {
        temperText=view.findViewById(R.id.temperTextView);
        cityName=view.findViewById(R.id.cityName);
        image=view.findViewById(R.id.weatherImage);
        pressText=view.findViewById(R.id.pressTextView);
        cloudText=view.findViewById(R.id.cloudTextView);
        pressLayout=view.findViewById(R.id.pressLayout);
        cloudLayout=view.findViewById(R.id.cloudLayout);
        temperName=view.findViewById(R.id.temperName);
        recyclerView=view.findViewById(R.id.recyclerView);
        btnForRecycler=view.findViewById(R.id.btnForRecycler);
    }

    private void initListeners(){
        btnForRecycler.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAddElement){
                    gc.add(Calendar.DATE,1);
                    int month=gc.get(Calendar.MONTH);
                    int day=gc.get(Calendar.DAY_OF_MONTH);
                    int temper=random.nextInt(20);
                    String dateTerminate;
                    if (month<10){
                        dateTerminate=".0";
                    }
                    else {
                        dateTerminate=".";
                    }
                    data.add(new ForecastData(day+dateTerminate+month,temper+"..."+(temper+random.nextInt(5))));
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() - 1);
                    if(data.size()==7){
                        btnForRecycler.setImageResource(R.drawable.less);
                        isAddElement=false;
                    }
                }else {
                    data.remove(data.size()-1);
                    adapter.notifyDataSetChanged();
                    recyclerView.smoothScrollToPosition(Objects.requireNonNull(recyclerView.getAdapter()).getItemCount() - 1);
                    if(data.size()==1){
                        btnForRecycler.setImageResource(R.drawable.more);
                        isAddElement=true;
                    }
                }
            }
        });
    }
}
