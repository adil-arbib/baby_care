package com.groupe6.babycare.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.groupe6.babycare.activities.dialogs.DatePickerDialog;
import com.groupe6.babycare.activities.dialogs.DeleteDialog;
import com.groupe6.babycare.activities.dialogs.SimpleDeleteDialog;
import com.groupe6.babycare.consts.GlobalKeys;
import com.groupe6.babycare.databinding.ActivityChildInfoBinding;
import com.groupe6.babycare.dtos.children.ChildDTO;
import com.groupe6.babycare.dtos.error.ErrorDTO;
import com.groupe6.babycare.listeners.OnChildDeleteListener;
import com.groupe6.babycare.listeners.OnDatePickListener;
import com.groupe6.babycare.listeners.ResponseListener;
import com.groupe6.babycare.repositories.implementations.ChildApiImpl;
import com.groupe6.babycare.utils.InputsUtils;
import com.groupe6.babycare.utils.TimeUtils;

public class ChildInfoActivity extends AppCompatActivity implements OnDatePickListener, OnChildDeleteListener {

    private ChildDTO child;

    private String selectedDate;

    private ActivityChildInfoBinding binding;
    @RequiresApi(api = Build.VERSION_CODES.O)
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

        binding.btnSave.setOnClickListener(v -> {
            saveDate();
        });

        binding.inputDate.setOnClickListener(v -> {

            DatePickerDialog datePickerDialog = new DatePickerDialog(this, this);
            datePickerDialog.init();
            datePickerDialog.open();

        });

        displayData();

        binding.icDelete.setOnClickListener(v -> deleteChild());

    }

    private void deleteChild() {
        SimpleDeleteDialog simpleDeleteDialog = new SimpleDeleteDialog(this, child.getFirstName(), this);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.copyFrom(simpleDeleteDialog.getWindow().getAttributes());
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT ;
        simpleDeleteDialog.show();
        simpleDeleteDialog.getWindow().setAttributes(lp);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void saveDate() {
        if(!InputsUtils.validateInputs(binding.inputName, binding.inputDate, binding.inputHeight, binding.inputWeight))
            return;
        ChildDTO childDTO = new ChildDTO();
        childDTO.setId(child.getId());
        String gender = binding.radioBoy.isSelected() ? "MALE" : "FEMALE";
        childDTO.setGender(gender);
        childDTO.setHeight(Double.parseDouble(binding.inputHeight.getText().toString()));
        childDTO.setWeight(Double.parseDouble(binding.inputWeight.getText().toString()));
        childDTO.setFirstName(binding.inputName.getText().toString());
        childDTO.setLastName(child.getLastName());
        childDTO.setBirthDate(selectedDate != null ? selectedDate : child.getBirthDate());
        ChildApiImpl childApi = ChildApiImpl.getInstance(getApplicationContext());
        binding.progressBar.setVisibility(View.VISIBLE);
        childApi.updateChild(childDTO, child.getId(), new ResponseListener<ChildDTO>() {
            @Override
            public void onSuccess(ChildDTO response) {
                Toast.makeText(ChildInfoActivity.this, "Updates Successfully !!", Toast.LENGTH_SHORT).show();
                binding.progressBar.setVisibility(View.GONE);
                child = response;
                displayData();
            }

            @Override
            public void onError(ErrorDTO error) {

            }
        });

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


    @Override
    public void onDatePick(int year, int month, int day) {
        binding.inputDate.setText(day+"/"+month+"/"+year);
        selectedDate = year + "-" + month + "-" + day;
    }

    @Override
    public void onDelete(boolean confirmed) {
        if(confirmed) {
            ChildApiImpl childApi = ChildApiImpl.getInstance(this);
            childApi.deleteChild(child.getId(), new ResponseListener<Void>() {
                @Override
                public void onSuccess(Void response) {
                    Toast.makeText(ChildInfoActivity.this, "Child deleted successfully !!", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onError(ErrorDTO error) {
                    Log.e("ERROR", error.getMessage());
                    Toast.makeText(ChildInfoActivity.this, "An error was occurred !!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}