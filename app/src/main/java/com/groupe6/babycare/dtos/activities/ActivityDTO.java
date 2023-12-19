package com.groupe6.babycare.dtos.activities;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ActivityDTO implements Parcelable {

    private Long id;
    private String type;
    private String date;

    private String status;

    private String note;


    public ActivityDTO(Long id, String type, String date, String status, String note) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.status = status;
        this.note = note;
    }

    protected ActivityDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        type = in.readString();
        date = in.readString();
        status = in.readString();
        note = in.readString();
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

    public ActivityDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public String toString() {
        return "ActivityDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                ", status='" + status + '\'' +
                ", note='" + note + '\'' +
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
        dest.writeString(type);
        dest.writeString(date);
        dest.writeString(status);
        dest.writeString(note);
    }
}
