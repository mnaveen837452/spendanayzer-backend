package com.thrivarna.spendanalyzer.repository;

import com.thrivarna.spendanalyzer.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
