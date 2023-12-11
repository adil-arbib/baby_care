package com.groupe6.babycare.dtos.feeding;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class FoodDTO implements Parcelable {

    private Long id;


    private String nutritionType;

    private String label;

    private Double quantity;

    private String reminderState;

    private String reminderDate;

    public FoodDTO() {
    }

    public FoodDTO(Long id, String nutritionType, String label, Double quantity, String reminderState, String reminderDate) {
        this.id = id;
        this.nutritionType = nutritionType;
        this.label = label;
        this.quantity = quantity;
        this.reminderState = reminderState;
        this.reminderDate = reminderDate;
    }

    protected FoodDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        nutritionType = in.readString();
        label = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readDouble();
        }
        reminderState = in.readString();
        reminderDate = in.readString();
    }

    public static final Creator<FoodDTO> CREATOR = new Creator<FoodDTO>() {
        @Override
        public FoodDTO createFromParcel(Parcel in) {
            return new FoodDTO(in);
        }

        @Override
        public FoodDTO[] newArray(int size) {
            return new FoodDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(nutritionType);
        dest.writeString(label);
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(quantity);
        }
        dest.writeString(reminderState);
        dest.writeString(reminderDate);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNutritionType() {
        return nutritionType;
    }

    public void setNutritionType(String nutritionType) {
        this.nutritionType = nutritionType;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public String getReminderState() {
        return reminderState;
    }

    public void setReminderState(String reminderState) {
        this.reminderState = reminderState;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }
}
