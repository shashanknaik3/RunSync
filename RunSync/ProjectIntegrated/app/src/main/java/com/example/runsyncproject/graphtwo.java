package com.example.runsyncproject;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class graphtwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graphtwo);

        BarChart barChart = findViewById(R.id.barCharttwo);

        RunsDatabaseHandler runsDatabaseHandler = new RunsDatabaseHandler(this);
        Cursor cursor = runsDatabaseHandler.getAllRunsForInnings(2);

        ArrayList<BarEntry> entries = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                int runs = cursor.getInt(cursor.getColumnIndex("runs"));
                int over = cursor.getInt(cursor.getColumnIndex("over"));

                entries.add(new BarEntry(over, runs));
            } while (cursor.moveToNext());
        }

        cursor.close();

        BarDataSet dataSet = new BarDataSet(entries, "Runs per Over");
        dataSet.setColor(Color.BLUE);
        dataSet.setValueTextSize(12f);

        List<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData data = new BarData(dataSets);
        barChart.setData(data);


        barChart.getDescription().setEnabled(false);
        barChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        barChart.getAxisRight().setEnabled(false);
        barChart.setFitBars(true);

        XAxis xAxis = barChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return String.valueOf((int) value);
            }
        });

        barChart.invalidate();
    }
}
