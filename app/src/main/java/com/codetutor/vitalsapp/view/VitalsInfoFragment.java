package com.codetutor.vitalsapp.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.codetutor.vitalsapp.R;
import com.codetutor.vitalsapp.bean.Vital;
import com.codetutor.vitalsapp.bean.VitalsInfo;

public class VitalsInfoFragment extends Fragment {

    private static final String TAG = VitalsInfoFragment.class.getSimpleName();

    private View rootView;

    private Vital vital;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_vitals_info, container, false);
        initUI();
        return rootView;
    }

    private void initUI(){
        Bundle bundle = getArguments();
        vital = (Vital) bundle.getSerializable("Vitals");
    }

    @Override
    public void onResume() {
        super.onResume();
        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(vital.getType()+"("+vital.getUnit()+")");

    }
}
