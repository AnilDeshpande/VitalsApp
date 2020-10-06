package com.codetutor.vitalsapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.codetutor.vitalsapp.data.IRepository;

public class VitalsInfoViewModelFactory implements ViewModelProvider.Factory {

    IRepository repository;

    public VitalsInfoViewModelFactory(IRepository repository){
        this.repository = repository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new VitalsInfoViewModel(repository);
    }
}
