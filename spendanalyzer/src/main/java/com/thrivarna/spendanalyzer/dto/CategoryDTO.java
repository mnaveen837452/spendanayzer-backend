package com.thrivarna.spendanalyzer.dto;

import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CategoryDTO {
    private Long id;
    private String name;
    private String description;
    private Set<SpendAnalyzerDTO> spendAnalyzerDTOS;
}
