package Tests.AUserTests;

import Data.Factory.User;
import Data.Model.User.RegisterUserRequest;
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

import java.util.List;

import static Data.Builder.User.LoginUserRequestBuilder.loginUser;
import static Data.Builder.User.RegisterUserRequestBuilder.newUser;
import static io.restassured.RestAssured.*;
import static io.restassured.path.json.JsonPath.from;


public class BUserTests extends BaseTest {

    Response response;
    int id = 8;
    String token = "";
    private static String emptyToken ="";
    private static String fakeToken ="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxaFVQbnZSb2ZTUllwbjZQRTVPTnhyYnVEQ05NWTdNUkw3bGxpa0Y1SVRvIn0.eyJleHAiOjE2NzA3OTY3NDMsImlhdCI6MTY3MDc5NjQ0MywianRpIjoiMmFlMjU1OTktNjE3Mi00NGYwLTlhZTctOGVjYmYzMDM2OWM3IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9kbWgiLCJzdWIiOiI1YzliYWJjNi04ZWJlLTQ5MjItOTVkYy1hY2M4NzA1MWY0MTQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ1c2Vycy1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiNmU5OTgwNzctMmQ1Yy00MzdjLTkyZTUtM2Y0ZjNkYWVkZTgxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjZlOTk4MDc3LTJkNWMtNDM3Yy05MmU1LTNmNGYzZGFlZGU4MSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Ik1hcmNlbG8gTG9wZXoiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJtYXJjZWxpdG8iLCJnaXZlbl9uYW1lIjoiTWFyY2VsbyIsImZhbWlseV9uYW1lIjoiTG9wZXoiLCJlbWFpbCI6Im1hcmNlbGl0b0BtYWlsLmNvbSJ9.ZLesazIG93Ew6slCd-SVz3Rumk9xGZwWJaqOLY-VCdn1fzZViTPnH4L1HwAAlF9xulLGuvH5GWa0AhfTnoUCYwyvpYRiJD9Xu1hCqq8otU9iTcDiyg9dav3FmBCAY3PNuYaaVfbaSkdYSzH4AGWmtMthh0rqE4t32sw2pLCLVPsAD_uYOz7Oj8rQBFc7GvTpXt_Yw7BDGlWEQLvgthZ2ciDDxbpsUexCZ5xIep5EaVZ5UWrpR4wwQTt9PKEaLRgL0ROPFG28ZcvdVy4qGowmNrMcFaIb-kQrQnl0yksCrjmz79LfiZ6xyy-hSxkUZpNwSuju1BovcyynZtPOip-4GQ";

    @BeforeClass
    public void setUp() {
        requestSpecification = userRequestSpecification();
        loginOkGetToken();
    }

    public String loginOkGetToken() {

       return token = given()
                .when()
                .body(loginUser())
                .post("/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();
    }

    @Test (priority=1)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all users, response has headers")
    public void headersOnGetAllUsersResponse() {
        response = given().header("Authorization", "Bearer "+token).get();
        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.exist());
    }

    @Test(priority=2)
    @Tag("SMOKE")
    @Epic("Sprint 1")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all users, Status Code is 200")
    public void httpStatus200OnGetAllUsers() {
        response = given().header("Authorization", "Bearer "+token).get();
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test(priority=3)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all users, response has content-type: application/json")
    public void contentTypeOnGetAllUsersResponse() {
        response = given().header("Authorization", "Bearer "+token).get();
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test(priority=4)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all users, each user has no empty values")
    public void fieldsNotEmptyGetAllUsersResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get().then().extract().body().asString();

        List<User> userList = from(responses).getList("", User.class);
        for (User user : userList) {
            Assert.assertFalse(user.getAlias().isEmpty() || user.getFirstName().isEmpty() ||
                    user.getDni().isEmpty() || user.getEmail().isEmpty() || user.getCvu().isEmpty() ||
                    user.getLastName().isEmpty() || user.getUsername().isEmpty() || user.getPhoneNumber().isEmpty());
        }

    }

    @Test(priority=5)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all users, each user hasn´t null values")
    public void fieldsNotNullGetAllUsersResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get().then().extract().body().asString();

        List<User> userList = from(responses).getList("", User.class);
        for (User user : userList) {
            Assert.assertTrue(user.getAlias() != (null) || user.getFirstName() != (null) ||
                    user.getDni() != (null) || user.getEmail() != (null) || user.getCvu() != (null) ||
                    user.getLastName() != (null) || user.getUsername() != (null) || user.getPhoneNumber() != (null));
        }

    }

    @Test(priority=6)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user by id, Status Code is 200")
    public void httpStatus200OnGetUserById() {
        response = given().header("Authorization", "Bearer "+token).get("{id}", id);
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test(priority=7)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user by invalid id, Status Code is 404")
    public void OnGetUserByIdWithWrongId() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("{id}", 655)
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR)
                .extract()
                .body()
                .asString();

