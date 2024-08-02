package com.thrivarna.spendanalyzer.dto;

public class SpendSummaryDTO {
    private String name;
    private double totalAmount;

    public SpendSummaryDTO(String name, double totalAmount) {
        this.name = name;
        this.totalAmount = totalAmount;
    }

    // Getter and setter methods
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
