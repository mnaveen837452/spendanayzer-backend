package com.thrivarna.spendanalyzer.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class SpendAnalyzer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate date;
    private double amount;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;


    private String description;
}

