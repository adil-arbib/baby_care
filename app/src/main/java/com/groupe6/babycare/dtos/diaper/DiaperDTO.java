package com.groupe6.babycare.dtos.diaper;

public class DiaperDTO {

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
}
