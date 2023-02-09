package dh.pi.accountservice.entity;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Card {

    private Integer id;
    private Integer accountId;
    private String type;
    private String cardNumber;
    private String owner;
    private String securityNumber;
    private Timestamp expirationDate;
    private Integer balance;
    private String lastNumbers;
}
