package Data.Builder.Transaction;

import Data.Model.Transaction.TransactionRequest;

public class TransactionRequestBuilder {

    TransactionRequest transactionRequest;

    private static final String DEFAULT_DATE = "2022-12-26";
    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer DEFAULT_ORIGINID = 2;
    private static final String DEFAULT_DETAIL = "Alquiler";
    private static final String DEFAULT_TYPE = "Debito";
    private static final Integer  DEFAULT_DESTINYID = 1;

    TransactionRequestBuilder(){transactionRequest = new TransactionRequest();}

    public static TransactionRequestBuilder newTransaction(){
        return new TransactionRequestBuilder();
    }

    public TransactionRequest build(){return transactionRequest;}

    public TransactionRequestBuilder withDefaultDate(){
        this.transactionRequest.setDate(DEFAULT_DATE);
        return this;
    }

    public TransactionRequestBuilder withDefaultAmount(){
        this.transactionRequest.setAmount(DEFAULT_AMOUNT);
        return this;
    }

    public TransactionRequestBuilder withDefaultOriginId(){
        this.transactionRequest.setAccountOriginId(DEFAULT_ORIGINID);
        return this;
    }

    public TransactionRequestBuilder withDefaultDetail(){
        this.transactionRequest.setDetail(DEFAULT_DETAIL);
        return this;
    }

    public TransactionRequestBuilder withDefaultType(){
        this.transactionRequest.setDetail(DEFAULT_TYPE);
        return this;
    }

    public TransactionRequestBuilder withDefaultDestinyId(){
        this.transactionRequest.setAccountDestinyId(DEFAULT_DESTINYID);
        return this;
    }

    public TransactionRequestBuilder withNullDate(){
        this.transactionRequest.setDate(null);
        return this;
    }

    public TransactionRequestBuilder withNullAmount(){
        this.transactionRequest.setAmount(null);
        return this;
    }

    public TransactionRequestBuilder withNullOriginId(){
        this.transactionRequest.setAccountOriginId(null);
        return this;
    }

    public TransactionRequestBuilder withNullDetail(){
        this.transactionRequest.setDetail(null);
        return this;
    }

    public TransactionRequestBuilder withNullType(){
        this.transactionRequest.setDetail(null);
        return this;
    }

    public TransactionRequestBuilder withNullDestinyId(){
        this.transactionRequest.setAccountDestinyId(null);
        return this;
    }

    public static TransactionRequest defaultTransaction(){
        return newTransaction()
                .withDefaultDetail()
                .withDefaultType()
                .withDefaultDate()
                .withDefaultDestinyId()
                .withDefaultOriginId()
                .withDefaultAmount()
                .build();
    }

    public static TransactionRequest defaultTransactionWithNullDetail(){
        return newTransaction()
                .withNullDetail()
                .withDefaultType()
                .withDefaultDate()
                .withDefaultDestinyId()
                .withDefaultOriginId()
                .withDefaultAmount()
                .build();
    }

    public static TransactionRequest defaultTransactionWithNullType(){
        return newTransaction()
                .withDefaultDetail()
                .withNullType()
                .withDefaultDate()
                .withDefaultDestinyId()
                .withDefaultOriginId()
                .withDefaultAmount()
                .build();
    }

    public static TransactionRequest defaultTransactionWithNullDate(){
        return newTransaction()
                .withDefaultDetail()
                .withDefaultType()
                .withNullDate()
                .withDefaultDestinyId()
                .withDefaultOriginId()
                .withDefaultAmount()
                .build();
    }

    public static TransactionRequest defaultTransactionNullDestiny(){
        return newTransaction()
                .withDefaultDetail()
                .withDefaultType()
                .withDefaultDate()
                .withNullDestinyId()
                .withDefaultOriginId()
                .withDefaultAmount()
                .build();
    }

    public static TransactionRequest defaultTransactionNullOrigin(){
        return newTransaction()
                .withDefaultDetail()
                .withDefaultType()
                .withDefaultDate()
                .withDefaultDestinyId()
                .withNullOriginId()
                .withDefaultAmount()
                .build();
    }

    public static TransactionRequest defaultTransactionNullAmount(){
        return newTransaction()
                .withDefaultDetail()
                .withDefaultType()
                .withDefaultDate()
                .withDefaultDestinyId()
                .withDefaultOriginId()
                .withNullAmount()
                .build();
    }

    public static TransactionRequest nullTransaction(){
        return newTransaction()
                .withNullAmount()
                .withNullDestinyId()
                .withNullOriginId()
                .withNullDate()
                .withNullType()
                .withNullDetail()
                .build();
    }





}
