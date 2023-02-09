package dh.pi.accountservice.repository.feign;

import dh.pi.accountservice.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "user-service")
public interface IUserFeignRepository {

    @GetMapping("/user/{id}/getCvuAlias")
    User getCvcAndAliasByUserId(@PathVariable(value = "id") Integer id);

    @PutMapping("/user/update/{id}")
    User editAlias(@PathVariable(value = "id") Integer id, @RequestBody User user);
}
