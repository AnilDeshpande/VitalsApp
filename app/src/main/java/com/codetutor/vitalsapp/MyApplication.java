package com.codetutor.vitalsapp;

import android.app.Application;
import android.content.Context;

import com.codetutor.vitalsapp.data.IRepository;
import com.codetutor.vitalsapp.data.RepositoryImplementor;
import com.codetutor.vitalsapp.data.SimpleCustomCache;
import com.codetutor.vitalsapp.networking.VitalsAPIProvider;

import javax.inject.Inject;

import dagger.Module;
import dagger.hilt.android.HiltAndroidApp;
import dagger.hilt.android.qualifiers.ApplicationContext;
import okhttp3.logging.HttpLoggingInterceptor;

@HiltAndroidApp
public class MyApplication extends Application {

    @ApplicationContext
    @Inject
    Context context;

    @Inject VitalsAPIProvider vitalsAPIProvider;
    @Inject SimpleCustomCache simpleCustomCache;

    IRepository repository;

    @Override
    public void onCreate() {
        super.onCreate();
        repository = new RepositoryImplementor(context,vitalsAPIProvider, simpleCustomCache);
    }

    public IRepository getRepository(){
        return this.repository;
    }
}
