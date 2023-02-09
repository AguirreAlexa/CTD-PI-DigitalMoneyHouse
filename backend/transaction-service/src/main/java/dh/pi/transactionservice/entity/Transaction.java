package dh.pi.transactionservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;


@Data
@Entity(name="transactions")
@Table(name="transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer accountOriginId;
    private Integer accountDestinyId;
    private Double amount;
    private Timestamp date;
    private String detail;
    private String type;


}
