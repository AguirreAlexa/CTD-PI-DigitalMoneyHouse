package dh.pi.accountservice.service;

import dh.pi.accountservice.entity.Account;
import dh.pi.accountservice.entity.Card;
import dh.pi.accountservice.entity.Transaction;
import dh.pi.accountservice.entity.User;
import dh.pi.accountservice.exception.BadRequestException;
import dh.pi.accountservice.exception.ResourceNotFoundException;
import dh.pi.accountservice.repository.AccountRepository;
import dh.pi.accountservice.repository.feign.ITransactionFeignRepository;
import dh.pi.accountservice.repository.feign.ICardFeignRepository;
import dh.pi.accountservice.repository.feign.IUserFeignRepository;
import dh.pi.accountservice.service.interfaces.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountService implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ICardFeignRepository cardFeignRepository;

    @Autowired
    private ITransactionFeignRepository transactionFeignRepository;

    @Autowired
    private IUserFeignRepository userFeignRepository;

    public AccountService(AccountRepository accountRepository, ICardFeignRepository cardFeignRepository, ITransactionFeignRepository transactionFeignRepository, IUserFeignRepository userFeignRepository) {
        this.accountRepository = accountRepository;
        this.cardFeignRepository = cardFeignRepository;
        this.transactionFeignRepository = transactionFeignRepository;
        this.userFeignRepository = userFeignRepository;
    }


    @Override
    public Optional<Account> getAccountDetailsById(Integer id) throws Exception{

        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            List<Card> cards = cardFeignRepository.findAllByAccountId(id);
            account.get().setCards(cards);

            User userFeign = userFeignRepository.getCvcAndAliasByUserId(account.get().getUserId());
            User user = new User(account.get().getUserId(), userFeign.getCvu(), userFeign.getAlias());
            account.get().setUser(user);
            return account;
        }else {
            throw new BadRequestException("No se encontro cuenta con este id");
        }

    }

    @Override
    public Optional<List<Card>> getAllCardsByAccountId(Integer id)throws ResourceNotFoundException {

        if(accountRepository.existsById(id) == false){
            throw new ResourceNotFoundException("No se encontro cuenta con este id");
        }
        List<Card> cards = cardFeignRepository.findAllByAccountId(id);
        return Optional.of(cards);
    }

    @Override
    public Optional<User> editAlias(User user, Integer id)  throws ResourceNotFoundException, BadRequestException {
        if(user.getAlias() != null && validarVacio(user.getAlias())){
            throw new BadRequestException("No se puede actualizar por el alias vacio");
        }
        if(accountRepository.existsById(id) == false){
            throw new ResourceNotFoundException("No se encontro cuenta con este id");
        }
        User userUpdate = userFeignRepository.editAlias(id, user);
        return Optional.of(userUpdate);

    }

    @Override
    public Optional<Transaction> getByAccountAndTransactionId(Integer accountId, Integer transactionId) throws BadRequestException, ResourceNotFoundException{
        Optional<Transaction> transaction = transactionFeignRepository.getByAccountAndTransactionId(accountId, transactionId);

        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("No se encontro transferencia con este id");
        }

        return transaction;
    }

    @Override
    public Optional<List<Transaction>> getAllByAccountId(Integer accountId) throws BadRequestException, ResourceNotFoundException {
        List<Transaction> transactions = transactionFeignRepository.getAllByAccountId(accountId);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron transferencias con este id de cuenta");
        }

        return Optional.of(transactions);
    }

    @Override
    public Optional<List<Transaction>> getLastTenByAccountId(Integer accountId) throws BadRequestException, ResourceNotFoundException {
        List<Transaction> transactions = transactionFeignRepository.getLastTenByAccountId(accountId);

        if (transactions.isEmpty()) {
            throw new ResourceNotFoundException("No se encontraron transferencias con este id de cuenta");
        }

        return Optional.of(transactions);
    }

    @Override
    public Optional<Account> createAccount(Integer userId){
        Account newAccount = new Account(userId, 0);
        accountRepository.save(newAccount);
        return Optional.of(newAccount);
    }

    public Card updateCard(Card card){
        try{
            Card card1 = cardFeignRepository.updateCard(card.getId(), card);
            return card1;
        }catch (Exception e){
            return null;
        }
    }

    public Transaction createTransaction(Transaction transaction) throws ResourceNotFoundException {
        try{
            Transaction transaction1 = transactionFeignRepository.createTransaction(transaction);
            return transaction1;
        }catch (Exception e){
            throw new ResourceNotFoundException("No se pudo crear la transferencia");
        }
    }

    public Account updateAccount(Account account){
        return accountRepository.save(account);
    }

    public Optional<Account> getAccountById(Integer id) throws Exception{
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            return account;
        }else{
            throw  new BadRequestException("No se encontro account con ese id");
        }
    }

    public Optional<Account> getAccountByUserId(Integer id) throws Exception{
        Optional<Account> accountUser = accountRepository.findByUserId(id);
        if(accountUser.isEmpty()){
            throw new ResourceNotFoundException("No se encontro account con ese user_id");
        }else{
            return accountUser;
        }
    }

    //
    public boolean validarVacio(String cadena){
        return cadena.trim().isEmpty();
    }
}
