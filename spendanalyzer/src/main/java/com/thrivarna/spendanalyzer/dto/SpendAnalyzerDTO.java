package com.thrivarna.spendanalyzer.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SpendAnalyzerDTO {
    private long id;
    private String date;

    private String name;
    private double amount;
    private CategoryDTO category;
    private String description;
}

