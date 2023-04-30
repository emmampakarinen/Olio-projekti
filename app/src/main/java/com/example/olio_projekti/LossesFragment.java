
package com.example.olio_projekti;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.anychart.AnyChart;
import com.anychart.AnyChartView;
import com.anychart.chart.common.dataentry.DataEntry;
import com.anychart.chart.common.dataentry.ValueDataEntry;
import com.anychart.charts.Cartesian;
import com.anychart.core.cartesian.series.Column;

import java.util.ArrayList;
import java.util.List;


public class LossesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // reference: https://github.com/AnyChart/AnyChart-Android/blob/master/sample/src/main/java/com/anychart/sample/charts/ColumnChartActivity.java

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_losses, container, false);
        AnyChartView anyChartView = view.findViewById(R.id.losses_chart_view);
        anyChartView.setProgressBar(view.findViewById(R.id.progress_bar));
        Cartesian cartesian = AnyChart.column();

        List<DataEntry> data = new ArrayList<>();
        if (!Storage.getInstance().getAllLutemons().isEmpty()) {
            for (Lutemon l: Storage.getInstance().getAllLutemons()) {
                if (l.wins != 0 || l.losses != 0) { // adding only Lutemons who have been in a battle
                    data.add(new ValueDataEntry(l.getName(), l.getLosses()));
                }
            }
            Column column = cartesian.column(data);
            cartesian.title("Losses per Lutemon");
            column.color(String.valueOf(R.color.purple_500));
            cartesian.yScale().ticks().allowFractional(false);

            anyChartView.setChart(cartesian);
        }
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}