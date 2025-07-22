package com.dashboardinventory.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.dashboardinventory.models.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>, JpaSpecificationExecutor<Transaction> {

}
