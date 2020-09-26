package com.codetutor.vitalsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.codetutor.vitalsapp.MyApplication;
import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.codetutor.vitalsapp.data.IRepository;
import com.codetutor.vitalsapp.data.RepositoryImplementor;

public class VitalsInfoViewModel extends ViewModel {

    IRepository repository;

    MutableLiveData<VitalsInfo> vitalsInfoLiveData;

    MutableLiveData<String> selectedVitalLiveData;

    MutableLiveData<Vital> selectedVitals;

    public VitalsInfoViewModel(){
        super();
        repository = RepositoryImplementor.getInstance(MyApplication.getContext());
        vitalsInfoLiveData = repository.getVitalsInfo();
        selectedVitalLiveData = new MutableLiveData<>();
        selectedVitals = new MutableLiveData<>();
    }

    public void onVitalsSelected(String vitals){
        selectedVitalLiveData.setValue(vitals);
        selectedVitals.setValue(repository.getVitalsForSelected(selectedVitalLiveData.getValue()).getValue());
    }

    public MutableLiveData<String> selectedVitalLiveData(){
        return selectedVitalLiveData;
    }

    public LiveData<VitalsInfo> getVitalsInfoLiveData(){
        return vitalsInfoLiveData;
    }

    public LiveData<Vital> getVitals(){
        return selectedVitals;
    }
}
