package dh.pi.userservice.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dh.pi.userservice.entity.Account;

import javax.persistence.Id;

public class UserRegisterDTO {

    @Id
    private Integer id;
    private String firstName;
    private String lastName;
    private String dni;
    private String email;
    private String phoneNumber;
    private String username;

    @JsonIgnore
    private String password;

    private String cvu;
    private String alias;

    @JsonIgnore
    private String keycloakId;

    private Account account;

    public UserRegisterDTO() {
    }

    public UserRegisterDTO(Integer id, String firstName, String lastName, String dni, String email, String phoneNumber, String username, String password, String cvu, String alias, String token) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.password = password;
        this.cvu = cvu;
        this.alias = alias;
    }

    public UserRegisterDTO(Integer id, String firstName, String lastName, String dni, String email, String phoneNumber, String username, String cvu, String alias) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.cvu = cvu;
        this.alias = alias;
    }

    public UserRegisterDTO(Integer id, String firstName, String lastName, String dni, String email, String phoneNumber, String username, String cvu, String alias, Account account) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dni = dni;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.username = username;
        this.cvu = cvu;
        this.alias = alias;
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCvu() {
        return cvu;
    }

    public void setCvu(String cvu) {
        this.cvu = cvu;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public Account getAccount() {
        return account;
    }
    public void setAccount(Account account) {
        this.account = account;
    }

}

