package Data.Builder.Transaction;

import Data.Builder.User.RegisterUserRequestBuilder;
import Data.Model.Transaction.TransferenceRequest;
import Data.Model.User.RegisterUserRequest;

public class TransferenceRequestBuilder {

    private TransferenceRequest transferenceRequest;

    private static final Integer DEFAULT_AMOUNT = 1;
    private static final Integer DEFAULT_CARDID = 5;

    TransferenceRequestBuilder (){transferenceRequest = new TransferenceRequest();}

    public static TransferenceRequestBuilder newTransference(){
        return new TransferenceRequestBuilder();
    }

    public TransferenceRequest build(){
        return transferenceRequest;
    }

    public TransferenceRequestBuilder withDefaultAmount(){
        this.transferenceRequest.setAmount(DEFAULT_AMOUNT);
        return this;
    }

    public TransferenceRequestBuilder withDefaultCardId(){
        this.transferenceRequest.setCardId(DEFAULT_CARDID);
        return this;
    }

    public TransferenceRequestBuilder withNullAmount(){
        this.transferenceRequest.setAmount(null);
        return this;
    }

    public TransferenceRequestBuilder withNullCardId(){
        this.transferenceRequest.setCardId(null);
        return this;
    }

    public TransferenceRequest defaultTransference(){
        return newTransference()
                .withDefaultAmount()
                .withDefaultCardId()
                .build();
    }

    public TransferenceRequest nullTransference(){
        return newTransference()
                .withNullAmount()
                .withNullCardId()
                .build();
    }

    public TransferenceRequest transferenceWithNullAmount(){
        return newTransference()
                .withNullAmount()
                .withDefaultCardId()
                .build();
    }

    public TransferenceRequest transferenceWithNullCardId(){
        return newTransference()
                .withDefaultAmount()
                .withNullCardId()
                .build();
    }

    public TransferenceRequest nullTrasference(){
        return newTransference()
                .withNullAmount()
                .withNullCardId()
                .build();
    }








}
