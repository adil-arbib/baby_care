package com.groupe6.babycare.dtos.healthcare;

public class HealthCareCreateDTO {
    private String startDate;
    private String endDate;
    private String healthCareType;
    private String notes;
    private Long childId;

    public HealthCareCreateDTO() {
    }

    public HealthCareCreateDTO(String startDate, String endDate, String healthCareType, String notes, Long childId) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.healthCareType = healthCareType;
        this.notes = notes;
        this.childId = childId;
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

    public String getHealthCareType() {
        return healthCareType;
    }

    public void setHealthCareType(String healthCareType) {
        this.healthCareType = healthCareType;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Long getChildId() {
        return childId;
    }

    public void setChildId(Long childId) {
        this.childId = childId;
    }



    @Override
    public String toString() {
        return "HealthCareCreateDTO{" +
                "startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", healthCareType='" + healthCareType + '\'' +
                ", notes='" + notes + '\'' +
                ", childId=" + childId +
                '}';
    }
}
