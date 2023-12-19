package com.groupe6.babycare.dtos.tips;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class TipsDTO implements Parcelable {

    private Long id;
    private String category;
    private String target;
    private String description;

    public TipsDTO() {
    }

    protected TipsDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        category = in.readString();
        target = in.readString();
        description = in.readString();
    }

    public static final Creator<TipsDTO> CREATOR = new Creator<TipsDTO>() {
        @Override
        public TipsDTO createFromParcel(Parcel in) {
            return new TipsDTO(in);
        }

        @Override
        public TipsDTO[] newArray(int size) {
            return new TipsDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTarget() {
        return target;
    }

    public String getDescription() {
        return description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "TipsDTO{" +
                "id=" + id +
                ", category='" + category + '\'' +
                ", target='" + target + '\'' +
                ", description='" + description + '\'' +
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
        dest.writeString(category);
        dest.writeString(target);
        dest.writeString(description);
    }
}
