package com.thrivarna.spendanalyzer.service;

import com.thrivarna.spendanalyzer.dto.CategoryDTO;
import com.thrivarna.spendanalyzer.dto.SpendAnalyzerDTO;
import com.thrivarna.spendanalyzer.dto.SpendCategorySummaryDTO;
import com.thrivarna.spendanalyzer.dto.SpendSummaryDTO;
import com.thrivarna.spendanalyzer.entity.Category;
import com.thrivarna.spendanalyzer.entity.SpendAnalyzer;
import com.thrivarna.spendanalyzer.repository.CategoryRepository;
import com.thrivarna.spendanalyzer.repository.SpendAnalyzerRepository;
import com.thrivarna.spendanalyzer.util.DateUtils;
import com.thrivarna.spendanalyzer.util.Utility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SpendAnalyzerService {

    private static  String DATE_FORMAT = "yyyy-MM-dd";


    @Autowired
    private SpendAnalyzerRepository spendAnalyzerRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    /**
     * Retrieves a list of all spend records.
     * @return a list of SpendAnalyzerDTO representing all spend records.
     */
    public List<SpendAnalyzerDTO> getAllSpends() {
        return spendAnalyzerRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }


    public SpendAnalyzerDTO createSpendAnalyzer(SpendAnalyzerDTO spendAnalyzerDTO) {
        SpendAnalyzer spendAnalyzer = new SpendAnalyzer();
        spendAnalyzer.setName(spendAnalyzerDTO.getName());
        spendAnalyzer.setAmount(spendAnalyzerDTO.getAmount());
        spendAnalyzer.setDate(DateUtils.stringToLocalDate(spendAnalyzerDTO.getDate(), DATE_FORMAT));
        spendAnalyzer.setDescription(spendAnalyzerDTO.getDescription());

        if (spendAnalyzerDTO.getCategory() != null) {
            Category category = categoryRepository.findById(spendAnalyzerDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            spendAnalyzer.setCategory(category);
        }

        SpendAnalyzer savedSpendAnalyzer = spendAnalyzerRepository.save(spendAnalyzer);
        return convertToDTO(savedSpendAnalyzer);
    }

    public List<SpendAnalyzerDTO> createSpendAnalyzerWithList(List<SpendAnalyzerDTO> spendAnalyzerDTO) {

        List<SpendAnalyzer> listOfSpendAnalyzer = spendAnalyzerDTO.stream()
                .map(Utility::convertSpendAnalyzerDTOToSpendAnalyzer)
                .toList();

        List<SpendAnalyzer> savedSpendAnalyzer = spendAnalyzerRepository.saveAll(listOfSpendAnalyzer);
        return savedSpendAnalyzer.stream().map(Utility::convertSpendAnalyzerToSpendAnalyzerDTO).toList();
    }

    public SpendAnalyzerDTO getSpendAnalyzer(Long id) {
        SpendAnalyzer spendAnalyzer = spendAnalyzerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpendAnalyzer not found"));
        return convertToDTO(spendAnalyzer);
    }

    public SpendAnalyzerDTO updateSpendAnalyzer(Long id, SpendAnalyzerDTO spendAnalyzerDTO) {
        SpendAnalyzer spendAnalyzer = spendAnalyzerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpendAnalyzer not found"));
        spendAnalyzer.setName(spendAnalyzerDTO.getName());
        spendAnalyzer.setAmount(spendAnalyzerDTO.getAmount());

        if (spendAnalyzerDTO.getCategory() != null) {
            Category category = categoryRepository.findById(spendAnalyzerDTO.getCategory().getId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            spendAnalyzer.setCategory(category);
        }

        SpendAnalyzer updatedSpendAnalyzer = spendAnalyzerRepository.save(spendAnalyzer);
        return convertToDTO(updatedSpendAnalyzer);
    }

    public void deleteSpendAnalyzer(Long id) {
        SpendAnalyzer spendAnalyzer = spendAnalyzerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("SpendAnalyzer not found"));
        spendAnalyzerRepository.delete(spendAnalyzer);
    }

    private SpendAnalyzerDTO convertToDTO(SpendAnalyzer spendAnalyzer) {
        SpendAnalyzerDTO spendAnalyzerDTO = new SpendAnalyzerDTO();
        spendAnalyzerDTO.setId(spendAnalyzer.getId());
        spendAnalyzerDTO.setName(spendAnalyzer.getName());
        spendAnalyzerDTO.setAmount(spendAnalyzer.getAmount());
        spendAnalyzerDTO.setDescription(spendAnalyzer.getDescription());
        spendAnalyzerDTO.setDate(DateUtils.localDateToString(spendAnalyzer.getDate(), "dd-MM-yyyy"));

        if (spendAnalyzer.getCategory() != null) {
            CategoryDTO categoryDTO = new CategoryDTO();
            categoryDTO.setId(spendAnalyzer.getCategory().getId());
            categoryDTO.setName(spendAnalyzer.getCategory().getName());
            categoryDTO.setDescription(spendAnalyzer.getCategory().getDescription());
            spendAnalyzerDTO.setCategory(categoryDTO);
        }

        return spendAnalyzerDTO;
    }

    /**
     * Retrieves spend details (name and sum of amount) for the given date range.
     * @param startDate the start date of the range.
     * @param endDate the end date of the range.
     * @return a list of SpendSummaryDTO containing spend names and their total amounts.
     */
    public List<SpendSummaryDTO> getSpendSummaryByDateRange(String strStartDate, String strEndDate) {
        LocalDate startDate = DateUtils.stringToLocalDate(strStartDate);
        LocalDate endDate = DateUtils.stringToLocalDate(strEndDate);
        return spendAnalyzerRepository.findSpendSummaryByDateRange(startDate, endDate);
    }


    /**
     * Retrieves spend details (category and sum of amount) for the given date range.
     * @param startDate the start date of the range.
     * @param endDate the end date of the range.
     * @return a list of SpendCategorySummaryDTO containing category details and their total amounts.
     */
    public List<SpendCategorySummaryDTO> getSpendSummaryByCategoryAndDateRange(String strStartDate, String strEndDate) {
        LocalDate startDate = DateUtils.clientStringToLocalDate(strStartDate);
        LocalDate endDate = DateUtils.clientStringToLocalDate(strEndDate);
        System.out.println("start date ==>"+ strEndDate);
        System.out.println("strStartDate date ==>"+ strStartDate);
        return spendAnalyzerRepository.findSpendSummaryByCategoryAndDateRange(startDate, endDate);
    }

    public List<SpendSummaryDTO> getSpendSummaryByDateRange2(String strStartDate, String strEndDate) {
        LocalDate startDate = DateUtils.stringToLocalDate(strStartDate);
        LocalDate endDate = DateUtils.stringToLocalDate(strEndDate);
        return spendAnalyzerRepository.findSpendSummaryByDateRange(startDate, endDate);
    }
}
