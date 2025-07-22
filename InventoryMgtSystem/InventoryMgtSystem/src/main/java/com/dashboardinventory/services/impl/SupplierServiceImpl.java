package com.dashboardinventory.services.impl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dashboardinventory.dtos.Response;
import com.dashboardinventory.dtos.SupplierDTO;
import com.dashboardinventory.exceptions.NotFoundException;
import com.dashboardinventory.models.Supplier;
import com.dashboardinventory.repositories.SupplierRepository;
import com.dashboardinventory.services.SupplierService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class SupplierServiceImpl implements SupplierService {
	
	private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    
    private static final Logger log = LoggerFactory.getLogger(SupplierServiceImpl.class);

	public SupplierServiceImpl(SupplierRepository supplierRepository, ModelMapper modelMapper) {
		super();
		this.supplierRepository = supplierRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response addSupplier(SupplierDTO supplierDTO) {
		// TODO Auto-generated method stub
		Supplier supplierToSave = modelMapper.map(supplierDTO, Supplier.class);

        supplierRepository.save(supplierToSave);

        return Response.builder()
                .status(200)
                .message("Supplier Saved Successfully")
                .build();
	}

	@Override
	public Response updateSupplier(Long id, SupplierDTO supplierDTO) {
		// TODO Auto-generated method stub
		Supplier existingSupplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        if (supplierDTO.getName() != null) existingSupplier.setName(supplierDTO.getName());
        if (supplierDTO.getContactInfo() != null) existingSupplier.setContactInfo(supplierDTO.getContactInfo());
        if (supplierDTO.getAddress() != null) existingSupplier.setAddress(supplierDTO.getAddress());

        supplierRepository.save(existingSupplier);

        return Response.builder()
                .status(200)
                .message("Supplier Was Successfully Updated")
                .build();
	}

	@Override
	public Response getAllSupplier() {
		// TODO Auto-generated method stub
		List<Supplier> suppliers = supplierRepository.findAll(Sort.by(Sort.Direction.DESC, "id"));

        List<SupplierDTO> supplierDTOList = modelMapper.map(suppliers, new TypeToken<List<SupplierDTO>>() {
        }.getType());

        return Response.builder()
                .status(200)
                .message("success")
                .suppliers(supplierDTOList)
                .build();
	}

	@Override
	public Response getSupplierById(Long id) {
		// TODO Auto-generated method stub
		Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        SupplierDTO supplierDTO = modelMapper.map(supplier, SupplierDTO.class);

        return Response.builder()
                .status(200)
                .message("success")
                .supplier(supplierDTO)
                .build();
	}

	@Override
	public Response deleteSupplier(Long id) {
		// TODO Auto-generated method stub
		supplierRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

supplierRepository.deleteById(id);

return Response.builder()
        .status(200)
        .message("Supplier Was Successfully Deleted")
        .build();
	}
	
	

}
