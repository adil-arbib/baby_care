package com.groupe6.babycare.dtos.activities;

public class ActivityCreateDTO {

    private String activityType;

    private String reminderDate;

    private String reminderState;

    private Long childId;

    private String notes;


    public ActivityCreateDTO(String activityType, String reminderDate, Long childId, String notes) {
        this.activityType = activityType;
        this.reminderDate = reminderDate;
        this.childId = childId;
        this.notes = notes;
        this.reminderState = "UPCOMING";
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
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

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
