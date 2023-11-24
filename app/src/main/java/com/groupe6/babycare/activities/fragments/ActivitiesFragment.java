package com.groupe6.babycare.activities.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.groupe6.babycare.R;
import com.groupe6.babycare.activities.MainActivity;


public class ActivitiesFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        getActivity().setTitle(R.string.activities);
        return inflater.inflate(R.layout.fragment_activities, container, false);
    }


}