package Data.Model.User;

import java.util.Base64;

public class LoginUserRequest{
	private String password;
	private String email;

	private String accessToken;

	public void setPassword(String password){
		this.password = password;
	}

	public String getPassword(){
		return password;
	}

	public void setEmail(String email){
		this.email = email;
	}

	public String getEmail(){
		return email;
	}

	public String getPayloadToken(String token){
		String[] chunks = token.split("\\.");
		Base64.Decoder decoder = Base64.getUrlDecoder();
		return new String(decoder.decode(chunks[1]));
	};


	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	@Override
 	public String toString(){
		return 
			"LoginUserRequest{" + 
			"password = '" + password + '\'' + 
			",email = '" + email + '\'' + 
			"}";
		};
}
