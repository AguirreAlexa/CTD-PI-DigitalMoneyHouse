package Data.Model.Account;

import java.util.List;

public class AccountResponse{
	private List<CardsItem> cards;
	private int balance;
	private int id;
	private Object transactions;
	private int userId;
	private User user;

	public void setCards(List<CardsItem> cards){
		this.cards = cards;
	}

	public List<CardsItem> getCards(){
		return cards;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}

	public int getBalance(){
		return balance;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTransactions(Object transactions){
		this.transactions = transactions;
	}

	public Object getTransactions(){
		return transactions;
	}

	public void setUserId(int userId){
		this.userId = userId;
	}

	public int getUserId(){
		return userId;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
	}

	@Override
 	public String toString(){
		return 
			"AccountResponse{" + 
			"cards = '" + cards + '\'' + 
			",balance = '" + balance + '\'' + 
			",id = '" + id + '\'' + 
			",transactions = '" + transactions + '\'' + 
			",userId = '" + userId + '\'' + 
			",user = '" + user + '\'' + 
			"}";
		}
}