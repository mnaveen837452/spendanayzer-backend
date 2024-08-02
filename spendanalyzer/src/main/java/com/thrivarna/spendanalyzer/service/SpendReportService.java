package com.thrivarna.spendanalyzer.service;

import com.thrivarna.spendanalyzer.dto.SpendAnalyzerDTO;
import com.thrivarna.spendanalyzer.entity.SpendAnalyzer;
import com.thrivarna.spendanalyzer.repository.SpendAnalyzerRepository;
import com.thrivarna.spendanalyzer.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpendReportService {

    @Autowired
    private SpendAnalyzerRepository spendAnalyzerRepository;

    public List<SpendAnalyzerDTO> getSpendDetails(LocalDate startDate, LocalDate endDate) {
        List<SpendAnalyzer> listOfSpends = spendAnalyzerRepository.findByDateBetween(startDate, endDate);

        return listOfSpends.stream()
                .map(Utility::convertSpendAnalyzerToSpendAnalyzerDTO)
                .collect(Collectors.toList());
    }
}

