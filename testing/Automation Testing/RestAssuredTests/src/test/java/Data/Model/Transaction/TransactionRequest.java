package Data.Model.Transaction;

public class TransactionRequest{
	private String date;
	private Integer amount;
	private Integer accountOriginId;
	private String detail;
	private String type;
	private Integer accountDestinyId;

	public void setDate(String date){
		this.date = date;
	}

	public String getDate(){
		return date;
	}

	public void setAmount(Integer amount){
		this.amount = amount;
	}

	public Integer getAmount(){
		return amount;
	}

	public void setAccountOriginId(Integer accountOriginId){
		this.accountOriginId = accountOriginId;
	}

	public int getAccountOriginId(){
		return accountOriginId;
	}

	public void setDetail(String detail){
		this.detail = detail;
	}

	public String getDetail(){
		return detail;
	}

	public void setType(String type){
		this.type = type;
	}

	public String getType(){
		return type;
	}

	public void setAccountDestinyId(Integer accountDestinyId){
		this.accountDestinyId = accountDestinyId;
	}

	public int getAccountDestinyId(){
		return accountDestinyId;
	}

	@Override
 	public String toString(){
		return 
			"TransactionRequest{" + 
			"date = '" + date + '\'' + 
			",amount = '" + amount + '\'' + 
			",accountOriginId = '" + accountOriginId + '\'' + 
			",detail = '" + detail + '\'' + 
			",type = '" + type + '\'' + 
			",accountDestinyId = '" + accountDestinyId + '\'' + 
			"}";
		}
}