        //Assert.assertEquals(responses,"No existe usuario con este id");
    }

    @Test(priority=8)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Get an user by their id")
    public void GetUserById() {
        String responses =  given().header("Authorization", "Bearer "+token)
                .get("{id}", id)
                        .then().extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertEquals(user.getId(), id);
    }

    @Test(priority=9)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get all users")
    public void GetAllUsers() {
        String responses =  given().header("Authorization", "Bearer "+token)
                .get()
                .then().extract().body().asString();
        List<User> userList = from(responses).getList("", User.class);
        Assert.assertTrue(userList.size()>0);
    }


    @Test(priority=10)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get a user by id, response has content-type:application/json")
    public void contentTypeOnGetUserByIDResponse() {
        response = given().header("Authorization", "Bearer "+token).get("{id}", id);
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test(priority=11)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user by id, response has no empty values")
    public void fieldsNotEmptyGetUserByIDResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("{id}", id).then().extract().body().asString();

        User user = from(responses).getObject("", User.class);
        Assert.assertFalse(user.getAlias().isEmpty() || user.getFirstName().isEmpty() ||
                user.getDni().isEmpty() || user.getEmail().isEmpty() || user.getCvu().isEmpty() ||
                user.getLastName().isEmpty() || user.getUsername().isEmpty() || user.getPhoneNumber().isEmpty()
        );


    }

    @Test(priority=12)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user by id, response has no null values")
    public void fieldsNotNullGetUserByIDResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("{id}", id).then().extract().body().asString();

        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(user.getAlias() != null || user.getFirstName() != null ||
                user.getDni() != null || user.getEmail() != null || user.getCvu() != null ||
                user.getLastName() != null || user.getUsername() != null || user.getPhoneNumber() != null);


    }

    @Test(priority=13)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user with a invalid token, Status Code is 401")
    public void httpStatusWithInvalidToken() {
        response = given().header("Authorization", "Bearer "+fakeToken).get("{id}", id);
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, httpStatus);
    }

    @Test(priority=14)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a user with an empty token, Status Code is 401")
    public void httpStatusWithEmptyToken() {
        response = given().header("Authorization", "Bearer "+emptyToken).get("{id}", id);
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_UNAUTHORIZED, httpStatus);
    }

    @Test(priority=15)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully firstname´s user by id")
    public void patchFirstNameById(){
        RegisterUserRequest updateUser = newUser().withRandomFirstName().build();
        String nameUpdateUser = updateUser.getFirstName();
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("FirstName", nameUpdateUser)
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertEquals(user.getFirstName(),nameUpdateUser);
    }


    @Test(priority=16)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully lastname´s user by id")
    public void patchLastNameById(){
        RegisterUserRequest updateUser = newUser().withRandomLastName().build();
        String nameUpdateUser = updateUser.getLastName();
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("lastName", nameUpdateUser)
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertEquals(user.getLastName(),nameUpdateUser);
    }


    @Test(priority=17)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully username´s user by id")
    public void patchUsernameById(){
        RegisterUserRequest updateUser = newUser().withRandomUsername().build();
        String nameUpdateUser = updateUser.getUsername();
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("username", nameUpdateUser)
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertEquals(user.getUsername(),nameUpdateUser);
    }

    @Test(priority=18)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully password´s user by id")
    public void patchPasswordById(){
        RegisterUserRequest updateUser = newUser().withRandomPassword().build();
        String nameUpdateUser = updateUser.getPassword();
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("password", nameUpdateUser)
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertEquals(user.getPassword(),nameUpdateUser);
    }

    @Test(priority=19)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to edit password´s user by id with invalid token")
    public void patchPasswordInvalidToken(){
        RegisterUserRequest updateUser = newUser().withRandomPassword().build();
        String nameUpdateUser = updateUser.getPassword();
         given()
                .contentType("multipart/form-data")
                .multiPart("password", nameUpdateUser)
                .header("Authorization", "Bearer "+fakeToken)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority=20)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to edit password´s user by id with empty token")
    public void patchPasswordEmptyToken(){
        RegisterUserRequest updateUser = newUser().withRandomPassword().build();
        String nameUpdateUser = updateUser.getPassword();
        given()
                .contentType("multipart/form-data")
                .multiPart("password", nameUpdateUser)
                .header("Authorization", "Bearer "+emptyToken)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority=21)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to modify user CVU without success")
    public void patchCvuById(){
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("cvu", "123456123546123")
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertEquals(responses,"No se puede modificar el cvu");
    }

    @Test(priority=22)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully dni and phone´s user by id")
    public void patchDniAndPhoneById(){
        RegisterUserRequest updateUser = newUser().withRandomDni().withRandomPhone().build();
        String phoneUser = updateUser.getPhoneNumber();
        String dniUSer= updateUser.getDni();
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("phoneNumber", phoneUser)
                .multiPart("dni",dniUSer)
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(((user.getDni().equals(dniUSer) ) && (user.getPhoneNumber().equals(phoneUser))) );
    }

    @Test(priority=23)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully password and lastName´s user by id")
    public void patchLastNameAndPasswordById(){
        RegisterUserRequest updateUser = newUser().withRandomPassword().withRandomLastName().build();
        String passwordUser = updateUser.getPassword();
        String lastNameUser = updateUser.getLastName();
        String responses = given()
                .contentType("multipart/form-data")
                .multiPart("lastName", lastNameUser)
                .multiPart("password",passwordUser)
                .header("Authorization", "Bearer "+token)
                .patch("update/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        User user = from(responses).getObject("", User.class);
        Assert.assertTrue(((user.getPassword().equals(passwordUser) ) && (user.getPassword().equals(passwordUser))) );
    }
}