package com.studies.se;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class Fragment1 extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate((R.layout.fragment1_layout),container,false );
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        BarChart barChart = (BarChart) getView().findViewById(R.id.barChart);
        ArrayList<BarEntry> orders = new ArrayList<>();
        orders.add(new BarEntry(0, 10));
        orders.add(new BarEntry(1, 4));
        orders.add(new BarEntry(2, 9));
        orders.add(new BarEntry(3, 12));
        orders.add(new BarEntry(4, 7));
        orders.add(new BarEntry(5, 3));
        orders.add(new BarEntry(6, 2));
        orders.add(new BarEntry(7, 15));

        BarDataSet barDataSet = new BarDataSet(orders, "Number of orders in previous weeks");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setValueTextColor(Color.BLACK);
        barDataSet.setValueTextSize(16f);

        BarData barData = new BarData(barDataSet);
        barChart.setFitBars(true);
        barChart.setData(barData);

        barChart.animateY(2000);
    }
}
