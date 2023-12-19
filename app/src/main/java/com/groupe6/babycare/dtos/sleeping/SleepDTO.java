package com.groupe6.babycare.dtos.sleeping;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SleepDTO implements Parcelable {

    private Long id;

    private int awakenings;
    private String sleepType;
    private String reminderState;

    private String startDate;
    private String endDate;
    private Long childId;

    protected SleepDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        awakenings = in.readInt();
        sleepType = in.readString();
        reminderState = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        endDate = in.readString();
        childId= in.readLong();
    }

    public static final Creator<SleepDTO> CREATOR = new Creator<SleepDTO>() {
        @Override
        public SleepDTO createFromParcel(Parcel in) {
            return new SleepDTO(in);
        }

        @Override
        public SleepDTO[] newArray(int size) {
            return new SleepDTO[size];
        }
    };

    @Override
    public String toString() {
        return "SleepDTO{" +
                "id=" + id +
                ", awakenings=" + awakenings +
                ", sleepType='" + sleepType + '\'' +
                ", reminderState='" + reminderState + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", childId=" + childId +
                '}';
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getAwakenings() {
        return awakenings;
    }

    public void setAwakenings(int awakenings) {
        this.awakenings = awakenings;
    }

    public String getSleepType() {
        return sleepType;
    }

    public void setSleepType(String sleepType) {
        this.sleepType = sleepType;
    }

    public String getReminderState() {
        return reminderState;
    }

    public void setReminderState(String reminderState) {
        this.reminderState = reminderState;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public SleepDTO(){}

    public SleepDTO(Long id, int awakenings, String sleepType, String reminderState, String startDate, String endDate, Long childId) {
        this.id = id;
        this.awakenings = awakenings;
        this.sleepType = sleepType;
        this.reminderState = reminderState;
        this.startDate = startDate;
        this.endDate = endDate;
        this.childId = childId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        if (id == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeLong(id);
        }
        parcel.writeInt(awakenings);
        parcel.writeString(sleepType);
        parcel.writeString(reminderState);
        parcel.writeString(startDate);
        parcel.writeString(endDate);
        parcel.writeLong(childId);
    }
}
