package com.dashboardinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dashboardinventory.models.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
