package com.groupe6.babycare.activities.fragments;

import androidx.fragment.app.Fragment;

import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;
import com.groupe6.babycare.listeners.OnDeleteConfirmationListener;
import com.groupe6.babycare.listeners.OnItemClickListener;
import com.groupe6.babycare.listeners.SwipeListener;

public class HealthCareInfo  extends Fragment implements OnItemClickListener<HealthCareDTO>, SwipeListener, OnDeleteConfirmationListener<HealthCareDTO> {
    @Override
    public void onConfirm(int itemPosition) {

    }

    @Override
    public void onClick(HealthCareDTO item) {

    }

    @Override
    public void onItemSwiped(int position) {

    }
}
