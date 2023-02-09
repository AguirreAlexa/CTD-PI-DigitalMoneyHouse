package Tests.AUserTests;

import Data.Builder.User.RegisterUserRequestBuilder;
import Data.Model.User.RegisterUserResponse;
import Data.Model.User.UserResponse;
import Tests.AABase.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static Data.Builder.User.RegisterUserRequestBuilder.automationTestUser;
import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;


public class ARegisterTests extends BaseTest {
    Response response;

    @BeforeClass
    public void setUp() { requestSpecification = userRegisterSpecification();
    }

    public void registerOk1() {
        given()
                .when()
                .body(RegisterUserRequestBuilder.registeredUser())
                .post()
                .then()
                .spec(okResponseSpecification());
    }

    @Test(priority=1)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Register process returns an user")
    public void registerOk() {

        given()
                .when()
                .body(RegisterUserRequestBuilder.automationTestUser())
                .post()
                .then()
                .spec(okResponseSpecification());
    }

    @Test(priority=2)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.NORMAL)
    @Description("When register an user, response has headers")
    public void registerHeaders() {
        response = (Response) given()
                .body(automationTestUser())
                .post()
                .then()
                .extract()
                .body();
        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.exist());
    }

    @Test(priority=3)
    @Tag("Regression")
    @Epic("Sprint 1")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When register an user, Status Code is 200")
    public void registerOkStatusCode() {
        response = (Response) given()
                .body(automationTestUser())
                .post()
                .then()
                .extract()
                .body();
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test(priority=4)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.NORMAL)
    @Description("When register an user, response has content-type: application/json")
    public void registerOkContentType() {
        response = (Response) given()
                .body(automationTestUser())
                .post()
                .then()
                .extract()
                .body();
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test(priority=5)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Register process returns an user")
    public void registerRandomUser() {
        String responses =  given()
                .when()
                .body(automationTestUser())
                .post()
                .then()
                .spec(okResponseSpecification())
                .extract()
                .body()
                .asString();
        UserResponse user = from(responses).getObject("", UserResponse.class);

    }

    @Test(priority=6, dependsOnMethods = {"registerRandomUser"})
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Register process returns an user with no empty values")
    public void registerRandomUser2() {
        String responses =  given()
                .when()
                .body(automationTestUser())
                .post()
                .then()
                .spec(okResponseSpecification())
                .extract()
                .body()
                .asString();
        RegisterUserResponse user = from(responses).getObject("", RegisterUserResponse.class);
        Assert.assertTrue((!user.getFirstName().isEmpty()) && (!user.getLastName().isEmpty()) &&
                (!user.getEmail().isEmpty()) && (!user.getDni().isEmpty()) &&
                (!user.getUsername().isEmpty()) && (!user.getCvu().isEmpty()) && (!user.getAlias().isEmpty()));

    }

    @Test(priority=7, dependsOnMethods = {"registerRandomUser", "registerRandomUser2"})
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Register process returns an user with no null values")
    public void registerRandomUser3() {
        String responses =  given()
                .when()
                .body(automationTestUser())
                .post()
                .then()
                .spec(okResponseSpecification())
                .extract()
                .body()
                .asString();
        RegisterUserResponse user = from(responses).getObject("", RegisterUserResponse.class);
        Assert.assertTrue((user.getFirstName()!= null) && (user.getLastName()!= null) &&
                (user.getEmail()!=null) && (user.getDni()!=null) &&
                (user.getUsername()!=null) && (user.getCvu()!=null) && (user.getAlias()!=null));

    }

    @Test(priority=8)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty firstName")
    public void registerWithEmptyFirstName() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithFirstNameEmpty())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo nombre"));
    }

    @Test(priority=9)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty lastName")
    public void registerWithEmptyLastName() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithEmptyLastName())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo apellido"));

    }

    @Test(priority=10)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty dni")
    public void registerWithEmptyDni() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithEmptyDni())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo dni"));
    }

    @Test(priority=11)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty email")
    public void registerWithEmptyEmail() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithEmptyEmail())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo email"));
    }

    @Test(priority=12)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty username")
    public void registerWithEmptyUsername() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithEmptyUsername())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();

    }

    @Test(priority=13)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Can´t register with empty phoneNumber")
    public void registerWithEmptyPhoneNumber() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithEmptyPhone())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo telefono"));
    }

    @Test(priority=13)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty password")
    public void registerWithEmptyPassword() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithEmptyPassword())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }

    @Test(priority=14)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with empty user")
    public void registerWithEmptyUser() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.emptyUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }

    @Test(priority=15)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register without firstName")
    public void registerWithoutFirstName() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutFirstName())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo nombre"));
    }

    @Test(priority=16)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register without lastName")
    public void registerWithoutLastName() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutLastName())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo apellido"));
    }

    @Test(priority=17)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register without dni")
    public void registerWithoutDni() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutDni())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo dni"));
    }

    @Test(priority=18)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register without email")
    public void registerWithoutEmail() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutEmail())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo email"));
    }

    @Test(priority=19)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register without username")
    public void registerWithoutUsername() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutUsername())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }

    @Test(priority=20)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Can´t register without phoneNumber")
    public void registerWithoutPhoneNumber() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutPhone())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo telefono"));
    }

    @Test(priority=21)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register without password")
    public void registerWithoutPassword() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithoutPassword())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }

    @Test(priority=22)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null firstName")
    public void registerWithNullFirstName() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullFirstName())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo nombre"));
    }

    @Test(priority=23)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null lastName")
    public void registerWithNullLastName() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullLastName())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo apellido"));
    }

    @Test(priority=24)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null dni")
    public void registerWithNullDni() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullDni())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo dni"));
    }

    @Test(priority=25)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null email")
    public void registerWithNullEmail() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullEmail())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo email"));
    }

    @Test(priority=26)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null username")
    public void registerWithNullUsername() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullUsername())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }

    @Test(priority=27)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Can´t register with null phoneNumber")
    public void registerWithNullPhoneNumber() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullPhone())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
        Assert.assertTrue((response.getBody().asString()).contains("Falta completar el campo telefono"));
    }

    @Test(priority=28)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null password")
    public void registerWithNullPassword() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.userWithNullPassword())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }

    @Test(priority=29)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Can´t register with null user")
    public void registerWithNullUser() {
        response = (Response) given()
                .when()
                .body(RegisterUserRequestBuilder.nullUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();
    }
}