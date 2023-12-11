package com.groupe6.babycare.dtos.sleeping;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class SleepDTO implements Parcelable {

    private Long id;
    private String type;
    private String status;
    private String date;
    private String startDate;
    private String endDate;
    int awakenings;


    public SleepDTO(Long id, String type, String status, String date, String startDate, String endDate, int awakenings) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.awakenings = awakenings;
    }

    protected SleepDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        type = in.readString();
        status = in.readString();
        date = in.readString();
        startDate = in.readString();
        endDate = in.readString();
        awakenings = in.readInt();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getAwakenings() {
        return awakenings;
    }

    public void setAwakenings(int awakenings) {
        this.awakenings = awakenings;
    }

    @Override
    public String toString() {
        return "SleepDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", awakenings=" + awakenings +
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
        dest.writeString(status);
        dest.writeString(date);
        dest.writeString(startDate);
        dest.writeString(endDate);
        dest.writeInt(awakenings);
    }
}
