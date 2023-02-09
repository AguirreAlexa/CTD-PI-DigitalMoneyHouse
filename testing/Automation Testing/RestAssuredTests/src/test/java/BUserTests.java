import Data.Factory.User;
import Model.BaseTest;
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

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.path.json.JsonPath.from;


public class BUserTests extends BaseTest {

    Response response;
    int id = 8;


    @BeforeClass
    public void setUp() {
        requestSpecification = userRequestSpecification();
    }


    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all users, response has headers")
    public void headersOnGetAllUsersResponse() {
        response = given().get();
        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.exist());
    }

    @Test
    @Tag("SMOKE")
    @Epic("Sprint 1")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get all users, Status Code is 200")
    public void httpStatus200OnGetAllUsers() {
        response = given().get();
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all users, response has content-type: application/json")
    public void contentTypeOnGetAllUsersResponse() {
        response = given().get();
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all users, each user has no empty values")
    public void fieldsNotEmptyGetAllUsersResponse() {
        String responses = given()

                .get().then().extract().body().asString();

        List<User> userList = from(responses).getList("", User.class);
        for (User user : userList) {
            Assert.assertFalse(user.getAlias().isEmpty() || user.getFirstName().isEmpty() ||
                    user.getDni().isEmpty() || user.getEmail().isEmpty() || user.getCvu().isEmpty() ||
                    user.getLastName().isEmpty() || user.getUsername().isEmpty() || user.getPhoneNumber().isEmpty());
        }

    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all users, each user hasn´t null values")
    public void fieldsNotNullGetAllUsersResponse() {
        String responses = given()

                .get().then().extract().body().asString();

        List<User> userList = from(responses).getList("", User.class);
        for (User user : userList) {
            Assert.assertTrue(user.getAlias() != (null) || user.getFirstName() != (null) ||
                    user.getDni() != (null) || user.getEmail() != (null) || user.getCvu() != (null) ||
                    user.getLastName() != (null) || user.getUsername() != (null) || user.getPhoneNumber() != (null));
        }

    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user by id, Status Code is 200")
    public void httpStatus200OnGetUserById() {

        response = given().get("{id}", id);
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get a user by id, response has content-type:application/json")
    public void contentTypeOnGetUserByIDResponse() {
        response = given().get("{id}", id);
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get a user by id, response has no empty values")
    public void fieldsNotEmptyGetUserByIDResponse() {
        String responses = given()

                .get("{id}", id).then().extract().body().asString();

        User user = from(responses).getObject("", User.class);
        Assert.assertFalse(user.getAlias().isEmpty() || user.getFirstName().isEmpty() ||
                user.getDni().isEmpty() || user.getEmail().isEmpty() || user.getCvu().isEmpty() ||
                user.getLastName().isEmpty() || user.getUsername().isEmpty() || user.getPhoneNumber().isEmpty()
        );


    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get a user by id, response has no null values")
    public void fieldsNotNullGetUserByIDResponse() {
        String responses = given()

                .get("{id}", id).then().extract().body().asString();

        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getAlias() != null || user.getFirstName() != null ||
                user.getDni() != null || user.getEmail() != null || user.getCvu() != null ||
                user.getLastName() != null || user.getUsername() != null || user.getPhoneNumber() != null);


    }


    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully firstname´s user by id")
    public void patchFirstNameById(){

        String responses = given()
                .body("{\n"+
                        "\"firstName\" : \"Marcelo Javier\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getFirstName().equals("Marcelo Javier"));
    }
    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully lastname´s user by id")
    public void patchLastNameById(){
        String responses = given()
                .body("{\n"+
                        "\"lastName\" : \"Mendoza Roger\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getLastName().equals("Mendoza Roger"));
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully emails´s user by id")
    public void patchEmailById(){
        String responses = given()
                .body("{\n"+
                        "\"email\" : \"mmendoza@mail.com\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getEmail().equals("mmendoza@mail.com"));
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully username´s user by id")
    public void patchUsernameById(){
        String responses = given()
                .body("{\n"+
                        "\"username\" : \"mmroger\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getUsername().equals("mmroger"));
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully password´s user by id")
    public void patchPasswordById(){
        String responses = given()
                .body("{\n"+
                        "\"password\" : \"mmendoza1234\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getPassword().equals("mmendoza1234"));
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to modify user CVU without success")
    public void patchCvuById(){
        String responses = given()
                .body("{\n"+
                        "\"cvu\" : \"123456\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertFalse(user.getCvu().equals("123456"));
    }

    @Test
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Modify user alias")
    public void patchAliasById(){
        String responses = given()
                .body("{\n"+
                        "\"alias\" : \"rio.seco.bravo\" \n"+"}")
                .put("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertEquals("rio.seco.bravo", user.getAlias());
    }
}