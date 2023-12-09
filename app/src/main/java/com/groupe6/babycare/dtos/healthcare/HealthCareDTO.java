package com.groupe6.babycare.dtos.healthcare;

public class HealthCareDTO {

    private Long id;

    private String healthCareType;
    private String notes;

    public HealthCareDTO() {
    }

    public HealthCareDTO(Long id, String healthCareType, String notes) {
        this.id = id;
        this.healthCareType = healthCareType;
        this.notes = notes;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "HealthCareDTO{" +
                "id=" + id +
                ", healthCareType='" + healthCareType + '\'' +
                ", notes='" + notes + '\'' +
                '}';
    }
}
