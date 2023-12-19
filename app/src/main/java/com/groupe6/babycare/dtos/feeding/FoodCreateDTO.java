package com.groupe6.babycare.dtos.feeding;

public class FoodCreateDTO {

    private String label;
    private String nutritionType;

    private double quantity;

    private String reminderDate;

    private String reminderState;

    private Long childId;


    public FoodCreateDTO(String label, String nutritionType, double quantity, String reminderDate, String reminderState, Long childId) {
        this.label = label;
        this.nutritionType = nutritionType;
        this.quantity = quantity;
        this.reminderDate = reminderDate;
        this.reminderState = reminderState;
        this.childId = childId;
    }

    public FoodCreateDTO(String label, String nutritionType, double quantity, String reminderDate, Long childId) {
        this.label = label;
        this.nutritionType = nutritionType;
        this.quantity = quantity;
        this.reminderDate = reminderDate;
        this.reminderState = "UPCOMING";
        this.childId = childId;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getNutritionType() {
        return nutritionType;
    }

    public void setNutritionType(String nutritionType) {
        this.nutritionType = nutritionType;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
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

    @Override
    public String toString() {
        return "FoodCreateDTO{" +
                "label='" + label + '\'' +
                ", nutritionType='" + nutritionType + '\'' +
                ", quantity=" + quantity +
                ", reminderDate='" + reminderDate + '\'' +
                ", reminderState='" + reminderState + '\'' +
                ", childId=" + childId +
                '}';
    }
}
