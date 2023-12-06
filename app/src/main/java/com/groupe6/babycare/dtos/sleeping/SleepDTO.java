package com.groupe6.babycare.dtos.sleeping;

public class SleepDTO {

    private Long id;
    private String type;
    private String status;
    private String date;
    private String startDate;
    private String endDate;
    int awakenings;


    public SleepDTO(Long id, String type, String status, String date, String startDate, String endDate, int awakenings) {
        this.id = id;
        this.type = type;
        this.status = status;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.awakenings = awakenings;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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

    public int getAwakenings() {
        return awakenings;
    }

    public void setAwakenings(int awakenings) {
        this.awakenings = awakenings;
    }

    @Override
    public String toString() {
        return "SleepDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", awakenings=" + awakenings +
                '}';
    }
}
