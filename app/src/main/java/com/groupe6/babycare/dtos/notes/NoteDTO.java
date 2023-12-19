package com.groupe6.babycare.dtos.notes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class NoteDTO implements Parcelable {

    private Long id;
    private String title;
    private String content;
    private String date;

    public NoteDTO() {
    }

    public NoteDTO(Long id, String title, String content, String date) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = date;
    }

    protected NoteDTO(Parcel in) {
        if (in.readByte() == 0) {
            id = null;
        } else {
            id = in.readLong();
        }
        title = in.readString();
        content = in.readString();
        date = in.readString();
    }

    public static final Creator<NoteDTO> CREATOR = new Creator<NoteDTO>() {
        @Override
        public NoteDTO createFromParcel(Parcel in) {
            return new NoteDTO(in);
        }

        @Override
        public NoteDTO[] newArray(int size) {
            return new NoteDTO[size];
        }
    };

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "NoteDTO{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
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
        dest.writeString(title);
        dest.writeString(content);
        dest.writeString(date);
    }
}
