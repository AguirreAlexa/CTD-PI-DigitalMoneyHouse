package Data.Model.User;

import Data.Factory.Account;

public class RegisterUserResponse {
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String cvu;
	private String alias;
	private int id;
	private String dni;
	private String email;
	private Account account;
	private String username;

	public void setFirstName(String firstName){
		this.firstName = firstName;
	}

	public String getFirstName(){
		return firstName;
	}

	public void setLastName(String lastName){
		this.lastName = lastName;
	}

	public String getLastName(){
		return lastName;
	}

	public void setPhoneNumber(String phoneNumber){
		this.phoneNumber = phoneNumber;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public void setCvu(String cvu){
		this.cvu = cvu;
	}

	public String getCvu(){
		return cvu;
	}

	public void setAlias(String alias){
		this.alias = alias;
	}

	public String getAlias(){
		return alias;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setDni(String dni){
		this.dni = dni;
	}

	public String getDni(){
		return dni;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public void setAccount(Account account){
		this.account = account;
	}

	public Account getAccount(){
		return account;
	}

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}

	@Override
 	public String toString(){
		return 
			"RegisterResponse{" + 
			"firstName = '" + firstName + '\'' + 
			",lastName = '" + lastName + '\'' + 
			",phoneNumber = '" + phoneNumber + '\'' + 
			",cvu = '" + cvu + '\'' + 
			",alias = '" + alias + '\'' + 
			",id = '" + id + '\'' + 
			",dni = '" + dni + '\'' + 
			",email = '" + email + '\'' + 
			",account = '" + account + '\'' + 
			",username = '" + username + '\'' + 
			"}";
		}
}
