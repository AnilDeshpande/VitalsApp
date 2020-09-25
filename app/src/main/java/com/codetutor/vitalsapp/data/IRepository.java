package com.codetutor.vitalsapp.data;

import androidx.lifecycle.LiveData;

import com.codetutor.vitalsapp.bean.Vital;

public interface IRepository {

    String TYPE_WEIGHT = "Weight";
    String TYPE_BLOOD_PRESSURE = "Blood pressure";
    String TYPE_SLEEP = "Sleep";
    String TYPE_BLOOD_SUGAR = "Blood Sugar";

    LiveData<Vital> getWeightVitals();
    LiveData<Vital> getBloodPressureVitals();
    LiveData<Vital> getSleepVitals();
    LiveData<Vital> getBloodSugarVitals();
}
