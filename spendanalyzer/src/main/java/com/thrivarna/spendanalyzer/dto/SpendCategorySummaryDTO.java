package com.thrivarna.spendanalyzer.dto;
public class SpendCategorySummaryDTO {
    private Long categoryId;
    private String categoryName;
    private double totalAmount;

    public SpendCategorySummaryDTO(Long categoryId, String categoryName, double totalAmount) {
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.totalAmount = totalAmount;
    }

    // Getter and setter methods
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}

