package com.thrivarna.spendanalyzer.controller;

import com.thrivarna.spendanalyzer.dto.SpendAnalyzerDTO;
import com.thrivarna.spendanalyzer.service.SpendReportService;
import com.thrivarna.spendanalyzer.util.DateUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("thrivarna/api/v1/reports")
@Tag(name = "SpendReport", description = "Get the report for spends")
public class SpendReportController {

    @Autowired
    private SpendReportService spendReportService;


    @GetMapping("/betweendates")
    @Operation(summary = "Get Details of spends",
            description = "Get details of spend for given dates")
    @ApiResponses(value ={
            @ApiResponse(responseCode = "200", description = "Available data input"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public List<SpendAnalyzerDTO> getSpendDetailsBetweenDates(
            @RequestParam String startDate,
            @RequestParam String endDate) {

        LocalDate start = DateUtils.stringToLocalDate(startDate);
        LocalDate end = DateUtils.stringToLocalDate(endDate);

        return spendReportService.getSpendDetails(start, end);
    }
}
