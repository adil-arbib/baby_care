package com.groupe6.babycare.dtos.feeding;

public class FoodDTO {

    private Long id;

    private String type;

    private String label;

    private Double quantity;

    private String status;

    private String date;

    public FoodDTO(Long id, String type, String label, Double quantity, String status, String date) {
        this.id = id;
        this.type = type;
        this.label = label;
        this.quantity = quantity;
        this.status = status;
        this.date = date;
    }

    public FoodDTO() {
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

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "FoodDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", label='" + label + '\'' +
                ", quantity=" + quantity +
                ", status='" + status + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
