package Data.Factory;

import java.util.List;

public class Account {

    Integer id;
    Integer userId;
    Integer balance;
    List<Cards> cardsList;
    List<Transactions> transactionsList;
    User user;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
}
