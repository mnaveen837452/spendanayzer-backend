package com.thrivarna.spendanalyzer.util;

import com.thrivarna.spendanalyzer.dto.CategoryDTO;
import com.thrivarna.spendanalyzer.dto.SpendAnalyzerDTO;
import com.thrivarna.spendanalyzer.entity.Category;
import com.thrivarna.spendanalyzer.entity.SpendAnalyzer;
import com.thrivarna.spendanalyzer.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Utility {

    private static String DATE_FORMAT = "dd-MM-yyyy";

    @Autowired
    private static CategoryRepository categoryRepository;


    public static SpendAnalyzerDTO convertSpendAnalyzerToSpendAnalyzerDTO(SpendAnalyzer spendAnalyzer) {
        SpendAnalyzerDTO spendAnalyzerDTO = new SpendAnalyzerDTO();
        spendAnalyzerDTO.setId(spendAnalyzer.getId());
        spendAnalyzerDTO.setName(spendAnalyzer.getName());
        spendAnalyzerDTO.setAmount(spendAnalyzer.getAmount());
        spendAnalyzerDTO.setDescription(spendAnalyzer.getDescription());
        spendAnalyzerDTO.setDate(DateUtils.localDateToString(spendAnalyzer.getDate(), DATE_FORMAT));

        if (spendAnalyzer.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(spendAnalyzer.getCategory().getId());
            categoryDTO.setName(spendAnalyzer.getCategory().getName());
            categoryDTO.setDescription(spendAnalyzer.getCategory().getDescription());
            spendAnalyzerDTO.setCategory(categoryDTO);
        }

        return spendAnalyzerDTO;
    }

    public static SpendAnalyzer convertSpendAnalyzerDTOToSpendAnalyzer(SpendAnalyzerDTO spendAnalyzerDTO) {
        SpendAnalyzer spendAnalyzer = new SpendAnalyzer();
        spendAnalyzer.setId(spendAnalyzerDTO.getId());
        spendAnalyzer.setName(spendAnalyzerDTO.getName());
        spendAnalyzer.setAmount(spendAnalyzerDTO.getAmount());
        spendAnalyzer.setDescription(spendAnalyzerDTO.getDescription());
        spendAnalyzer.setDate(DateUtils.stringToLocalDate(spendAnalyzerDTO.getDate()));

        if (spendAnalyzerDTO.getCategory() != null) {
            Category category = categoryRepository.findById(spendAnalyzerDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            spendAnalyzer.setCategory(category);
        }

        return spendAnalyzer;
    }
}
