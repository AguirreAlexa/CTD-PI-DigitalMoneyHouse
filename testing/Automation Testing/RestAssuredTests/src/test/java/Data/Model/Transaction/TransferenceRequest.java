package Data.Model.Transaction;

public class TransferenceRequest{
	private int amount;
	private int cardId;

	public void setAmount(Integer amount){
		this.amount = amount;
	}

	public int getAmount(){
		return amount;
	}

	public void setCardId(Integer cardId){
		this.cardId = cardId;
	}

	public int getCardId(){
		return cardId;
	}

	@Override
 	public String toString(){
		return 
			"TransferenceRequest{" + 
			"amount = '" + amount + '\'' + 
			",cardId = '" + cardId + '\'' + 
			"}";
		}
}
