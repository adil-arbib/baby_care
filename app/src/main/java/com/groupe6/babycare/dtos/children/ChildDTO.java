package com.groupe6.babycare.dtos.children;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ChildDTO implements Parcelable {

    private String name;
    private String gender;

    public ChildDTO() {
    }

    public ChildDTO(String name, String gender) {
        this.name = name;
        this.gender = gender;
    }

    protected ChildDTO(Parcel in) {
        name = in.readString();
        gender = in.readString();
    }

    public static final Creator<ChildDTO> CREATOR = new Creator<ChildDTO>() {
        @Override
        public ChildDTO createFromParcel(Parcel in) {
            return new ChildDTO(in);
        }

        @Override
        public ChildDTO[] newArray(int size) {
            return new ChildDTO[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "ChildDTO{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(gender);
    }
}
