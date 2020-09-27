package com.codetutor.vitalsapp.data;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.InputStream;

public class SimpleCustomCache {

    private static final String TAG = SimpleCustomCache.class.getSimpleName();

    Context context;
    SharedPreferences sharedPreferences;

    public SimpleCustomCache(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences("simple_preference.xml", Context.MODE_PRIVATE);

    }

    public void saveVitalsInfo(VitalsInfo vitalsInfo){
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String string = new Gson().toJson(vitalsInfo).toString();
        editor.putString("vitalsInfo",string);
        editor.commit();
    }

    public VitalsInfo fetchVitalsInfo(){
        String jsonString = sharedPreferences.getString("vitalsInfo", null);
        if(jsonString!=null){
            return new Gson().fromJson(jsonString, VitalsInfo.class);
        }else {
            return readFromMock();
        }
    }

    private VitalsInfo readFromMock(){
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
}
