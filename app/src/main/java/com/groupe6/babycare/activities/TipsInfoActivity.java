package com.groupe6.babycare.activities;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityTipsInfoBinding;
import com.groupe6.babycare.dtos.tips.TipsDTO;

public class TipsInfoActivity extends AppCompatActivity {

    private ActivityTipsInfoBinding binding;

    private TipsDTO tip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTipsInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tip = extras.getParcelable(GlobalKeys.TIPS_KEY);
        }
        displayData();

        binding.icBack.setActivity(this);
    }

    private void displayData() {
        binding.inputCategory.setText(tip.getCategory());
        binding.inputDescription.setText(tip.getDescription());
        binding.inputTarget.setText(tip.getTarget());
    }

}