package dh.pi.accountservice.controller;

import dh.pi.accountservice.entity.*;
import dh.pi.accountservice.exception.BadRequestException;
import dh.pi.accountservice.exception.ResourceNotFoundException;
import dh.pi.accountservice.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Account> getAccountDetailsById(@PathVariable Integer id) throws Exception{
        Account account = accountService.getAccountDetailsById(id).get();
        if(account != null){
            return new ResponseEntity(account, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cards")
    public ResponseEntity<List<Card>> getAllCardsByAccountId(@RequestParam Integer id) throws ResourceNotFoundException {
        return new ResponseEntity(accountService.getAllCardsByAccountId(id), HttpStatus.OK);
    }

    @GetMapping("/{accountId}/activity/{transactionId}")
    public ResponseEntity<Transaction> getByAccountAndTransactionId(@PathVariable(name = "accountId") Integer accountId, @PathVariable(name = "transactionId") Integer transactionId) throws Exception {
        try {
            Optional<Account> account = accountService.getAccountById(accountId);
            if (account.isEmpty()) {
                return new ResponseEntity("Cuenta no encontrada", HttpStatus.NOT_FOUND);
            }

            Optional<Transaction> optionalTransaction = accountService.getByAccountAndTransactionId(accountId, transactionId);

            if (optionalTransaction.isPresent()) {
                return ResponseEntity.ok().body(optionalTransaction.get());
            }
        }catch(Exception e){
            return new ResponseEntity(e, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity("Transacción no encontrada", HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{accountId}/activity")
    public ResponseEntity<List<Transaction>> getAllByAccountId(@PathVariable(name = "accountId") Integer accountId) throws BadRequestException, ResourceNotFoundException {
        try {
            Optional<Account> account = accountService.getAccountById(accountId);
            if (account.isEmpty()) {
                return new ResponseEntity("Cuenta no encontrada", HttpStatus.NOT_FOUND);
            }

            Optional<List<Transaction>> optionalTransactions = accountService.getAllByAccountId(accountId);

            if (optionalTransactions.isPresent()) {
                return ResponseEntity.ok().body(optionalTransactions.get());
            }

        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{accountId}/transferences")
    public ResponseEntity<List<Transaction>> getLastTenByAccountId(@PathVariable(name = "accountId") Integer accountId) throws BadRequestException, ResourceNotFoundException {
        Optional<List<Transaction>> optionalTransactions = accountService.getLastTenByAccountId(accountId);

        if(optionalTransactions.isPresent()){
            return ResponseEntity.ok().body(optionalTransactions.get());
        }

        return (ResponseEntity<List<Transaction>>) ResponseEntity.notFound();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<User> editAlias(@RequestBody User user, @PathVariable Integer id) throws ResourceNotFoundException, BadRequestException {
        Optional<User> userFound = accountService.editAlias(user, id);
        if(userFound.isPresent()){
            return new ResponseEntity(userFound, HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<Account> createAccount(@RequestBody Integer id){
        return new ResponseEntity(accountService.createAccount(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@RequestBody Account account, @PathVariable Integer id){
        return new ResponseEntity(accountService.updateAccount(account), HttpStatus.OK);
    }

    @PostMapping("/{id}/transferenceAccount")
    public ResponseEntity<Account> createTransaction(@PathVariable Integer id, @RequestBody Transference transference){
        try {
            Optional<List<Card>> cards = accountService.getAllCardsByAccountId(id);
            Optional<Account> account = accountService.getAccountById(id);
            if(cards.isPresent() && account.isPresent()){
                List<Card> cardList = cards.get();
                Card selectedCard = null;
                for(Card card: cardList){
                    if(card.getId().equals(transference.getCardId())){
                        selectedCard = card;
                    }
                }
                if(selectedCard != null){
                    selectedCard.setBalance((int) (selectedCard.getBalance() - transference.getAmount()));
                    account.get().setBalance((int) (account.get().getBalance() + transference.getAmount()));
                }else{
                    return ResponseEntity.badRequest().build();
                }
                Card cardSave = accountService.updateCard(selectedCard);
                Account accountSave = accountService.updateAccount(account.get());
                return new ResponseEntity(accountSave, HttpStatus.OK);
            }
            return ResponseEntity.badRequest().build();
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{id}/transferences")
    public ResponseEntity<Transaction> createTransaction(@PathVariable Integer id, @RequestBody Transaction transaction){
        try {
            Optional<Account> accountOrigin = accountService.getAccountById(transaction.getAccountOriginId());
            if(accountOrigin.isEmpty()){
                return new ResponseEntity( "No existe la cuenta de origen", HttpStatus.BAD_REQUEST);
            }
            Optional<Account> accountDestiny = accountService.getAccountById(transaction.getAccountDestinyId());
            if(accountDestiny.isEmpty()){
                return new ResponseEntity( "No existe la cuenta destino", HttpStatus.BAD_REQUEST);
            }
            if(accountOrigin.get().getBalance() < transaction.getAmount()){
                return new ResponseEntity( "Saldo insuficiente", HttpStatus.GONE);
            }

            Transaction transactionSucesss = accountService.createTransaction(transaction);
            return new ResponseEntity(transactionSucesss, HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/userId/{userId}")
    public ResponseEntity<Account> getAccountDetailsByUserId(@PathVariable Integer userId) throws Exception {
        Optional<Account> account = accountService.getAccountByUserId(userId);
        if(account.isPresent()){
            return new ResponseEntity(account,HttpStatus.OK);
        }
        return ResponseEntity.badRequest().build();
    }

}
