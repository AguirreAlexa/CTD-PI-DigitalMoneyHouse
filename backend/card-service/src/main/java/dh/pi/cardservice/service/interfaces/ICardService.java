package dh.pi.cardservice.service.interfaces;

import dh.pi.cardservice.entity.Account;
import dh.pi.cardservice.exception.BadRequestException;
import dh.pi.cardservice.exception.ResourceNotFoundException;
import dh.pi.cardservice.entity.Card;

import java.util.List;
import java.util.Optional;

public interface ICardService {
    List<Card> getAll(Integer accountId, String type);
    List<Card> getAllByAccountId(Integer accountId);
    List<Card> getAllByType(String type);

    Optional<Card> getById(Integer id) throws ResourceNotFoundException;

    Card create(Card card) throws BadRequestException;
    Card update(Card card, Integer id) throws ResourceNotFoundException, BadRequestException ;

    void delete(Integer id);

    List<Card> getAllByOwner(String owner) throws ResourceNotFoundException, BadRequestException;
    List<Card> getOnlyLastNumbers(Integer accountId) throws ResourceNotFoundException, BadRequestException;
    Card getCardByLastNumbers(String lastNumbers) throws ResourceNotFoundException, BadRequestException;

    Optional<Account> getAccountById(Integer id);
}
