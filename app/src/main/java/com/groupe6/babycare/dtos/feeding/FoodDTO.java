package com.groupe6.babycare.dtos.feeding;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class FoodDTO implements Parcelable {

    private Long id;

    private String type;

    private String label;

    private Double quantity;

    private String status;

    private String date;

    public FoodDTO(Long id, String type, String label, Double quantity, String status, String date) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.quantity = quantity;
        this.status = status;
        this.date = date;
    }

    public FoodDTO() {
    }

    protected FoodDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        type = in.readString();
        label = in.readString();
        if (in.readByte() == 0) {
            quantity = null;
        } else {
            quantity = in.readDouble();
        }
        status = in.readString();
        date = in.readString();
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

    @Override
    public String toString() {
        return "FoodDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
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
        dest.writeString(label);
        if (quantity == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeDouble(quantity);
        }
        dest.writeString(status);
        dest.writeString(date);
    }
}
