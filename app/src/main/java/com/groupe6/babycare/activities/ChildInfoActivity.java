package com.groupe6.babycare.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityChildInfoBinding;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.utils.TimeUtils;

public class ChildInfoActivity extends AppCompatActivity {

    private ChildDTO child;

    private ActivityChildInfoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityChildInfoBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            child = extras.getParcelable(GlobalKeys.CHILD_KEY);
            System.out.println(child);
        }

        binding.icBack.setActivity(this);

        displayData();

    }

    private void displayData() {
        binding.inputName.setText(child.getFirstName());
        binding.inputDate.setText(TimeUtils.formatFromSqlDateToRegular(child.getBirthDate()));
        binding.inputWeight.setText(child.getWeight()+"");
        binding.inputHeight.setText(child.getHeight()+"");
        boolean isBoy = child.getGender().toLowerCase().equals("male");
        System.out.println(isBoy);
        binding.radioBoy.setChecked(isBoy);
        binding.radioGirl.setChecked(!isBoy);
    }

    public void cancelChanges() {
        displayData();
    }


}