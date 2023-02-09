package Data.Factory;



public class User{
	private String firstName;
	private String lastName;
	private String password;
	private String phoneNumber;
	private String cvu;
	private String alias;
	private int id;
	private String dni;
	private String email;
	private String username;
	private Account account;

	public User() {
	}

	public Account getAccount() {
		return account;
	}

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

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
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

	public void setUsername(String username){
		this.username = username;
	}

	public String getUsername(){
		return username;
	}


}
