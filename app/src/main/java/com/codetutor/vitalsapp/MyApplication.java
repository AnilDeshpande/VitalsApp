package com.codetutor.vitalsapp;

import android.app.Application;
import android.content.Context;

import com.codetutor.vitalsapp.data.SimpleCustomCache;
import com.codetutor.vitalsapp.networking.VitalsAPIConstants;
import com.codetutor.vitalsapp.networking.VitalsAPIProvider;

import okhttp3.logging.HttpLoggingInterceptor;

public class MyApplication extends Application {

    private static Context context;

    protected static VitalsAPIProvider vitalsAPIProvider;
    protected static SimpleCustomCache simpleCustomCache;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        vitalsAPIProvider = VitalsAPIProvider.getApiServiceProvider(VitalsAPIConstants.BASE_URL, 5000, 5000, HttpLoggingInterceptor.Level.BODY);
        simpleCustomCache = new SimpleCustomCache(this.context);
    }

    public static Context getContext(){
        return context;
    }

    public static VitalsAPIProvider getVitalsAPIProvider(){
        return vitalsAPIProvider;
    }

    public static SimpleCustomCache getSimpleCustomCache(){
        return simpleCustomCache;
    }
}
