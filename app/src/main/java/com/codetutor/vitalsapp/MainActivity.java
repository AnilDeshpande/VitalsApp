package com.codetutor.vitalsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.codetutor.vitalsapp.data.IRepository;
import com.codetutor.vitalsapp.data.RepositoryImplementor;

public class MainActivity extends AppCompatActivity {

    IRepository iRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iRepository = RepositoryImplementor.getInstance(this);
    }
}