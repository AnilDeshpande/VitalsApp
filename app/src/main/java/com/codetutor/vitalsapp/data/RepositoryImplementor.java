    package com.codetutor.vitalsapp.data;

    import android.content.Context;

    import androidx.lifecycle.MutableLiveData;

    import com.codetutor.vitalsapp.bean.Vital;
    import com.codetutor.vitalsapp.bean.VitalsInfo;
    import com.codetutor.vitalsapp.networking.VitalsAPIProvider;

    import javax.inject.Inject;

    import dagger.hilt.android.qualifiers.ApplicationContext;
    import retrofit2.Call;
    import retrofit2.Callback;
    import retrofit2.Response;

    public class RepositoryImplementor implements IRepository {

    private static final String TAG = RepositoryImplementor.class.getSimpleName();

    @Inject Context context;
    @Inject VitalsAPIProvider apiProvider;
    @Inject SimpleCustomCache simpleCustomCache;

    private MutableLiveData<Boolean> isLoading = new MutableLiveData<>();
    private MutableLiveData<VitalsInfo> vitalsInfoMutableLiveData = new MutableLiveData<VitalsInfo>();

    @Inject
    public RepositoryImplementor(@ApplicationContext Context context, VitalsAPIProvider provider, SimpleCustomCache cache) {

        this.context = context;
        this.apiProvider = provider;
        this.simpleCustomCache = cache;

        isLoading.postValue(true);
        apiProvider.getVitalsInfo().enqueue(new Callback<VitalsInfo>() {
            @Override
            public void onResponse(Call<VitalsInfo> call, Response<VitalsInfo> response) {
                vitalsInfoMutableLiveData.postValue(response.body());
                simpleCustomCache.saveVitalsInfo(response.body());
                isLoading.postValue(false);
            }

            @Override
            public void onFailure(Call<VitalsInfo> call, Throwable t) {
                vitalsInfoMutableLiveData.setValue(simpleCustomCache.fetchVitalsInfo());
                isLoading.postValue(false);
            }
        });
    }

    @Override
    public MutableLiveData<Boolean> getIsLoading() {
        return isLoading;
    }

    @Override
    public MutableLiveData<VitalsInfo> getVitalsInfoMutableLiveData() {
        return this.vitalsInfoMutableLiveData;
    }

    public MutableLiveData<Vital> getVitalsForSelected (String type){
        Vital specificVital = null;
        for(Vital vital: this.vitalsInfoMutableLiveData.getValue().getVitals()){
            if(vital.getType().equals(type)){
                specificVital = vital;
            }
        }

        MutableLiveData<Vital> vitals = new MutableLiveData<Vital>();
        vitals.setValue(specificVital);
        return vitals;
    }
}
