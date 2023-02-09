package Data.Factory;

import java.sql.Timestamp;
import java.util.Date;

public class Transactions {


    Integer id;
    Integer accountOriginId;
     Integer accountDestinyId;
     Double amount;
    Timestamp date;
     String detail;
     String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAccountOriginId() {
        return accountOriginId;
    }

    public void setAccountOriginId(Integer accountOriginId) {
        this.accountOriginId = accountOriginId;
    }

    public Integer getAccountDestinyId() {
        return accountDestinyId;
    }

    public void setAccountDestinyId(Integer accountDestinyId) {
        this.accountDestinyId = accountDestinyId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

