package com.codetutor.vitalsapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.codetutor.vitalsapp.data.IRepository;

public class VitalsInfoModelFactory implements ViewModelProvider.Factory {

    IRepository repository;

    public VitalsInfoModelFactory(IRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VitalsInfoViewModel(repository);
    }
}
