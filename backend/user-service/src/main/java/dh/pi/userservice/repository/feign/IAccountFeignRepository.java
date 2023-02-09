package dh.pi.userservice.repository.feign;

import dh.pi.userservice.entity.Account;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("account-service")
public interface IAccountFeignRepository {

    @PostMapping("/account/create")
    public ResponseEntity<Account> createAccount(@RequestBody Integer id);

    @GetMapping("/account/userId/{userId}")
    public ResponseEntity<Account> getAccountDetailsByUserId(@PathVariable Integer userId);
}
