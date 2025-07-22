package com.dashboardinventory.services;

import com.dashboardinventory.dtos.Response;
import com.dashboardinventory.dtos.SupplierDTO;

public interface SupplierService {
	
	Response addSupplier(SupplierDTO supplierDTO);

    Response updateSupplier(Long id, SupplierDTO supplierDTO);

    Response getAllSupplier();

    Response getSupplierById(Long id);

    Response deleteSupplier(Long id);

}
