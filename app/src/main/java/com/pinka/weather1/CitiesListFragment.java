package com.pinka.weather1;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.Objects;


public class CitiesListFragment extends Fragment {
    private ListView listView;

    int currentPosition = 0;

    private  Bundle savedInstanceState=null;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initList(getContext());
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.savedInstanceState=savedInstanceState;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (savedInstanceState != null) {
            currentPosition = savedInstanceState.getInt("CurrentCity", 0);
        }
        if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
            listView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showCoatOfArms();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("CurrentCity", currentPosition);
        super.onSaveInstanceState(outState);
    }

    private void initViews(View view) {
        listView = view.findViewById(R.id.cities_list_view);
        ImageView settingsBtn = view.findViewById(R.id.btnSettings);
        settingsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(Objects.requireNonNull(getActivity()), SettingsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initList(Context context) {
        CityData[]data=new CityData[]{
             new CityData(getResources().getStringArray(R.array.Cities)[0]),
                new CityData(getResources().getStringArray(R.array.Cities)[1]),
                new CityData(getResources().getStringArray(R.array.Cities)[2]),
                new CityData(getResources().getStringArray(R.array.Cities)[3]),
                new CityData(getResources().getStringArray(R.array.Cities)[4])
        };
        CityListAdapter adapter=new CityListAdapter(context,data);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                currentPosition = position;
                showCoatOfArms();
            }
        });
    }

    private void showCoatOfArms() {
        if (getResources().getConfiguration().orientation==Configuration.ORIENTATION_LANDSCAPE) {
            listView.setItemChecked(currentPosition, true);
            DetailedFragment detail = (DetailedFragment) Objects.requireNonNull(getFragmentManager()).
                    findFragmentById(R.id.data_of_temp);
            if (detail == null || detail.getIndex() != currentPosition) {
                detail = DetailedFragment.create(currentPosition);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.data_of_temp, detail);
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(Objects.requireNonNull(getActivity()), DetailedActivity.class);
            intent.putExtra("index", currentPosition);
            startActivity(intent);
        }
    }
}
