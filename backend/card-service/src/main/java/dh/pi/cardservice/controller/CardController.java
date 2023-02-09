package dh.pi.cardservice.controller;

import dh.pi.cardservice.entity.Account;
import dh.pi.cardservice.exception.BadRequestException;
import dh.pi.cardservice.exception.ResourceNotFoundException;
import dh.pi.cardservice.entity.Card;
import dh.pi.cardservice.service.interfaces.ICardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cards")
public class CardController {
    private ICardService cardService;

    @Autowired
    public CardController(ICardService cardService) {
        this.cardService = cardService;
    }

    @GetMapping
    public ResponseEntity<List<Card>> getAllCards(@RequestParam(required = false) Integer accountId, @RequestParam(required = false) String type){
        return ResponseEntity.ok().body(cardService.getAll(accountId, type));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Card> getById(@PathVariable Integer id) throws ResourceNotFoundException{
        Optional<Card> optionalCard = cardService.getById(id);
        if(optionalCard.isPresent()){
            return ResponseEntity.ok().body(optionalCard.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/accountId/{accountId}")
    public ResponseEntity<List<Card>> getByAccountId(@PathVariable(name="accountId") Integer accountId) throws ResourceNotFoundException{
        List<Card> optionalCard = cardService.getAllByAccountId(accountId);
        if(optionalCard != null){
            return ResponseEntity.ok().body(optionalCard);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Card> create(@RequestBody Card card) throws BadRequestException {
        try {
            Optional<Account> account = cardService.getAccountById(card.getAccountId());
            if(account.isEmpty()){
                return new ResponseEntity("No se encuentra la cuenta asociada", HttpStatus.BAD_REQUEST);
            }
            Timestamp dateToday = new Timestamp(new Date().getTime());
            if(card.getExpirationDate().before(dateToday)){
                return new ResponseEntity("La fecha de expiración debe ser posterior a la fecha actual", HttpStatus.BAD_REQUEST);
            }

            return ResponseEntity.ok(cardService.create(card));
        }catch (Exception e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Card> update(@RequestBody Card card, @PathVariable Integer id) throws ResourceNotFoundException, BadRequestException{
        Card cardUpdated = cardService.update(card, id);
        if(cardUpdated != null) {
            return ResponseEntity.ok().body(cardUpdated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Integer id){
        try {
            cardService.delete(id);
            return ResponseEntity.noContent().build();
        }catch (Exception e){
            return new ResponseEntity("No existe la tarjeta a eliminar", HttpStatus.NO_CONTENT);
        }
    }
}
