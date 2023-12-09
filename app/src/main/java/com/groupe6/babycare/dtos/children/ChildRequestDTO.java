package com.groupe6.babycare.dtos.children;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.groupe6.babycare.dtos.activities.ActivityDTO;
import com.groupe6.babycare.dtos.diaper.DiaperDTO;
import com.groupe6.babycare.dtos.feeding.FoodDTO;
import com.groupe6.babycare.dtos.healthcare.HealthCareDTO;

import java.util.Date;
import java.util.List;

public class ChildRequestDTO implements Parcelable {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private Date birthDate;
    private String gender;
    private double weight;
    private double height;
    private List<FoodDTO> nutritionReminders;
    private List<DiaperDTO> diaperReminders;
    private List<ActivityDTO> activityReminders;
    private List<HealthCareDTO> healthCareReminders;

    public ChildRequestDTO() {
    }

    public ChildRequestDTO(
            Long id,
            String firstName,
            String lastName,
            int age,
            Date birthDate,
            String gender,
            double weight,
            double height,
            List<FoodDTO> nutritionReminders,
            List<DiaperDTO> diaperReminders,
            List<ActivityDTO> activityReminders,
            List<HealthCareDTO> healthCareReminders) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
        this.nutritionReminders = nutritionReminders;
        this.diaperReminders = diaperReminders;
        this.activityReminders = activityReminders;
        this.healthCareReminders = healthCareReminders;
    }

    protected ChildRequestDTO(Parcel in) {

        id = in.readLong();
        firstName = in.readString();
        lastName = in.readString();
        age = in.readInt();
        birthDate = new Date(in.readLong());
        gender = in.readString();
        weight = in.readDouble();
        height = in.readDouble();
        in.readList(nutritionReminders, FoodDTO.class.getClassLoader());
        in.readList(diaperReminders, DiaperDTO.class.getClassLoader());
        in.readList(activityReminders, ActivityDTO.class.getClassLoader());
        in.readList(healthCareReminders, HealthCareDTO.class.getClassLoader());
    }

    public static final Creator<ChildRequestDTO> CREATOR = new Creator<ChildRequestDTO>() {
        @Override
        public ChildRequestDTO createFromParcel(Parcel in) {
            return new ChildRequestDTO(in);
        }

        @Override
        public ChildRequestDTO[] newArray(int size) {
            return new ChildRequestDTO[size];
        }
    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(age);
        dest.writeLong(birthDate.getTime());
        dest.writeString(gender);
        dest.writeDouble(weight);
        dest.writeDouble(height);
        dest.writeList(nutritionReminders);
        dest.writeList(diaperReminders);
        dest.writeList(activityReminders);
        dest.writeList(healthCareReminders);
    }
}