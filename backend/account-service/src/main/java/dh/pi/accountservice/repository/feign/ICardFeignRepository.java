package dh.pi.accountservice.repository.feign;

import dh.pi.accountservice.entity.Card;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "card-service")
public interface ICardFeignRepository {

    @GetMapping("/cards/accountId/{accountId}")
    List<Card> findAllByAccountId(@PathVariable(name = "accountId") Integer accountId);

    @PutMapping("/cards/{id}")
    Card updateCard(@PathVariable(name = "id") Integer id, @RequestBody Card card);
}
