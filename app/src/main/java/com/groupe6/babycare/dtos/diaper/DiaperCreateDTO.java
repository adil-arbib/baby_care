package com.groupe6.babycare.dtos.diaper;

public class DiaperCreateDTO {

    private String reminderDate;
    private String reminderState;
    private Long childId;
    private String diaperType;

    public DiaperCreateDTO(String reminderDate, Long childId, String diaperType) {
        this.reminderDate = reminderDate;
        this.childId = childId;
        this.diaperType = diaperType;
        this.reminderState = "UPCOMING";
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

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }

    public String getDiaperType() {
        return diaperType;
    }

    public void setDiaperType(String diaperType) {
        this.diaperType = diaperType;
    }
}
