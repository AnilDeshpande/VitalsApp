package com.codetutor.vitalsapp.networking;

import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;
import javax.inject.Singleton;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VitalsAPIProvider {


    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    VitalAPIInterface apiInterface;

    @Inject
    public VitalsAPIProvider(String baseUrl,
                              long readTimeout,
                              long connectTimeout,
                              HttpLoggingInterceptor.Level logLevel){

        loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(logLevel);

        okHttpClient = new OkHttpClient.Builder()
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .addNetworkInterceptor(loggingInterceptor)
                .build();

        retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();

        apiInterface = retrofit.create(VitalAPIInterface.class);
    }

    public Call<VitalsInfo> getVitalsInfo(){
        return apiInterface.getVitalsInfo();
    }
}
