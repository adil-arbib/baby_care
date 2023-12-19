package com.groupe6.babycare.dtos.reminder;

public class ReminderDTO {

    private Long id;

    private String date;

    private String type;

    public ReminderDTO(Long id, String date, String type) {
        this.id = id;
        this.date = date;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
