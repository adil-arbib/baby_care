package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.adapters.ChildAdapter;
import com.groupe6.babycare.databinding.ActivitySelectChildBinding;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.holders.GlobalObjectsHolder;
import com.groupe6.babycare.listeners.OnChildClickListener;

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
        ChildAdapter childAdapter = new ChildAdapter(this, getStaticData(), this);
        binding.grid.setAdapter(childAdapter);
    }


    public List<ChildDTO> getStaticData() {
        return new ArrayList<>(Arrays.asList(
              new ChildDTO("Adil","boy")  ,
              new ChildDTO("Safae","girl")  ,
              new ChildDTO("Kaoutar","girl")  ,
              new ChildDTO("Mohamed","boy")
        ));
    }

    @Override
    public void onChildClick(ChildDTO child) {
        GlobalObjectsHolder.getInstance().setCurrentChild(child);
        startActivity(new Intent(SelectChildActivity.this, MainActivity.class));
        finish();
    }
}