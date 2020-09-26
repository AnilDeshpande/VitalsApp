package com.codetutor.vitalsapp.view;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.codetutor.vitalsapp.R;
import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.util.Utils;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.DataSet;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class VitalsInfoFragment extends Fragment {

    private static final String TAG = VitalsInfoFragment.class.getSimpleName();

    private View rootView;

    private Vital vital;

    private LineChart lineChart;
    private LineDataSet lineDataSet;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_vitals_info, container, false);
        initUI();
        return rootView;
    }

    private void initUI(){
        Bundle bundle = getArguments();
        vital = (Vital) bundle.getSerializable("Vitals");
        lineChart = (LineChart)rootView.findViewById(R.id.lineChart);
    }

    private void preparedDataForChart(){
        List<Float> values = Utils.getDataInFloats(vital.getValues(),vital.getType());

        ArrayList<Entry> yValues = new ArrayList<>();
        for(int i=0;i<values.size();i++){
            yValues.add(new Entry(i,values.get(i)));
        }

        LineDataSet lineDataSet1 = new LineDataSet(yValues, vital.getUnit() == null || vital.getUnit().equals("")? "Unit Unavailable".toUpperCase():vital.getUnit().toUpperCase());
        lineDataSet1.setFillAlpha(110);
        lineDataSet1.setCircleColor(Color.RED);
        lineDataSet1.setValueTextColor(Color.BLUE);
        lineDataSet1.setCircleRadius(4f);
        lineDataSet1.setColor(Color.RED);
        lineDataSet1.setLineWidth(2.0f);
        lineDataSet1.setValueTextSize(12f);

        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataSet1);

        LineData lineData = new LineData(dataSets);
        lineChart.setData(lineData);

        lineChart.getAxisRight().setEnabled(false);

        XAxis xAxis = lineChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setTextColor(Color.MAGENTA);

        xAxis.setValueFormatter(new CustomDateFormatter(vital.getDates()));
        xAxis.setGranularity(1f);

    }

    @Override
    public void onResume() {
        super.onResume();
        String unit = vital.getUnit() == null || vital.getUnit().equals("")? "Unit Unavailable":vital.getUnit();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(vital.getType()+" ("+unit+")");
        preparedDataForChart();
    }

    public class CustomDateFormatter extends ValueFormatter {

        List<String> dates;

        public CustomDateFormatter(List<String> dates){
            this.dates = dates;
        }

        @Override
        public String getAxisLabel(float value, AxisBase axis) {
            String currentDate = dates.get((int)value);
            Date date = Utils.getStringDateInUtils(currentDate);
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM", Locale.ENGLISH);
            return sdf.format(date);
        }
    }
}
