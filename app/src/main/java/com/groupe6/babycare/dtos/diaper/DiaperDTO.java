package com.groupe6.babycare.dtos.diaper;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class DiaperDTO implements Parcelable {

    private Long id;

    private String diaperType;

    private String reminderDate;

    private String reminderState;

    public DiaperDTO(Long id, String diaperType, String reminderDate, String reminderState) {
        this.id = id;
        this.diaperType = diaperType;
        this.reminderDate = reminderDate;
        this.reminderState = reminderState;
    }

    protected DiaperDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        diaperType = in.readString();
        reminderDate = in.readString();
        reminderState = in.readString();
    }

    public static final Creator<DiaperDTO> CREATOR = new Creator<DiaperDTO>() {
        @Override
        public DiaperDTO createFromParcel(Parcel in) {
            return new DiaperDTO(in);
        }

        @Override
        public DiaperDTO[] newArray(int size) {
            return new DiaperDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaperType() {
        return diaperType;
    }

    public void setDiaperType(String diaperType) {
        this.diaperType = diaperType;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeLong(id);

        dest.writeString(diaperType);
        dest.writeString(reminderState);
        dest.writeString(reminderDate);

    }
}
