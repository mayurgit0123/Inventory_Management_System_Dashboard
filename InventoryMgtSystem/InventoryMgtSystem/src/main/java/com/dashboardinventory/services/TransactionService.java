package com.dashboardinventory.services;

import com.dashboardinventory.dtos.Response;
import com.dashboardinventory.dtos.TransactionRequest;
import com.dashboardinventory.enums.TransactionStatus;

public interface TransactionService {
	
	Response purchase(TransactionRequest transactionRequest);

    Response sell(TransactionRequest transactionRequest);

    Response returnToSupplier(TransactionRequest transactionRequest);

    Response getAllTransactions(int page, int size, String filter);

    Response getAllTransactionById(Long id);

    Response getAllTransactionByMonthAndYear(int month, int year);

    Response updateTransactionStatus(Long transactionId, TransactionStatus status);

}
