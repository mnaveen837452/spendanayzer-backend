package com.thrivarna.spendanalyzer.controller;

import com.thrivarna.spendanalyzer.dto.SpendAnalyzerDTO;
import com.thrivarna.spendanalyzer.dto.SpendCategorySummaryDTO;
import com.thrivarna.spendanalyzer.dto.SpendSummaryDTO;
import com.thrivarna.spendanalyzer.service.SpendAnalyzerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("thrivarna/api/v1/spendanalyzer")
@Tag(name = "SpendAnalyzer", description = "Operations related to SpendAnalyzer")
public class SpendAnalyzerController {

    @Autowired
    private SpendAnalyzerService spendAnalyzerService;

    @GetMapping
    @Operation(summary = "Get all spend records", description = "Retrieves a list of all spend records")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of spend records"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<SpendAnalyzerDTO> getAllSpends() {
        return spendAnalyzerService.getAllSpends();
    }

    @PostMapping
    @Operation(summary = "Create a new SpendAnalyzer",
            description = "Creates a new SpendAnalyzer and returns the created entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created", content = @Content(schema = @Schema(implementation = SpendAnalyzerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SpendAnalyzerDTO> createSpendAnalyzer(@RequestBody SpendAnalyzerDTO spendAnalyzerDTO) {
        SpendAnalyzerDTO createdSpendAnalyzer = spendAnalyzerService.createSpendAnalyzer(spendAnalyzerDTO);
        return new ResponseEntity<>(createdSpendAnalyzer, HttpStatus.CREATED);
    }

    @PostMapping("/listofspends")
    @Operation(summary = "Create a new SpendAnalyzer",
            description = "Creates a new SpendAnalyzer and returns the created entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created", content = @Content(schema = @Schema(implementation = SpendAnalyzerDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public List<SpendAnalyzerDTO> createSpendAnalyzerWithList(@RequestBody List<SpendAnalyzerDTO> spendAnalyzerDTO) {
        return spendAnalyzerService.createSpendAnalyzerWithList(spendAnalyzerDTO);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a SpendAnalyzer by ID",
            description = "Retrieves a SpendAnalyzer by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(schema = @Schema(implementation = SpendAnalyzerDTO.class))),
            @ApiResponse(responseCode = "404", description = "SpendAnalyzer not found")
    })
    public ResponseEntity<SpendAnalyzerDTO> getSpendAnalyzer(@PathVariable Long id) {
        SpendAnalyzerDTO spendAnalyzer = spendAnalyzerService.getSpendAnalyzer(id);
        return new ResponseEntity<>(spendAnalyzer, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing SpendAnalyzer",
            description = "Updates an existing SpendAnalyzer and returns the updated entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated", content = @Content(schema = @Schema(implementation = SpendAnalyzerDTO.class))),
            @ApiResponse(responseCode = "404", description = "SpendAnalyzer not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<SpendAnalyzerDTO> updateSpendAnalyzer(@PathVariable Long id, @RequestBody SpendAnalyzerDTO spendAnalyzerDTO) {
        SpendAnalyzerDTO updatedSpendAnalyzer = spendAnalyzerService.updateSpendAnalyzer(id, spendAnalyzerDTO);
        return new ResponseEntity<>(updatedSpendAnalyzer, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a SpendAnalyzer by ID",
            description = "Deletes a SpendAnalyzer by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "SpendAnalyzer not found")
    })
    public ResponseEntity<Void> deleteSpendAnalyzer(@PathVariable Long id) {
        spendAnalyzerService.deleteSpendAnalyzer(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/summary")
    @Operation(summary = "Get spend summary by date range", description = "Retrieves spend details like name and sum of amount for the given date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved spend summary"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<SpendSummaryDTO> getSpendSummaryByDateRange(@RequestParam String startDate,
                                                            @RequestParam String endDate) {
        return spendAnalyzerService.getSpendSummaryByDateRange(startDate, endDate);
    }

    @GetMapping("/summarybycategory")
    @Operation(summary = "Get category wise spend summary by date range", description = "Retrieves category wise spend details like name and sum of amount for the given date range")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved spend summary"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<SpendCategorySummaryDTO> getCategorySummaryByDateRange(@RequestParam String startDate,
                                                                       @RequestParam String endDate) {
        return spendAnalyzerService.getSpendSummaryByCategoryAndDateRange(startDate, endDate);
    }

    public List<SpendCategorySummaryDTO> getCategorySummaryByDateRange2(@RequestParam String startDate,
                                                                       @RequestParam String endDate) {
        return spendAnalyzerService.getSpendSummaryByCategoryAndDateRange(startDate, endDate);
    }
}

