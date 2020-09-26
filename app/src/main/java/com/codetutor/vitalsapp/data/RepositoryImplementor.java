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
    public MutableLiveData<VitalsInfo> getVitalsInfo() {
        MutableLiveData<VitalsInfo> vitalsInfo = new MutableLiveData<VitalsInfo>();
        vitalsInfo.setValue(this.vitalsInfo);
        return vitalsInfo;
    }

    public MutableLiveData<Vital> getVitalsForSelected (String type){
        Vital specificVital = null;
        for(Vital vital: this.vitalsInfo.getVitals()){
            if(vital.getType().equals(type)){
                specificVital = vital;
            }
        }

        MutableLiveData<Vital> vitals = new MutableLiveData<Vital>();
        vitals.setValue(specificVital);
        return vitals;
    }
}
