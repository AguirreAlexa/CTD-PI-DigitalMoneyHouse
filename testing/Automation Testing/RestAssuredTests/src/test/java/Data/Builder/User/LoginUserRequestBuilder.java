package Data.Builder.User;

import Data.Model.User.LoginUserRequest;
import org.apache.commons.lang3.StringUtils;

public class LoginUserRequestBuilder {

    private LoginUserRequest loginUserRequest;

    private static final String REGISTERED_EMAIL = "scaloneta@mail.com";
    private static final String REGISTERED_PASSWORD= "scaloneta86";
    private static final String UNREGISTERED_EMAIL = "pentacampeon@mail.com";
    private static final String UNREGISTERED_PASSWORD= "brazuca10";
    private LoginUserRequestBuilder(){loginUserRequest = new LoginUserRequest();}

    public static LoginUserRequestBuilder user(){return new LoginUserRequestBuilder();}

    public LoginUserRequestBuilder withRegisteredEmail(){
        this.loginUserRequest.setEmail(REGISTERED_EMAIL);
        return this;
    }

    public LoginUserRequestBuilder withRegisteredPassword(){
        this.loginUserRequest.setPassword(REGISTERED_PASSWORD);
        return this;
    }

    public LoginUserRequestBuilder withUnegisteredEmail(){
        this.loginUserRequest.setEmail(UNREGISTERED_EMAIL);
        return this;
    }

    public LoginUserRequestBuilder withUnegisteredPassword(){
        this.loginUserRequest.setPassword(UNREGISTERED_PASSWORD);
        return this;
    }

    public LoginUserRequestBuilder withEmptyEmail(){
        this.loginUserRequest.setEmail(StringUtils.EMPTY);
        return this;
    }

    public LoginUserRequestBuilder withEmptyPassword(){
        this.loginUserRequest.setPassword(StringUtils.EMPTY);
        return this;
    }

    public LoginUserRequestBuilder withNullEmail(){
        this.loginUserRequest.setEmail(null);
        return this;
    }

    public LoginUserRequestBuilder withNullPassword(){
        this.loginUserRequest.setPassword(null);
        return this;
    }
    public static LoginUserRequest loginUser(){
        return  user().withRegisteredEmail().withRegisteredPassword().build();
    }

    public static LoginUserRequest unregisteredEmailUser(){
        return user().withUnegisteredEmail().withRegisteredPassword().build();
    }

    public static LoginUserRequest unregisteredPasswordUser(){
        return user().withRegisteredEmail().withUnegisteredPassword().build();
    }

    public static LoginUserRequest unregisteredUser(){
        return user().withUnegisteredEmail().withUnegisteredPassword().build();
    }

    public static LoginUserRequest nullEmailUser(){
        return user().withNullEmail().withRegisteredPassword().build();
    }

    public static LoginUserRequest nullPasswordUser(){
        return user().withRegisteredEmail().withNullPassword().build();
    }

    public static LoginUserRequest nullUser(){
        return user().withNullEmail().withNullPassword().build();
    }

    public static LoginUserRequest emptyEmailUser(){
        return user().withEmptyEmail().withRegisteredPassword().build();
    }

    public static LoginUserRequest emptyPasswordUser(){
        return user().withRegisteredEmail().withEmptyPassword().build();
    }

    public static LoginUserRequest emptyUser(){
        return user().withEmptyEmail().withEmptyPassword().build();
    }
    public LoginUserRequest build(){
        return loginUserRequest;
    }

    public LoginUserRequestBuilder and(){return this;}

    public LoginUserRequestBuilder but(){return this;}
}
