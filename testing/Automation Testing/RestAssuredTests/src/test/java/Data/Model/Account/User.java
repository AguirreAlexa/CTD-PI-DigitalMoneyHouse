package Data.Model.Account;

public class User{
	private String cvu;
	private String alias;
	private Object id;

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

	public void setId(Object id){
		this.id = id;
	}

	public Object getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"User{" + 
			"cvu = '" + cvu + '\'' + 
			",alias = '" + alias + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}
