package com.codetutor.vitalsapp.data;

import android.content.Context;
import android.content.SharedPreferences;

import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.google.gson.Gson;

public class SimpleCustomCache {

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
            return null;
        }
    }

    public boolean isVitalsInfoCached(){
        return sharedPreferences.contains("vitalsInfo");
    }
}
