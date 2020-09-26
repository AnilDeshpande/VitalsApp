package com.codetutor.vitalsapp.view;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.codetutor.vitalsapp.R;
import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.codetutor.vitalsapp.data.IRepository;

public class MainFragment extends Fragment implements View.OnClickListener {

    private VitalSelectedListener listener;

    private View rootView;

    private TextView textViewName, textViewDateOfBirth, textViewCity;

    private Button buttonBloodPressure, buttonSleep, buttonBloodSugar, buttonWeight;

    private VitalsInfo vitalsInfo;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_main, container, false);
        initView();
        return rootView;
    }

    private void initView(){

        textViewName = (TextView) rootView.findViewById(R.id.textViewName);
        textViewDateOfBirth = (TextView) rootView.findViewById(R.id.textViewDateOfBirth);
        textViewCity = (TextView) rootView.findViewById(R.id.textViewCity);

        buttonBloodPressure = (Button) rootView.findViewById(R.id.buttonBloodPressure);
        buttonSleep = (Button) rootView.findViewById(R.id.buttonSleep);
        buttonBloodSugar = (Button) rootView.findViewById(R.id.buttonBloodSugar);
        buttonWeight = (Button) rootView.findViewById(R.id.buttonWeight);

        Bundle bundle = getArguments();
        vitalsInfo = (VitalsInfo) bundle.getSerializable("VitalsInfo");

        textViewName.setText(vitalsInfo.getName());
        textViewDateOfBirth.setText(vitalsInfo.getDob());
        textViewCity.setText(vitalsInfo.getCity());

        buttonBloodPressure.setText(IRepository.TYPE_BLOOD_PRESSURE);
        buttonSleep.setText(IRepository.TYPE_SLEEP);
        buttonBloodSugar.setText(IRepository.TYPE_BLOOD_SUGAR);
        buttonWeight.setText(IRepository.TYPE_WEIGHT);

        buttonBloodPressure.setOnClickListener(this);
        buttonSleep.setOnClickListener(this);
        buttonBloodSugar.setOnClickListener(this);
        buttonWeight.setOnClickListener(this);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.listener = (VitalSelectedListener) getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("VitalsApp");

    }

    @Override
    public void onClick(View view) {
        String selectedType = null;
        switch (view.getId()){
            case R.id.buttonBloodPressure: selectedType = IRepository.TYPE_BLOOD_PRESSURE; break;
            case R.id.buttonSleep: selectedType = IRepository.TYPE_SLEEP; break;
            case R.id.buttonBloodSugar: selectedType = IRepository.TYPE_BLOOD_SUGAR; break;
            case R.id.buttonWeight: selectedType = IRepository.TYPE_WEIGHT; break;
        }
        if(listener!=null){
            listener.onVitalSelected(selectedType);
        }
    }
}
