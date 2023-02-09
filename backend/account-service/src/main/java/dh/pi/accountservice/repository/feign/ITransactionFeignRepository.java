package dh.pi.accountservice.repository.feign;

import dh.pi.accountservice.entity.Transaction;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@FeignClient(name = "transaction-service")
public interface ITransactionFeignRepository {

    @GetMapping("/transaction/{transactionId}/{accountId}")
    Optional<Transaction> getByAccountAndTransactionId
            (@PathVariable(name = "accountId") Integer accountId, @PathVariable(name = "transactionId") Integer transactionId);

    @GetMapping("/transaction/accountId/{accountId}")
    List<Transaction> getAllByAccountId(@PathVariable(name = "accountId") Integer accountId);

    @GetMapping("/transaction/accountId/lastTen/{accountId}")
    List<Transaction> getLastTenByAccountId(@PathVariable(name = "accountId") Integer accountId);

    @PostMapping("/transaction/")
    Transaction createTransaction(@RequestBody Transaction transaction);
}
