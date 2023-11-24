package com.groupe6.babycare.dtos.activities;




public class SimpleActivityDTO {

    private Long id;
    private String type;
    private String date;

    public SimpleActivityDTO(Long id, String type, String date) {
        this.id = id;
        this.type = type;
        this.date = date;
    }

    public SimpleActivityDTO() {
    }

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "SimpleActivityDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
