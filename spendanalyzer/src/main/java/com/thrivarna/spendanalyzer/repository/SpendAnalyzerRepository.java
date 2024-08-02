package com.thrivarna.spendanalyzer.repository;

import com.thrivarna.spendanalyzer.dto.SpendCategorySummaryDTO;
import com.thrivarna.spendanalyzer.dto.SpendSummaryDTO;
import com.thrivarna.spendanalyzer.entity.SpendAnalyzer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;


@Repository
public interface SpendAnalyzerRepository extends JpaRepository<SpendAnalyzer, Long> {

    List<SpendAnalyzer> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT new com.thrivarna.spendanalyzer.dto.SpendSummaryDTO(sa.name, SUM(sa.amount)) " +
            "FROM SpendAnalyzer sa " +
            "WHERE sa.date BETWEEN :startDate AND :endDate " +
            "GROUP BY sa.name")
    List<SpendSummaryDTO> findSpendSummaryByDateRange(@Param("startDate") LocalDate startDate,
                                                      @Param("endDate") LocalDate endDate);

    @Query("SELECT new com.thrivarna.spendanalyzer.dto.SpendCategorySummaryDTO(sa.category.id, sa.category.name, SUM(sa.amount)) " +
            "FROM SpendAnalyzer sa " +
            "WHERE sa.date BETWEEN :startDate AND :endDate " +
            "GROUP BY sa.category.id, sa.category.name")
    List<SpendCategorySummaryDTO> findSpendSummaryByCategoryAndDateRange(@Param("startDate") LocalDate startDate,
                                                                         @Param("endDate") LocalDate endDate);
}
