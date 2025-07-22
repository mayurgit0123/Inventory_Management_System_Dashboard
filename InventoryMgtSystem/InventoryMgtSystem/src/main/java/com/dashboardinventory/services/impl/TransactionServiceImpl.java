package com.dashboardinventory.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.dashboardinventory.dtos.Response;
import com.dashboardinventory.dtos.TransactionDTO;
import com.dashboardinventory.dtos.TransactionRequest;
import com.dashboardinventory.enums.TransactionStatus;
import com.dashboardinventory.enums.TransactionType;
import com.dashboardinventory.exceptions.NameValueRequiredException;
import com.dashboardinventory.exceptions.NotFoundException;
import com.dashboardinventory.models.Product;
import com.dashboardinventory.models.Supplier;
import com.dashboardinventory.models.Transaction;
import com.dashboardinventory.models.User;
import com.dashboardinventory.repositories.ProductRepository;
import com.dashboardinventory.repositories.SupplierRepository;
import com.dashboardinventory.repositories.TransactionRepository;
import com.dashboardinventory.services.TransactionService;
import com.dashboardinventory.services.UserService;
import com.dashboardinventory.specification.TransactionFilter;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService{
	
	private final TransactionRepository transactionRepository;
    private final ProductRepository productRepository;
    private final SupplierRepository supplierRepository;
    private final UserService userService;
    private final ModelMapper modelMapper;
    
    private static final Logger log = LoggerFactory.getLogger(TransactionServiceImpl.class);

	public TransactionServiceImpl(TransactionRepository transactionRepository, ProductRepository productRepository,
			SupplierRepository supplierRepository, UserService userService, ModelMapper modelMapper) {
		super();
		this.transactionRepository = transactionRepository;
		this.productRepository = productRepository;
		this.supplierRepository = supplierRepository;
		this.userService = userService;
		this.modelMapper = modelMapper;
	}

	@Override
	public Response purchase(TransactionRequest transactionRequest) {
		// TODO Auto-generated method stub
		Long productId = transactionRequest.getProductId();
        Long supplierId = transactionRequest.getSupplierId();
        Integer quantity = transactionRequest.getQuantity();

        if (supplierId == null) throw new NameValueRequiredException("Supplier Id is Required");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        User user = userService.getCurrentLoggedInUser();

        //update the stock quantity and re-save
        product.setStockQuantity(product.getStockQuantity() + quantity);
        productRepository.save(product);

        //create a transaction
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.PURCHASE)
                .status(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .supplier(supplier)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(transactionRequest.getDescription())
                .note(transactionRequest.getNote())
                .build();

        transactionRepository.save(transaction);
        return Response.builder()
                .status(200)
                .message("Purchase Made successfully")
                .build();
	}

	@Override
	public Response sell(TransactionRequest transactionRequest) {
		// TODO Auto-generated method stub
		Long productId = transactionRequest.getProductId();
        Integer quantity = transactionRequest.getQuantity();

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        User user = userService.getCurrentLoggedInUser();

        //update the stock quantity and re-save
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);


        //create a transaction
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.SALE)
                .status(TransactionStatus.COMPLETED)
                .product(product)
                .user(user)
                .totalProducts(quantity)
                .totalPrice(product.getPrice().multiply(BigDecimal.valueOf(quantity)))
                .description(transactionRequest.getDescription())
                .note(transactionRequest.getNote())
                .build();

        transactionRepository.save(transaction);
        return Response.builder()
                .status(200)
                .message("Product Sale successfully made")
                .build();

	}

	@Override
	public Response returnToSupplier(TransactionRequest transactionRequest) {
		// TODO Auto-generated method stub
		Long productId = transactionRequest.getProductId();
        Long supplierId = transactionRequest.getSupplierId();
        Integer quantity = transactionRequest.getQuantity();

        if (supplierId == null) throw new NameValueRequiredException("Supplier Id is Required");

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new NotFoundException("Product Not Found"));

        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new NotFoundException("Supplier Not Found"));

        User user = userService.getCurrentLoggedInUser();

        //update the stock quantity and re-save
        product.setStockQuantity(product.getStockQuantity() - quantity);
        productRepository.save(product);


        //create a transaction
        Transaction transaction = Transaction.builder()
                .transactionType(TransactionType.RETURN_TO_SUPPLIER)
                .status(TransactionStatus.PROCESSING)
                .product(product)
                .user(user)
                .totalProducts(quantity)
                .totalPrice(BigDecimal.ZERO)
                .description(transactionRequest.getDescription())
                .note(transactionRequest.getNote())
                .build();

        transactionRepository.save(transaction);

        return Response.builder()
                .status(200)
                .message("Product Returned in progress")
                .build();
	}

	@Override
	public Response getAllTransactions(int page, int size, String filter) {
		// TODO Auto-generated method stub
		Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "id"));

        //user the Transaction specification
        Specification<Transaction> spec = TransactionFilter.byFilter(filter);
        Page<Transaction> transactionPage = transactionRepository.findAll(spec, pageable);

        List<TransactionDTO> transactionDTOS = modelMapper.map(transactionPage.getContent(), new TypeToken<List<TransactionDTO>>() {
        }.getType());

        transactionDTOS.forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setProduct(null);
            transactionDTO.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("success")
                .transactions(transactionDTOS)
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .build();
	}

	@Override
	public Response getAllTransactionById(Long id) {
		// TODO Auto-generated method stub
		Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Transaction Not Found"));

        TransactionDTO transactionDTO = modelMapper.map(transaction, TransactionDTO.class);

        transactionDTO.getUser().setTransactions(null);

        return Response.builder()
                .status(200)
                .message("success")
                .transaction(transactionDTO)
                .build();
	}

	@Override
	public Response getAllTransactionByMonthAndYear(int month, int year) {
		// TODO Auto-generated method stub
		List<Transaction> transactions = transactionRepository.findAll(TransactionFilter.byMonthAndYear(month, year));

        List<TransactionDTO> transactionDTOS = modelMapper.map(transactions, new TypeToken<List<TransactionDTO>>() {
        }.getType());

        transactionDTOS.forEach(transactionDTO -> {
            transactionDTO.setUser(null);
            transactionDTO.setProduct(null);
            transactionDTO.setSupplier(null);
        });

        return Response.builder()
                .status(200)
                .message("success")
                .transactions(transactionDTOS)
                .build();
	}

	@Override
	public Response updateTransactionStatus(Long transactionId, TransactionStatus status) {
		// TODO Auto-generated method stub
		Transaction existingTransaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new NotFoundException("Transaction Not Found"));

        existingTransaction.setStatus(status);
        existingTransaction.setUpdateAt(LocalDateTime.now());

        transactionRepository.save(existingTransaction);

        return Response.builder()
                .status(200)
                .message("Transaction Status Successfully Updated")
                .build();
	}
	
	

}
