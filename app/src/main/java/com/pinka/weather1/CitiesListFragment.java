package com.pinka.weather1;

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
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class CitiesListFragment extends Fragment {
    private ListView listView;
    private TextView emptyTextView;

    int currentPosition = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViews(view);
        initList();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

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
    }

    private void initList() {
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(), R.array.Cities,
                android.R.layout.simple_list_item_activated_1);
        listView.setAdapter(adapter);

        listView.setEmptyView(emptyTextView);

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

            DetailedFragment detail = (DetailedFragment)
                    getFragmentManager().findFragmentById(R.id.data_of_temp);

            if (detail == null || detail.getIndex() != currentPosition) {
                detail = DetailedFragment.create(currentPosition);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.data_of_temp, detail);  // замена фрагмента
                ft.commit();
            }
        } else {
            Intent intent = new Intent();
            intent.setClass(getActivity(), DetailedActivity.class);
            intent.putExtra("index", currentPosition);
            startActivity(intent);
        }
    }
}
