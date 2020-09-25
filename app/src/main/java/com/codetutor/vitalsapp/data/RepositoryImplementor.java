    package com.codetutor.vitalsapp.data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

    public class RepositoryImplementor implements IRepository{

    private static final String TAG = RepositoryImplementor.class.getSimpleName();

    private Context context;
    private VitalsInfo vitalsInfo;

    private static IRepository instance;

    public static IRepository getInstance(Context context) {
        if(instance==null){
            instance = new RepositoryImplementor(context);
        }
        return instance;
    }

    private RepositoryImplementor(Context context) {
        this.context = context;
        String jsonString = null;
        try {
            InputStream is = context.getAssets().open("vitals_mock_data.json");

            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            jsonString = new String(buffer, "UTF-8");
            //Log.i(TAG,jsonString);
        } catch (IOException e) {
            e.printStackTrace();
        }
        vitalsInfo = new Gson().fromJson(jsonString, VitalsInfo.class);
    }

    @Override
    public LiveData<Vital> getWeightVitals() {
        MutableLiveData<Vital> weightVitals = new MutableLiveData<Vital>();
        weightVitals.setValue(getVitals(IRepository.TYPE_WEIGHT));
        return weightVitals;
    }

    @Override
    public LiveData<Vital> getBloodPressureVitals() {

        MutableLiveData<Vital> bloodPressureVitals = new MutableLiveData<Vital>();
        bloodPressureVitals.setValue(getVitals(IRepository.TYPE_BLOOD_PRESSURE));
        return bloodPressureVitals;
    }

    @Override
    public LiveData<Vital> getSleepVitals() {
        MutableLiveData<Vital> sleepVitals = new MutableLiveData<Vital>();
        sleepVitals.setValue(getVitals(IRepository.TYPE_SLEEP));
        return sleepVitals;
    }

    @Override
    public LiveData<Vital> getBloodSugarVitals() {
        MutableLiveData<Vital> bloodSugarVitals = new MutableLiveData<Vital>();
        bloodSugarVitals.setValue(getVitals(IRepository.TYPE_BLOOD_SUGAR));
        return bloodSugarVitals;
    }

        private Vital getVitals(String type){
            List<Vital> allVitals = vitalsInfo.getVitals();
            Vital specficVital = null;
            for(Vital vital: allVitals){
                if(vital.getType().equals(IRepository.TYPE_WEIGHT)){
                    specficVital = vital;
                }
            }
            return specficVital;
        }
}
