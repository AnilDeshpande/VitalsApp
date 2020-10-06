package com.codetutor.vitalsapp.dependency;

import android.content.Context;

import com.codetutor.vitalsapp.data.IRepository;
import com.codetutor.vitalsapp.data.RepositoryImplementor;
import com.codetutor.vitalsapp.data.SimpleCustomCache;
import com.codetutor.vitalsapp.networking.VitalsAPIConstants;
import com.codetutor.vitalsapp.networking.VitalsAPIProvider;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;
import dagger.hilt.android.qualifiers.ApplicationContext;
import okhttp3.logging.HttpLoggingInterceptor;

@InstallIn(ApplicationComponent.class)
@Module
public class CommonModulesProvider {

    @Provides
    public static VitalsAPIProvider provideVitalsAPIProvider (){
        return  new VitalsAPIProvider(VitalsAPIConstants.BASE_URL, 5000, 5000, HttpLoggingInterceptor.Level.BODY);
    }

    @Provides
    public static IRepository providerRepository(@ApplicationContext Context context, VitalsAPIProvider provider, SimpleCustomCache cache){
        return new RepositoryImplementor(context, provider, cache);
    }
}
