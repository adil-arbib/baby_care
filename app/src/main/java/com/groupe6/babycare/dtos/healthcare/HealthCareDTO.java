package com.groupe6.babycare.dtos.healthcare;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class HealthCareDTO implements Parcelable {

    private Long id;
    private String healthCareType;
    private String notes;
    private String reminderState;

    private String reminderDate;

    public HealthCareDTO() {
    }

    public HealthCareDTO(Long id, String healthCareType, String notes, String reminderState, String reminderDate) {
        this.id = id;
        this.healthCareType = healthCareType;
        this.notes = notes;
        this.reminderState = reminderState;
        this.reminderDate = reminderDate;
    }

    protected HealthCareDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        healthCareType = in.readString();
        notes = in.readString();
        reminderState = in.readString();
        reminderDate = in.readString();
    }

    public static final Creator<HealthCareDTO> CREATOR = new Creator<HealthCareDTO>() {
        @Override
        public HealthCareDTO createFromParcel(Parcel in) {
            return new HealthCareDTO(in);
        }

        @Override
        public HealthCareDTO[] newArray(int size) {
            return new HealthCareDTO[size];
        }
    };

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHealthCareType() {
        return healthCareType;
    }

    public void setHealthCareType(String healthCareType) {
        this.healthCareType = healthCareType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "HealthCareDTO{" +
                "id=" + id +
                ", healthCareType='" + healthCareType + '\'' +
                ", notes='" + notes + '\'' +
                ", reminderState='" + reminderState + '\'' +
                ", reminderDate='" + reminderDate + '\'' +
                '}';
    }

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
        dest.writeString(healthCareType);
        dest.writeString(notes);
        dest.writeString(reminderState);
        dest.writeString(reminderDate);
    }
}
