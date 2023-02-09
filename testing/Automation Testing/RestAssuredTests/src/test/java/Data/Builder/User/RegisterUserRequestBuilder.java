package Data.Builder.User;

import Data.Model.User.RegisterUserRequest;
import org.apache.commons.lang3.StringUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RegisterUserRequestBuilder {
    private RegisterUserRequest registerUserRequest;

    private static final String REGISTERED_FIRSTNAME = "Lionel";
    private static final String REGISTERED_LASTNAME = "Scaloni";
    private static final String REGISTERED_DNI= "43356757";
    private static final String REGISTERED_EMAIL = "scaloneta@mail.com";
    private static final String REGISTERED_USER = "scaloneta";
    private static final String REGISTERED_PHONENUMBER = "45420582";
    private static final String REGISTERED_PASSWORD= "scaloneta86";


    private String generateAutomationTesterName(){
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:SS");
        String dateToStr = dateFormat.format(date);
        String tester = "automation_tester"+dateToStr;
        return tester;
    }

    private RegisterUserRequestBuilder(){
        registerUserRequest = new RegisterUserRequest();
    }

    public static RegisterUserRequestBuilder newUser(){
        return new RegisterUserRequestBuilder();
    }

    public RegisterUserRequestBuilder withFirstName(String firstName){
        this.registerUserRequest.setFirstName(firstName);
        return this;
    }

    public RegisterUserRequestBuilder withLastName(String lastName){
        this.registerUserRequest.setLastName(lastName);
        return this;
    }

    public RegisterUserRequestBuilder withDni(String dni){
        this.registerUserRequest.setDni(dni);
        return this;
    }

    public RegisterUserRequestBuilder withPhoneNumber(String phoneNumber){
        this.registerUserRequest.setPhoneNumber(phoneNumber);
        return this;
    }

    public RegisterUserRequestBuilder withEmail(String email){
        this.registerUserRequest.setEmail(email);
        return this;
    }

    public RegisterUserRequestBuilder withUsername(String username){
        this.registerUserRequest.setUsername(username);
        return this;
    }

    public RegisterUserRequestBuilder withPassword(String password){
        this.registerUserRequest.setPassword(password);
        return this;
    }
    public RegisterUserRequestBuilder withEmptyFirstName(){
        this.registerUserRequest.setFirstName(StringUtils.EMPTY);
        return this;
    }

    public RegisterUserRequestBuilder withEmptyLastName(){
        this.registerUserRequest.setLastName(StringUtils.EMPTY);
        return this;
    }

    public RegisterUserRequestBuilder withEmptyDni(){
        this.registerUserRequest.setDni(StringUtils.EMPTY);
        return this;
    }

    public RegisterUserRequestBuilder withEmptyPhoneNumber(){
        this.registerUserRequest.setPhoneNumber(StringUtils.EMPTY);
        return this;
    }

    public RegisterUserRequestBuilder withEmptyEmail(){
        this.registerUserRequest.setEmail(StringUtils.EMPTY);
        return this;
    }

    public RegisterUserRequestBuilder withEmptyUsername(){
        this.registerUserRequest.setUsername(StringUtils.EMPTY);
        return this;
    }

    public RegisterUserRequestBuilder withEmptyPassword(){
        this.registerUserRequest.setPassword(StringUtils.EMPTY);
        return this;
    }
    public RegisterUserRequest build(){
        return registerUserRequest;
    }

    public RegisterUserRequestBuilder withRandomFirstName(){
        this.registerUserRequest.setFirstName(generateAutomationTesterName());
        return this;
    }
    public RegisterUserRequestBuilder withRandomLastName(){
        this.registerUserRequest.setLastName(generateAutomationTesterName());
        return this;
    }
    public RegisterUserRequestBuilder withRandomDni(){
        this.registerUserRequest.setDni(generateAutomationTesterName());
        return this;
    }

    public RegisterUserRequestBuilder withRandomPhone(){
        this.registerUserRequest.setPhoneNumber(generateAutomationTesterName());
        return this;
    }
    public RegisterUserRequestBuilder withRandomEmail(){
        this.registerUserRequest.setEmail(generateAutomationTesterName()+"@mail.com");
        return this;
    }

    public RegisterUserRequestBuilder withRandomPassword(){
        this.registerUserRequest.setPassword(generateAutomationTesterName());
        return this;
    }

    public RegisterUserRequestBuilder withRandomUsername(){
        this.registerUserRequest.setUsername(generateAutomationTesterName());
        return this;
    }

    public RegisterUserRequestBuilder withNullFirstName(){
        this.registerUserRequest.setFirstName(null);
        return this;
    }

    public RegisterUserRequestBuilder withNullLastName(){
        this.registerUserRequest.setLastName(null);
        return this;
    }

    public RegisterUserRequestBuilder withNullDni(){
        this.registerUserRequest.setDni(null);
        return this;
    }

    public RegisterUserRequestBuilder withNullPhoneNumber(){
        this.registerUserRequest.setPhoneNumber(null);
        return this;
    }

    public RegisterUserRequestBuilder withNullEmail(){
        this.registerUserRequest.setEmail(null);
        return this;
    }

    public RegisterUserRequestBuilder withNullUsername(){
        this.registerUserRequest.setUsername(null);
        return this;
    }

    public RegisterUserRequestBuilder withNullPassword(){
        this.registerUserRequest.setPassword(null);
        return this;
    }

    public static RegisterUserRequest emptyUser(){
        return newUser()
                .withEmptyFirstName()
                .withEmptyLastName()
                .withEmptyDni()
                .withEmptyEmail()
                .withEmptyPhoneNumber()
                .withEmptyUsername()
                .withEmptyPassword()
                .build();
    }

    public static RegisterUserRequest nullUser(){
        return newUser()
                .withNullFirstName()
                .withNullLastName()
                .withNullDni()
                .withNullEmail()
                .withNullPhoneNumber()
                .withNullUsername()
                .withNullPassword()
                .build();
    }

    public static RegisterUserRequest automationTestUser(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithFirstNameEmpty(){
        return newUser()
                .withEmptyFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithEmptyLastName(){
        return newUser()
                .withRandomFirstName()
                .withEmptyLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithEmptyDni(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withEmptyDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithEmptyPhone(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withEmptyPhoneNumber()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithEmptyUsername(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withEmptyUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithEmptyEmail(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withEmptyEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithEmptyPassword(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withEmptyPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullFirstName(){
        return newUser()
                .withNullFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullLastName(){
        return newUser()
                .withRandomFirstName()
                .withNullLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullDni(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withNullDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullPhone(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withNullPhoneNumber()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullUsername(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withNullUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullEmail(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withNullEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithNullPassword(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withNullPassword()
                .build();
    }

    public static RegisterUserRequest userWithExistingUsername(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withUsername(REGISTERED_EMAIL)
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithExistingEmail(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withEmail(REGISTERED_EMAIL)
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest registeredUser(){
        return newUser()
                .withFirstName(REGISTERED_FIRSTNAME)
                .withLastName(REGISTERED_LASTNAME)
                .withDni(REGISTERED_DNI)
                .withPhoneNumber(REGISTERED_PHONENUMBER)
                .withUsername(REGISTERED_USER)
                .withEmail(REGISTERED_EMAIL)
                .withPassword(REGISTERED_PASSWORD)
                .build();
    }

    public static RegisterUserRequest userWithoutFirstName(){
        return newUser()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithoutLastName(){
        return newUser()
                .withRandomFirstName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithoutDni(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithoutPhone(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomUsername()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithoutUsername(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomEmail()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithoutEmail(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomPassword()
                .build();
    }

    public static RegisterUserRequest userWithoutPassword(){
        return newUser()
                .withRandomFirstName()
                .withRandomLastName()
                .withRandomDni()
                .withRandomPhone()
                .withRandomUsername()
                .withRandomEmail()
                .build();
    }
}
