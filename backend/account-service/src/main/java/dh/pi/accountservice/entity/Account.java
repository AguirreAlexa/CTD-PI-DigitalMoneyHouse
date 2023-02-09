package dh.pi.accountservice.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@ApiModel(description = "Detalles de cuenta")
@Entity
@Table(name = "accounts")
@NoArgsConstructor
@Data
@Access(AccessType.FIELD)
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El campo userId no puede estar vacio")
    private Integer userId;

    @NotBlank(message = "El campo balance no puede estar vacio")
    private int balance;

    @Transient
    @NotBlank(message = "El campo transactions no puede estar vacio")
    private List<Transaction> transactions;

    @Transient
    @NotBlank(message = "El campo cards no puede estar vacio")
    private List<Card> cards;

    @Transient
    @NotBlank(message = "El campo user no puede estar vacio")
    private User user;

    public Account(Integer userId, int balance, List<Transaction> transactions, List<Card> cards, User user) {
        this.userId = userId;
        this.balance = balance;
        this.transactions = transactions;
        this.cards = cards;
        this.user = user;
    }
    public Account(Integer userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
