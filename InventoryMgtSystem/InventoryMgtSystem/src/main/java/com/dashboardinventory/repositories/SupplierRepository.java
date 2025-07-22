package com.dashboardinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dashboardinventory.models.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Long> {

}
