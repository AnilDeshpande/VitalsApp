package com.codetutor.vitalsapp;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;


import android.os.Bundle;
import android.util.Log;

import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;
import com.codetutor.vitalsapp.view.MainFragment;
import com.codetutor.vitalsapp.view.VitalSelectedListener;
import com.codetutor.vitalsapp.view.VitalsInfoFragment;
import com.codetutor.vitalsapp.viewmodel.VitalsInfoViewModel;

public class MainActivity extends AppCompatActivity implements VitalSelectedListener {

    private static final String TAG = MainActivity.class.getSimpleName();

    LiveData<VitalsInfo> vitalsInfoLiveData;
    LiveData<Vital> vitalLiveData;

    private VitalsInfoViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = ViewModelProviders.of(this).get(VitalsInfoViewModel.class);
        vitalsInfoLiveData = viewModel.getVitalsInfoLiveData();

        vitalsInfoLiveData.observe(this, new Observer<VitalsInfo>() {
            @Override
            public void onChanged(VitalsInfo vitalsInfo) {
                Log.i(TAG,"Selected Vitals modified: "+vitalsInfo.getName());
            }
        });

        vitalLiveData = viewModel.getVitals();
        vitalLiveData.observe(this, new Observer<Vital>() {
            @Override
            public void onChanged(Vital vital) {
                if(vital!=null){
                    Log.i(TAG,"Selected Vitals modified: "+vital.getType());
                }else {
                    Log.i(TAG,"Vital Change Listener: "+vital);
                }
            }
        });

        viewModel.selectedVitalLiveData().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                Log.i(TAG,"New Vital selected : "+s);
            }
        });

        if(savedInstanceState==null){
            loadMainFragment();
        }
    }

    private void loadMainFragment(){
        MainFragment mainFragment = new MainFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("VitalsInfo", vitalsInfoLiveData.getValue());
        mainFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.fragmentContainer,mainFragment).addToBackStack(null).commit();
    }

    private void loadVitalsInfoFragment(){
        VitalsInfoFragment vitalsInfoFragment = new VitalsInfoFragment();
        Bundle bundle = new Bundle();
        bundle.putSerializable("Vitals", vitalLiveData.getValue());
        vitalsInfoFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,vitalsInfoFragment).addToBackStack(null).commit();
    }

    @Override
    public void onVitalSelected(String type) {
        viewModel.onVitalsSelected(type);
        loadVitalsInfoFragment();
    }


}