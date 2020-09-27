package com.codetutor.vitalsapp.dependency;

import com.codetutor.vitalsapp.networking.VitalsAPIConstants;
import com.codetutor.vitalsapp.networking.VitalsAPIProvider;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import okhttp3.logging.HttpLoggingInterceptor;

@Module
@InstallIn(ApplicationComponent.class)
public class NetworkingModule {

    @Provides
    public static VitalsAPIProvider provideVitalsAPIProvider (){
       return  new VitalsAPIProvider(VitalsAPIConstants.BASE_URL, 5000, 5000, HttpLoggingInterceptor.Level.BODY);
    }
}
