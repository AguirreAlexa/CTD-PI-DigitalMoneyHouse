package dh.pi.cardservice.repository.feign;

import dh.pi.cardservice.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "account-service")
public interface IAccountFeignRepository {

    @GetMapping("/account/{id}")
    Account getAccountById(@PathVariable(value = "id") Integer id);

}
