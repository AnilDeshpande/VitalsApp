package com.codetutor.vitalsapp.networking;

import com.codetutor.vitalsapp.bean.VitalsInfo;

import retrofit2.Call;
import retrofit2.http.GET;

public interface VitalAPIInterface {
    @GET(VitalsAPIConstants.vitals)
    Call<VitalsInfo> getVitalsInfo();
}
