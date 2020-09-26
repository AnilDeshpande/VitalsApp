package com.codetutor.vitalsapp.data;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;

public interface IRepository {

    String TYPE_WEIGHT = "Weight";
    String TYPE_BLOOD_PRESSURE = "Blood pressure";
    String TYPE_SLEEP = "Sleep";
    String TYPE_BLOOD_SUGAR = "Blood Sugar";

    MutableLiveData<VitalsInfo> getVitalsInfo();
    MutableLiveData<Vital> getVitalsForSelected(String selectedType);
}
