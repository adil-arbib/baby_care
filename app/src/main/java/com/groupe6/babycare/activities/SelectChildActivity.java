package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.adapters.ChildAdapter;
import com.groupe6.babycare.databinding.ActivitySelectChildBinding;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnChildClickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ParentApiImpl;
import com.groupe6.babycare.utils.SharedPreferencesUtils;
import com.groupe6.babycare.utils.TokenManager;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SelectChildActivity extends AppCompatActivity implements OnChildClickListener {

    private ActivitySelectChildBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivitySelectChildBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);


        // static data
        getChildren();
    }


    public void getChildren() {
        binding.progressBar.setVisibility(View.VISIBLE);
        SharedPreferencesUtils sharedPreferencesUtils = SharedPreferencesUtils.getInstance(getApplicationContext());
        ParentApiImpl parentApi = ParentApiImpl.getInstance(getApplicationContext());
        parentApi.getChildren(Long.parseLong(sharedPreferencesUtils.getValue("parentId")), new ResponseListener<List<ChildDTO>>() {
            @Override
            public void onSuccess(List<ChildDTO> response) {
                ChildAdapter childAdapter = new ChildAdapter(SelectChildActivity.this, response, SelectChildActivity.this);
                binding.grid.setAdapter(childAdapter);
                binding.progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onError(ErrorDTO error) {

            }
        });
    }

    @Override
    public void onChildClick(ChildDTO child) {
        GlobalObjectsHolder.getInstance().setCurrentChild(child);
        startActivity(new Intent(SelectChildActivity.this, MainActivity.class));
        finish();
    }
}