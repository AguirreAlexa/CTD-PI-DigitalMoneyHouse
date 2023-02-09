package Tests.AUserTests;

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

import java.util.Base64;

import static Data.Builder.User.LoginUserRequestBuilder.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;

public class BLoginTests extends BaseTest {

    Response response;

    String accessToken ="";

    BaseTest baseTest = new BaseTest();

    @BeforeClass
    public void setUp() { requestSpecification = userLoginSpecification();}




    @Test(priority=1)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login process returns an access token ")
    public void loginOkGetToken() {

        accessToken = given()
                .when()
                .body(loginUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();

       BaseTest.setAccessToken(accessToken);

    }

    @Test(priority=2)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login process returns an access token that contains key 'JWT'")
    public void loginOkHeaderToken() {

        accessToken = given()
                .when()
                .body(loginUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String headers = new String(decoder.decode(chunks[0]));
        Assert.assertTrue(headers.contains("JWT"));

    }

    @Test(priority=3)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Login process returns an access token that contains user´s email")
    public void loginOkPayloadToken() {

        accessToken = given()
                .when()
                .body(loginUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();
        String[] chunks = accessToken.split("\\.");
        Base64.Decoder decoder = Base64.getUrlDecoder();
        String payload = new String(decoder.decode(chunks[1]));
        Assert.assertTrue(payload.contains(loginUser().getEmail()));

    }

    @Test(priority=4)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.NORMAL)
    @Description("When login an user, response has headers")
    public void loginHeaders() {
        response = (Response) given()
                .body(loginUser())
                .post()
                .then()
                .extract()
                .body();
        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.exist());
    }

    @Test(priority=5)
    @Tag("Regression")
    @Epic("Sprint 1")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When login an user, Status Code is 200")
    public void loginOkStatusCode() {
        response = (Response) given()
                .body(loginUser())
                .post()
                .then()
                .extract()
                .body();
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test(priority=6)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.NORMAL)
    @Description("When login an user, response has content-type: application/json")
    public void loginOkContentType() {
        response = (Response) given()
                .body(loginUser())
                .post()
                .then()
                .extract()
                .body();
        String contentType = response.getContentType();
        Assert.assertEquals("text/plain;charset=UTF-8", contentType);
    }

    @Test(priority=7)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to login with an unregistered email")
    public void failLoginUnregisteredEmail() {
        String responses = given()
                .body(unregisteredEmailUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("No existe usuario con este email"));
    }

    @Test(priority=8)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to login with an unregistered password")
    public void failLoginUnregisteredPassword() {
        String responses = given()
                .body(unregisteredPasswordUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("Contraseña incorrecta"));
    }

    @Test(priority=9)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to login with an unregistered user")
    public void failLoginUnregisteredUser() {
        String responses = given()
                .body(unregisteredUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("No existe usuario con este email"));
    }

    @Test(priority=10)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to login with an empty email")
    public void failLoginEmptyEmail() {
        String responses = given()
                .body(emptyEmailUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("No existe usuario con este email"));
    }

    @Test(priority=11)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to login with an null email")
    public void failLoginNullEmail() {
        String responses = given()
                .body(nullEmailUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("No existe usuario con este email"));
    }

    @Test(priority=12)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to login with an null User")
    public void failLoginNullUser() {
        String responses = given()
                .body(nullUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("No existe usuario con este email"));
    }

    @Test(priority=13)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Attempt to login with an null password")
    public void failLoginNullPassword() {
        String responses = given()
                .body(nullPasswordUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("Contraseña incorrecta"));
    }

    @Test(priority=14)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to login with an empty password")
    public void failLoginEmptyPassword() {
        String responses = given()
                .body(emptyPasswordUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("Contraseña incorrecta"));
    }

    @Test(priority=15)
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to login with an empty User")
    public void failLoginEmptyUser() {
        String responses = given()
                .body(emptyUser())
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body()
                .asString();
        Assert.assertTrue(responses.equals("No existe usuario con este email"));
    }
}
