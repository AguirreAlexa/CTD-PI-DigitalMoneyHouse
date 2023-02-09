package Data.Model.Card;

public class CardRequest{
	private String owner;
	private String securityNumber;
	private int accountId;
	private Object balance;
	private String lastNumbers;
	private String type;
	private String cardNumber;
	private String expirationDate;

	public void setOwner(String owner){
		this.owner = owner;
	}

	public String getOwner(){
		return owner;
	}

	public void setSecurityNumber(String securityNumber){
		this.securityNumber = securityNumber;
	}

	public String getSecurityNumber(){
		return securityNumber;
	}

	public void setAccountId(Integer accountId){
		this.accountId = accountId;
	}

	public int getAccountId(){
		return accountId;
	}

	public void setBalance(Object balance){
		this.balance = balance;
	}

	public Object getBalance(){
		return balance;
	}

	public void setLastNumbers(String lastNumbers){
		this.lastNumbers = lastNumbers;
	}

	public String getLastNumbers(){
		return lastNumbers;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setCardNumber(String cardNumber){
		this.cardNumber = cardNumber;
	}

	public String getCardNumber(){
		return cardNumber;
	}

	public void setExpirationDate(String expirationDate){
		this.expirationDate = expirationDate;
	}

	public String getExpirationDate(){
		return expirationDate;
	}

	@Override
 	public String toString(){
		return 
			"CardRequest{" + 
			"owner = '" + owner + '\'' + 
			",securityNumber = '" + securityNumber + '\'' + 
			",accountId = '" + accountId + '\'' + 
			",balance = '" + balance + '\'' + 
			",lastNumbers = '" + lastNumbers + '\'' + 
			",type = '" + type + '\'' + 
			",cardNumber = '" + cardNumber + '\'' + 
			",expirationDate = '" + expirationDate + '\'' + 
			"}";
		}
}
