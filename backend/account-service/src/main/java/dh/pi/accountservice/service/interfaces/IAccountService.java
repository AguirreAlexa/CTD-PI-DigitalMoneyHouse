package dh.pi.accountservice.service.interfaces;

import dh.pi.accountservice.entity.Account;
import dh.pi.accountservice.entity.Card;
import dh.pi.accountservice.entity.Transaction;
import dh.pi.accountservice.entity.User;
import dh.pi.accountservice.exception.BadRequestException;
import dh.pi.accountservice.exception.ResourceNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface IAccountService {
    Optional<Account> getAccountDetailsById(Integer id) throws Exception;
    Optional<List<Card>> getAllCardsByAccountId(Integer id) throws ResourceNotFoundException;
    Optional<User> editAlias(User user, Integer id)  throws ResourceNotFoundException, BadRequestException;

    Optional<Transaction> getByAccountAndTransactionId (Integer accountId, Integer transactionId) throws BadRequestException, ResourceNotFoundException;
    Optional<List<Transaction>> getAllByAccountId(Integer accountId) throws BadRequestException, ResourceNotFoundException;

    Optional<List<Transaction>> getLastTenByAccountId(Integer accountId) throws BadRequestException, ResourceNotFoundException;

    Optional<Account> createAccount(Integer userId);
    Optional<Account> getAccountByUserId(Integer id) throws Exception;
}
