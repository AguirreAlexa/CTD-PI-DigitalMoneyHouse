package dh.pi.accountservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
public class Transaction {

    private Integer id;
    private Integer accountOriginId;
    private Integer accountDestinyId;
    private Double amount;
    private Timestamp date;
    private String detail;
    private String type;

}
