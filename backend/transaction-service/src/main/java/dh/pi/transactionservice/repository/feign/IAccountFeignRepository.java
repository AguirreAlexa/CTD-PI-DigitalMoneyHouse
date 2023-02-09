package dh.pi.transactionservice.repository.feign;

import dh.pi.transactionservice.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "account-service")
public interface IAccountFeignRepository {

    @GetMapping("/account/{id}")
    Account getAccountById(@PathVariable(value = "id") Integer id);

    @PutMapping("/account/{id}")
    Account udpate(@RequestBody Account account, @PathVariable(value = "id") Integer id);


}
