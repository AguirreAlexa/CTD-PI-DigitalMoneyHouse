package Data.Model.Account;

public class CardsItem{
	private String owner;
	private String securityNumber;
	private int accountId;
	private int balance;
	private String lastNumbers;
	private int id;
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

	public void setAccountId(int accountId){
		this.accountId = accountId;
	}

	public int getAccountId(){
		return accountId;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

	public void setLastNumbers(String lastNumbers){
		this.lastNumbers = lastNumbers;
	}

	public String getLastNumbers(){
		return lastNumbers;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
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
			"CardsItem{" + 
			"owner = '" + owner + '\'' + 
			",securityNumber = '" + securityNumber + '\'' + 
			",accountId = '" + accountId + '\'' + 
			",balance = '" + balance + '\'' + 
			",lastNumbers = '" + lastNumbers + '\'' + 
			",id = '" + id + '\'' + 
			",type = '" + type + '\'' + 
			",cardNumber = '" + cardNumber + '\'' + 
			",expirationDate = '" + expirationDate + '\'' + 
			"}";
		}
}
