package com.groupe6.babycare.dtos.diaper;

public class DiaperDTO {

    private Long id;

    private String diaperType;

    public DiaperDTO() {
    }

    public DiaperDTO(Long id, String diaperType) {
        this.id = id;
        this.diaperType = diaperType;
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

    @Override
    public String toString() {
        return "DiaperDTO{" +
                "id=" + id +
                ", diaperType='" + diaperType + '\'' +
                '}';
    }
}
