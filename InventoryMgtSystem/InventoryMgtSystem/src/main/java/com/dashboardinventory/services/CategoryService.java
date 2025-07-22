package com.dashboardinventory.services;

import com.dashboardinventory.dtos.CategoryDTO;
import com.dashboardinventory.dtos.Response;


public interface CategoryService {
	
	Response createCategory(CategoryDTO categoryDTO);

    Response getAllCategories();

    Response getCategoryById(Long id);

    Response updateCategory(Long id, CategoryDTO categoryDTO);

    Response deleteCategory(Long id);

}
