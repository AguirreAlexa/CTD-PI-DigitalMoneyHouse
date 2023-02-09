package Data.Model.User;

public class UserResponse{
	private String firstName;
	private String lastName;
	private String phoneNumber;
	private String cvu;
	private String alias;
	private int id;
	private String dni;
	private String email;
	private Object account;
	private String username;

	public String getFirstName(){
		return firstName;
	}

	public String getLastName(){
		return lastName;
	}

	public String getPhoneNumber(){
		return phoneNumber;
	}

	public String getCvu(){
		return cvu;
	}

	public String getAlias(){
		return alias;
	}

	public int getId(){
		return id;
	}

	public String getDni(){
		return dni;
	}

	public String getEmail(){
		return email;
	}

	public Object getAccount(){
		return account;
	}

	public String getUsername(){
		return username;
	}
}
