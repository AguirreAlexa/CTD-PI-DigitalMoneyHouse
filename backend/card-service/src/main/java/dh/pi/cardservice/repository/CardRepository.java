package dh.pi.cardservice.repository;

import dh.pi.cardservice.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Integer> {
    List<Card> findAllByAccountId(Integer accountId);
    List<Card> findAllByType(String type);

    List<Card> findAllByAccountIdOrType(Integer accountId, String type);
    List<Card> findAllByAccountIdAndType(Integer accountId, String type);

    List<Card> findAllByOwner(String owner);
    Card findAllByLastNumbers(String lastNumbers);
}
