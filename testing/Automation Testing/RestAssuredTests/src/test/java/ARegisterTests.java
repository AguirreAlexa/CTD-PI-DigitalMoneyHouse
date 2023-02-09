import Model.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;


public class ARegisterTests extends BaseTest {
    BaseTest baseTest = new BaseTest();

    Response response;

    @BeforeClass
    public void setUp() {
        requestSpecification = defaultRequestSpecification();
    }

    @Test
    @Epic("Sprint 1")
    @Tag("Smoke")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate response in a registry process")
    public void registerOkValidateResponse() {
        response = (Response) given()
                .when()
                .body("{\n"+
                        "\"firstName\" : \"Lorena\", \n"+
                        "\"lastName\" : \"Mendoza\", \n"+
                        "\"dni\" : \"22548698\" ,\n"+
                        "\"email\" : \"loremendoza@mail.com\" ,\n"+
                        "\"phoneNumber\" : \"45410582\", \n"+
                        "\"username\" : \"loremendoza\", \n"+
                        "\"password\" : \"lore1234\" \n"+"}")
                .post("user/register")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body();

        String bodyString = response.getBody().asString();

    }

    @Test
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate response in a wrong registry process - FirstName")
    public void registerWithoutFirstNameResponse() {
        response = (Response) given()
                .when()
                .body("{\n"+
                        "\"lastName\" : \"Mendoza\", \n"+
                        "\"dni\" : \"22548698\", \n"+
                        "\"email\" : \"loremendoza@mail.com\", \n"+
                        "\"phoneNumber\" : \"45410582\", \n"+
                        "\"username\" : \"loremendoza1\", \n"+
                        "\"password\" : \"lore1234\" \n"+"}")
                .post("user/register")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo nombre"));

    }

    @Test
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Validate response in a wrong registry process - LastName")
    public void registerWithoutLastNameResponse() {
        response = (Response) given()
                .when()
                .body("{\n"+
                        "\"firstName\" : \"Maria\" ,\n"+
                        "\"dni\" : \"22548698\", \n"+
                        "\"email\" : \"loremendoza@mail.com\", \n"+
                        "\"phoneNumber\" : \"45410582\", \n"+
                        "\"username\" : \"loremendoza2\", \n"+
                        "\"password\" : \"lore1234\" \n"+"}")
                .post("user/register")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo apellido"));

    }

    @Test
    @Epic("Sprint 1")
    @Tag("Regression")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Validate response in a wrong registry process - Dni")
    public void registerWithoutDniResponse() {
        response = (Response) given()
                .when()
                .body("{\n"+
                        "\"firstName\" : \"Maria\" , \n"+
                        "\"lastName\" : \"Mendoza\", \n"+
                        "\"email\" : \"marimendoza@mail.com\", \n"+
                        "\"phoneNumber\" : \"45410582\" ,\n"+
                        "\"username\" : \"mariamendoza\", \n"+
                        "\"password\" : \"mari1234\" \n"+"}")
                .post("user/register")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract()
                .body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo dni"));

    }
}