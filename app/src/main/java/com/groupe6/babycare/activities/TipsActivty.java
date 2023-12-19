package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.fragments.TipsFragment;
import com.groupe6.babycare.adapters.TipsAdapter;
import com.groupe6.babycare.dtos.tips.TipsDTO;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.TipsApiImpl;

import java.util.List;

public class TipsActivty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tips_activty);


    }

}