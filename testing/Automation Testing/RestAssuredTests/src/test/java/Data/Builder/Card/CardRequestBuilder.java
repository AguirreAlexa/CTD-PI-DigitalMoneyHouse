package Data.Builder.Card;


import Data.Builder.User.RegisterUserRequestBuilder;
import Data.Model.Card.CardRequest;
import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CardRequestBuilder {

    CardRequest cardRequest;

    private static final Integer DEFAULT_ACCOUNTID=2;
    private static final String DEFAULT_CARDNUMBER= "4541587598754869";
    private static final String DEFAULT_TYPE = "Debit";
    private static final String DEFAULT_OWNER = "Lionel Scaloni";
    private static final String DEFAULT_SECURITYNUMBER = "123";
    private static final Double DEFAULT_BALANCE = 15000.0;
    private static final String DEFAULT_EXPIRATIONDATE= "2022-12-25";
    private static final String DEFAULT_LASTNUMBERS = "4869";

    private String generateAutomationTesterName(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SS");
        String dateToStr = dateFormat.format(date);
        String tester = "automation_tester"+dateToStr;
        return tester;
    }

    private CardRequestBuilder(){cardRequest = new CardRequest();}

    public static CardRequestBuilder card(){return new CardRequestBuilder();}

    public CardRequestBuilder withAccountId (){
        this.cardRequest.setAccountId(DEFAULT_ACCOUNTID);
        return this;
    }

    public CardRequestBuilder withCardNumber(){
        this.cardRequest.setCardNumber(DEFAULT_CARDNUMBER);
        return this;
    }

    public CardRequestBuilder withType(){
        this.cardRequest.setType(DEFAULT_TYPE);
        return this;
    }

    public CardRequestBuilder withOwner(){
        this.cardRequest.setOwner(DEFAULT_OWNER);
        return this;
    }

    public CardRequestBuilder withSecurityNumber(){
        this.cardRequest.setSecurityNumber(DEFAULT_SECURITYNUMBER);
        return this;
    }

    public CardRequestBuilder withBalance(){
        this.cardRequest.setBalance(DEFAULT_BALANCE);
        return this;
    }

    public CardRequestBuilder withExpirationDate(){
        this.cardRequest.setExpirationDate(DEFAULT_EXPIRATIONDATE);
        return this;
    }

    public CardRequestBuilder withLastNumbers(){
        this.cardRequest.setLastNumbers(DEFAULT_LASTNUMBERS);
        return this;
    }


    public CardRequestBuilder withEmptyAccountId (){
        this.cardRequest.setAccountId(Integer.parseInt(StringUtils.EMPTY));
        return this;
    }

    public CardRequestBuilder withEmptyCardNumber(){
        this.cardRequest.setCardNumber(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withEmptyType(){
        this.cardRequest.setType(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withEmptyOwner(){
        this.cardRequest.setOwner(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withEmptySecurityNumber(){
        this.cardRequest.setSecurityNumber(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withEmptyBalance(){
        this.cardRequest.setBalance(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withEmptyExpirationDate(){
        this.cardRequest.setExpirationDate(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withEmptyLastNumbers(){
        this.cardRequest.setLastNumbers(StringUtils.EMPTY);
        return this;
    }

    public CardRequestBuilder withNullAccountId (){
        this.cardRequest.setAccountId(null);
        return this;
    }

    public CardRequestBuilder withNullCardNumber(){
        this.cardRequest.setCardNumber(null);
        return this;
    }

    public CardRequestBuilder withNullType(){
        this.cardRequest.setType(null);
        return this;
    }

    public CardRequestBuilder withNullOwner(){
        this.cardRequest.setOwner(null);
        return this;
    }

    public CardRequestBuilder withNullSecurityNumber(){
        this.cardRequest.setSecurityNumber(null);
        return this;
    }

    public CardRequestBuilder withNullBalance(){
        this.cardRequest.setBalance(null);
        return this;
    }

    public CardRequestBuilder withNullExpirationDate(){
        this.cardRequest.setExpirationDate(null);
        return this;
    }

    public CardRequestBuilder withNullLastNumbers(){
        this.cardRequest.setLastNumbers(null);
        return this;
    }

    public CardRequestBuilder withRandomCardNumber(){
        this.cardRequest.setCardNumber(generateAutomationTesterName());
        return this;
    }

    public CardRequestBuilder withRandomOwner(){
        this.cardRequest.setOwner(generateAutomationTesterName());
        return this;
    }

    public CardRequestBuilder withRandomSecurityNumber(){
        this.cardRequest.setSecurityNumber(generateAutomationTesterName());
        return this;
    }

    public CardRequestBuilder withRandomLastNumber(){
        this.cardRequest.setLastNumbers(generateAutomationTesterName());
        return this;
    }

    public CardRequestBuilder withRandomExpirationDate(){
        this.cardRequest.setExpirationDate("2023-01-08");
        return this;
    }

    public CardRequestBuilder withRandomAccountId(){
        this.cardRequest.setAccountId(4);
        return this;
    }

    public CardRequestBuilder withRandomType(){
        this.cardRequest.setType(generateAutomationTesterName());
        return this;
    }

    public CardRequestBuilder withRandomBalance(){
        this.cardRequest.setBalance(9999.99);
        return this;
    }

    public static CardRequest defaultCardRequest(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }
    public static CardRequest defaultCardRequestWithoutAccountId(){
        return card().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutCardNumber(){
        return card().withAccountId().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutOwner(){
        return card().withAccountId().withCardNumber().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutSecurityNumber(){
        return card().withAccountId().withCardNumber().withOwner().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutBalance(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutExpirationDate(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutType(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithoutLastNumbers(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().build();
    }

    public static CardRequest nullCardRequest(){
        return card().withNullAccountId().withNullCardNumber().withNullOwner().withNullType().withNullSecurityNumber()
                .withNullBalance().withNullExpirationDate().withNullLastNumbers().build();
    }

    public static CardRequest emptyCardRequest(){
        return card().withEmptyAccountId().withEmptyCardNumber().withEmptyOwner().withEmptyType().withEmptySecurityNumber()
                .withEmptyBalance().withEmptyExpirationDate().withEmptyLastNumbers().build();
    }

    public static CardRequest defaultCardRequestwithEmptyAccountid(){
        return card().withEmptyAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithEmptyCardNumber(){
        return card().withAccountId().withEmptyCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }
    public static CardRequest defaultCardRequestWithEmptyOwner(){
        return card().withAccountId().withCardNumber().withEmptyOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithEmptySecurityNumber(){
        return card().withAccountId().withCardNumber().withOwner().withEmptySecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithEmptyBalance(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withEmptyBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithEmptyExpirationDate(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withEmptyExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithEmptyType(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withEmptyType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithLastNumbers(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withEmptyLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullAccountId(){
        return card().withNullAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullCardNumber(){
        return card().withAccountId().withNullCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullOwner(){
        return card().withAccountId().withCardNumber().withNullOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullSecurityNumber(){
        return card().withAccountId().withCardNumber().withOwner().withNullSecurityNumber().withBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullBalance(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withNullBalance()
                .withExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullExpirationDate(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withNullExpirationDate().withType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullType(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withEmptyType().withLastNumbers().build();
    }

    public static CardRequest defaultCardRequestWithNullLastNumbers(){
        return card().withAccountId().withCardNumber().withOwner().withSecurityNumber().withBalance()
                .withExpirationDate().withType().withNullLastNumbers().build();
    }

    public static CardRequest randomCard(){
        return card().withRandomAccountId()
                .withRandomCardNumber()
                .withRandomOwner()
                .withRandomSecurityNumber()
                .withRandomType()
                .withRandomExpirationDate()
                .withRandomBalance()
                .withRandomLastNumber()
                .build();
    }

    private CardRequest build() { return cardRequest;}


}
