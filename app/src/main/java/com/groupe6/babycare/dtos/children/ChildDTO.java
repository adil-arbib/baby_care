package com.groupe6.babycare.dtos.children;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class ChildDTO implements Parcelable {

    private Long id;
    private String firstName;
    private String lastName;
    private int age;
    private String birthDate;
    private String gender;
    private double weight;
    private double height;

    public ChildDTO() {
    }

    public ChildDTO(Long id, String firstName, String gender) {
        this.id = id;
        this.firstName = firstName;
        this.gender = gender;
    }

    public ChildDTO(Long id, String firstName, String lastName, int age, String birthDate, String gender, double weight, double height) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.birthDate = birthDate;
        this.gender = gender;
        this.weight = weight;
        this.height = height;
    }

    protected ChildDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        firstName = in.readString();
        lastName = in.readString();
        age = in.readInt();
        birthDate = in.readString();
        gender = in.readString();
        weight = in.readDouble();
        height = in.readDouble();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
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
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeInt(age);
        dest.writeString(birthDate);
        dest.writeString(gender);
        dest.writeDouble(weight);
        dest.writeDouble(height);
    }



}
