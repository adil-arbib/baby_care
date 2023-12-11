package com.groupe6.babycare.listeners;

import android.view.View;

import com.groupe6.babycare.dtos.children.ChildDTO;

public interface SelectChildListener {

    void onSelectChild(ChildDTO child, View viewToSelect, View previousView);

}
