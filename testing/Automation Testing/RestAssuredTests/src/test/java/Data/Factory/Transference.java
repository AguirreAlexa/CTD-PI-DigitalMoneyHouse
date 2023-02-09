package Data.Factory;

import java.time.LocalDate;

public class Transference {
    private Double amount;
    private Integer cardId;
    private LocalDate date;
    private Integer destinyAccountId;


    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getCardId() {
        return cardId;
    }

    public void setCardId(Integer cardId) {
        this.cardId = cardId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getDestinyAccountId() {
        return destinyAccountId;
    }

    public void setDestinyAccountId(Integer destinyAccountId) {
        this.destinyAccountId = destinyAccountId;
    }
}
