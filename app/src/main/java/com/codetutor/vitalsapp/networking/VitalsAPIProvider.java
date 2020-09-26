package com.codetutor.vitalsapp.networking;

import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class VitalsAPIProvider {
    private static VitalsAPIProvider apiServiceProvider;

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpLoggingInterceptor loggingInterceptor;
    VitalAPIInterface apiInterface;

    private VitalsAPIProvider(String baseUrl,
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

    public static VitalsAPIProvider getApiServiceProvider(String baseUrl,
                                                          long readTimeout,
                                                          long connectTimeout,
                                                          HttpLoggingInterceptor.Level logLevel){
        if(apiServiceProvider==null){
            apiServiceProvider = new VitalsAPIProvider(baseUrl, readTimeout, connectTimeout, logLevel);
        }
        return apiServiceProvider;
    }

    public Call<VitalsInfo> getVitalsInfo(){
        return apiInterface.getVitalsInfo();
    }
}
