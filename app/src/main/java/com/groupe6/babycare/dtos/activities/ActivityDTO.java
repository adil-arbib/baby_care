package com.groupe6.babycare.dtos.activities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public class ActivityDTO implements Parcelable {

    private Long id;

    private String activityType;
    private String reminderDate;

    private String reminderState;

    private String notes;

    public ActivityDTO() {
    }

    public ActivityDTO(Long id, String activityType, String reminderDate, String reminderState, String notes) {
        this.id = id;
        this.activityType = activityType;
        this.reminderDate = reminderDate;
        this.reminderState = reminderState;
        this.notes = notes;
    }

    protected ActivityDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        activityType = in.readString();
        reminderDate = in.readString();
        reminderState = in.readString();
        notes = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (id == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeLong(id);
        }
        dest.writeString(activityType);
        dest.writeString(reminderDate);
        dest.writeString(reminderState);
        dest.writeString(notes);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<ActivityDTO> CREATOR = new Creator<ActivityDTO>() {
        @Override
        public ActivityDTO createFromParcel(Parcel in) {
            return new ActivityDTO(in);
        }

        @Override
        public ActivityDTO[] newArray(int size) {
            return new ActivityDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public String getReminderDate() {
        return reminderDate;
    }

    public void setReminderDate(String reminderDate) {
        this.reminderDate = reminderDate;
    }

    public String getReminderState() {
        return reminderState;
    }

    public void setReminderState(String reminderState) {
        this.reminderState = reminderState;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "id=" + id +
                ", activityType='" + activityType + '\'' +
                ", reminderDate='" + reminderDate + '\'' +
                ", reminderState='" + reminderState + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
