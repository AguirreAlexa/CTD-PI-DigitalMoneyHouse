package dh.pi.transactionservice.service.interfaces;

import dh.pi.transactionservice.entity.Account;
import dh.pi.transactionservice.exception.BadRequestException;
import dh.pi.transactionservice.exception.ResourceNotFoundException;
import dh.pi.transactionservice.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface ITransactionService {
    List<Transaction> getAll();
    Optional<Transaction> getById(Integer id);
    Optional<Transaction> getByAccountAndTransactionId(Integer accountId, Integer transactionId) throws BadRequestException, ResourceNotFoundException;
    Optional<List<Transaction>> getAllByAccountId(Integer id) throws BadRequestException, ResourceNotFoundException;
    Optional<List<Transaction>> getLastTenByAccountId(Integer id) throws BadRequestException, ResourceNotFoundException;
    Transaction create(Transaction transaction);
    Transaction update(Transaction transaction);
    void delete(Integer id);
    Optional<Account> getAccountById(Integer accountOriginId);
    void updateAccount(Account account);
}
