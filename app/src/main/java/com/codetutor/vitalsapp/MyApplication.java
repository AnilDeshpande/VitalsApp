package com.codetutor.vitalsapp;

import android.app.Application;
import android.content.Context;

import com.codetutor.vitalsapp.data.IRepository;
import com.codetutor.vitalsapp.data.RepositoryImplementor;
import com.codetutor.vitalsapp.data.SimpleCustomCache;
import com.codetutor.vitalsapp.networking.VitalsAPIConstants;
import com.codetutor.vitalsapp.networking.VitalsAPIProvider;

import okhttp3.logging.HttpLoggingInterceptor;

public class MyApplication extends Application {

    private Context context;

    protected VitalsAPIProvider vitalsAPIProvider;
    protected SimpleCustomCache simpleCustomCache;
    protected IRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        this.context = this;
        vitalsAPIProvider = new VitalsAPIProvider (VitalsAPIConstants.BASE_URL, 5000, 5000, HttpLoggingInterceptor.Level.BODY);
        simpleCustomCache = new SimpleCustomCache(this.context);
        repository = new RepositoryImplementor(this.context,vitalsAPIProvider, simpleCustomCache);
    }

    public IRepository getRepository(){
        return this.repository;
    }
}
