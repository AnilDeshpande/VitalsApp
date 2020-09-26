    package com.codetutor.vitalsapp.data;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.codetutor.vitalsapp.MyApplication;
import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.codetutor.vitalsapp.networking.VitalsAPIProvider;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class RepositoryImplementor implements IRepository{

    private static final String TAG = RepositoryImplementor.class.getSimpleName();

    private Context context;


    private static IRepository instance;
    private VitalsAPIProvider apiProvider;
    private SimpleCustomCache simpleCustomCache;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<VitalsInfo> vitalsInfoMutableLiveData = new MutableLiveData<VitalsInfo>();

    public static IRepository getInstance(Context context) {
        if(instance==null){
            instance = new RepositoryImplementor(context);
        }
        return instance;
    }

    private RepositoryImplementor(Context context) {
        this.context = context;

        apiProvider = MyApplication.getVitalsAPIProvider();
        simpleCustomCache = MyApplication.getSimpleCustomCache();

        isLoading.postValue(true);
        apiProvider.getVitalsInfo().enqueue(new Callback<VitalsInfo>() {
            @Override
            public void onResponse(Call<VitalsInfo> call, Response<VitalsInfo> response) {
                vitalsInfoMutableLiveData.postValue(response.body());
                simpleCustomCache.saveVitalsInfo(response.body());
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<VitalsInfo> call, Throwable t) {
                if(simpleCustomCache.isVitalsInfoCached()){
                    vitalsInfoMutableLiveData.setValue(simpleCustomCache.fetchVitalsInfo());
                }else {
                    vitalsInfoMutableLiveData.setValue(readFromLocalCache());
                }

                isLoading.postValue(false);
            }
        });
    }

    @Override
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    private VitalsInfo readFromLocalCache(){
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
        return new Gson().fromJson(jsonString, VitalsInfo.class);
    }

    @Override
    public MutableLiveData<VitalsInfo> getVitalsInfoMutableLiveData() {
        return this.vitalsInfoMutableLiveData;
    }

    public MutableLiveData<Vital> getVitalsForSelected (String type){
        Vital specificVital = null;
        for(Vital vital: this.vitalsInfoMutableLiveData.getValue().getVitals()){
            if(vital.getType().equals(type)){
                specificVital = vital;
            }
        }

        MutableLiveData<Vital> vitals = new MutableLiveData<Vital>();
        vitals.setValue(specificVital);
        return vitals;
    }
}
