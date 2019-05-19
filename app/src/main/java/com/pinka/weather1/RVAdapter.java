package com.pinka.weather1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter <RVAdapter.RVViewHolder> {

    private  ArrayList <ForecastData> data;

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        View view= LayoutInflater.from(context).inflate(R.layout.element_layout,viewGroup,false);
        return  new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder rvViewHolder, int i) {
        rvViewHolder.dateTextView.setText(data.get(i).date);
        rvViewHolder.temperatureText.setText(data.get(i).temperature);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    class RVViewHolder extends RecyclerView.ViewHolder{
        TextView dateTextView;
        TextView temperatureText;

        RVViewHolder(@NonNull View itemView){
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View itemView) {
            dateTextView=itemView.findViewById(R.id.textDate);
            temperatureText=itemView.findViewById(R.id.textTemperature);
        }
    }
    RVAdapter (ArrayList <ForecastData> data){
        this.data=data;
    }
}
