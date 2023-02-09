package dh.pi.cardservice.entity;

import lombok.Data;

@Data
public class Account {

    private Integer id;
    private Integer userId;
    private int balance;

    public Account(Integer userId, int balance) {
        this.userId = userId;
        this.balance = balance;
    }
}
