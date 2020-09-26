package com.codetutor.vitalsapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.codetutor.vitalsapp.MyApplication;
import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.codetutor.vitalsapp.data.IRepository;
import com.codetutor.vitalsapp.data.RepositoryImplementor;

class VitalsInfoViewModel {

    IRepository repository;

    MutableLiveData<VitalsInfo> vitalsInfoLiveData;

    MutableLiveData<String> selectedVitalLiveData;

    public VitalsInfoViewModel(){
        super();
        repository = RepositoryImplementor.getInstance(MyApplication.getContext());
        vitalsInfoLiveData = repository.getVitalsInfo();
        selectedVitalLiveData.setValue(null);
    }

    public void onVitalsSelected(String vitals){
        selectedVitalLiveData.setValue(vitals);
    }

    public MutableLiveData<String> selectedVitalLiveData(){
        return selectedVitalLiveData;
    }

    public LiveData<VitalsInfo> getVitalsInfoLiveData(){
        return vitalsInfoLiveData;
    }

    public LiveData<Vital> getVitals(){
        return repository.getVitalsForSelected(selectedVitalLiveData.getValue());
    }
}
