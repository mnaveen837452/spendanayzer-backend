package com.thrivarna.spendanalyzer.controller;

import com.thrivarna.spendanalyzer.dto.CategoryDTO;
import com.thrivarna.spendanalyzer.service.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import java.util.List;


@CrossOrigin("*")
@RestController
@RequestMapping("thrivarna/api/v1/categories")
@Tag(name = "Category", description = "Operations related to Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    @Operation(summary = "Create a new Category",
            description = "Creates a new Category and returns the created entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Successfully created", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CategoryDTO> createCategory(
            @RequestBody @Parameter(description = "Category details to create") CategoryDTO categoryDTO) {
        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all categories", description = "Retrieves a list of all categories")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of categories"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get a Category by ID",
            description = "Retrieves a Category by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<CategoryDTO> getCategory(
            @PathVariable @Parameter(description = "ID of the Category to retrieve") Long id) {
        CategoryDTO category = categoryService.getCategory(id);
        return new ResponseEntity<>(category, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing Category",
            description = "Updates an existing Category and returns the updated entity.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully updated", content = @Content(schema = @Schema(implementation = CategoryDTO.class))),
            @ApiResponse(responseCode = "404", description = "Category not found"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable @Parameter(description = "ID of the Category to update") Long id,
            @RequestBody @Parameter(description = "Updated Category details") CategoryDTO categoryDTO) {
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Category by ID",
            description = "Deletes a Category by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Successfully deleted"),
            @ApiResponse(responseCode = "404", description = "Category not found")
    })
    public ResponseEntity<Void> deleteCategory(
            @PathVariable @Parameter(description = "ID of the Category to delete") Long id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
