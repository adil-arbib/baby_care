package com.groupe6.babycare.utils;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.lang.reflect.Field;

public class InputsUtils {

    public static boolean validateInputs(TextInputEditText ... inputs) {
        boolean isValid = true;
        for(TextInputEditText input : inputs) {
            if(input.getText().toString().isEmpty()){
                isValid = false;
                input.setError("This field is required !!");
            }
        }
        return isValid;
    }

    public static void setErrorText(TextInputLayout inputLayout, String errorMessage){
        inputLayout.setError(errorMessage);
    }


    public static  <T> void displayData(T object, TextInputEditText... inputs) {
        Class<?> clazz = object.getClass();

        for (Field field : clazz.getDeclaredFields()) {
            field.setAccessible(true);
            try {
                Object value = field.get(object);
                System.out.println("Field: " + field.getName() + ", Value: " + value);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }

}
