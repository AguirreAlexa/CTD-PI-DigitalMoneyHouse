package Tests.CAccountTests;

import Data.Factory.AliasCvu;
import Data.Model.Account.AccountResponse;
import Tests.AABase.BaseTest;
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

import static Data.Builder.User.LoginUserRequestBuilder.loginUser;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.path.json.JsonPath.from;

public class EAccountTests extends BaseTest {

    Response response;
    int id = 2 ;
    String token = "";
    private static String emptyToken ="";
    private static String fakeToken ="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxaFVQbnZSb2ZTUllwbjZQRTVPTnhyYnVEQ05NWTdNUkw3bGxpa0Y1SVRvIn0.eyJleHAiOjE2NzA3OTY3NDMsImlhdCI6MTY3MDc5NjQ0MywianRpIjoiMmFlMjU1OTktNjE3Mi00NGYwLTlhZTctOGVjYmYzMDM2OWM3IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9kbWgiLCJzdWIiOiI1YzliYWJjNi04ZWJlLTQ5MjItOTVkYy1hY2M4NzA1MWY0MTQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ1c2Vycy1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiNmU5OTgwNzctMmQ1Yy00MzdjLTkyZTUtM2Y0ZjNkYWVkZTgxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjZlOTk4MDc3LTJkNWMtNDM3Yy05MmU1LTNmNGYzZGFlZGU4MSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Ik1hcmNlbG8gTG9wZXoiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJtYXJjZWxpdG8iLCJnaXZlbl9uYW1lIjoiTWFyY2VsbyIsImZhbWlseV9uYW1lIjoiTG9wZXoiLCJlbWFpbCI6Im1hcmNlbGl0b0BtYWlsLmNvbSJ9.ZLesazIG93Ew6slCd-SVz3Rumk9xGZwWJaqOLY-VCdn1fzZViTPnH4L1HwAAlF9xulLGuvH5GWa0AhfTnoUCYwyvpYRiJD9Xu1hCqq8otU9iTcDiyg9dav3FmBCAY3PNuYaaVfbaSkdYSzH4AGWmtMthh0rqE4t32sw2pLCLVPsAD_uYOz7Oj8rQBFc7GvTpXt_Yw7BDGlWEQLvgthZ2ciDDxbpsUexCZ5xIep5EaVZ5UWrpR4wwQTt9PKEaLRgL0ROPFG28ZcvdVy4qGowmNrMcFaIb-kQrQnl0yksCrjmz79LfiZ6xyy-hSxkUZpNwSuju1BovcyynZtPOip-4GQ";


    @BeforeClass
    public void setUp() {
        requestSpecification = defaultRequestSpecification();
        token = loginOkGetToken();
    }

    public String loginOkGetToken() {

        return token = given()
                .when()
                .body(loginUser())
                .post("user/login")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();
    }



    @Test(priority = 1)
    @Epic("Sprint 2")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Status code when get account by id")
    public void statusCodeGetAccountById(){
        given()
                .header("Authorization", "Bearer "+token)
                .get("/account/{id}",id)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(priority = 2)
    @Epic("Sprint 2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Content type when get account by id")
    public void ContentTypeGetAccountById(){
        response = given().header("Authorization", "Bearer "+token)
                .get("/account/{id}", id);
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test(priority = 3)
    @Epic("Sprint 2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get account by id")
    public void GetAccountById(){
        String responses = given().header("Authorization", "Bearer "+token)
                .get("/account/{id}", id)
                .then()
                .extract()
                .body()
                .asString();
        AccountResponse accountResponse = from(responses).getObject("",AccountResponse.class);
        Assert.assertEquals(accountResponse.getId(), id);
    }

    @Test(priority = 4)
    @Epic("Sprint 2")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Status code when get account by id with invalid token")
    public void statusCodeGetAccountByIdInvalidToken(){
        given()
                .header("Authorization", "Bearer "+fakeToken)
                .get("/account/{id}",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 5)
    @Epic("Sprint 2")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Status code when get account by id with empty token")
    public void statusCodeGetAccountByIdEmptyToken(){
        given()
                .header("Authorization", "Bearer "+emptyToken)
                .get("/account/{id}",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 6)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Account Id by invalid Id")
    public void GetByAccountIdInvalid(){
        String responses = given()
                .header("Authorization", "Bearer "+token)

                .get("/account/{id}", 789)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

    }

    @Test(priority = 7)
    @Epic("Sprint 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Cards by Id Account")
    public void getCardsByAccountId(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .queryParam("id",id)
                .get("/account/cards/")
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
    }

    @Test(priority = 8)
    @Epic("Sprint 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Cards by Id Account with invalid token")
    public void getCardsByAccountIdInvalidToken(){
        String responses = given()
                .header("Authorization", "Bearer "+fakeToken)
                .queryParam("id",id)
                .get("/account/cards/")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED).extract().body().asString();
    }

    @Test(priority = 9)
    @Epic("Sprint 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Cards by Id Account with Empty token")
    public void getCardsByAccountIdEmptyToken(){
        String responses = given()
                .header("Authorization", "Bearer "+emptyToken)
                .queryParam("id",id)
                .get("/account/cards/")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED).extract().body().asString();
    }

    @Test(priority = 10)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Activity by Id Account ")
    public void getActivityByAccountId(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("/account/{id}/activity",id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
    }

    @Test(priority = 11)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Activity by Id Account invalid Id")
    public void getActivityByInvalidAccountId(){
        given()
                .header("Authorization", "Bearer "+token)
                .get("/account/{id}/activity",987654)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 12)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Activity by Id Account with invalid token")
    public void getActivityByAccountIdInvalidToken(){
       given()
                .header("Authorization", "Bearer "+fakeToken)
                .get("/account/{id}/activity",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 13)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Activity by Id Account with empty token")
    public void getActivityByAccountIdEmptyToken(){
        given()
                .header("Authorization", "Bearer "+emptyToken)
                .get("/account/{id}/activity",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 14)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Transaction By Id  and accountId")
    public void getTransactionByIdAndAccountId(){
        given()
                .header("Authorization", "Bearer "+token)
                .pathParam("id_account",id)
                .pathParam("id_transaction",6)
                .get("/account/{id_account}/activity/{id_transaction}")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(priority = 15)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Transaction By invalid Id  and accountId")
    public void getTransactionByInvalidIdAndAccountId(){
        given()
                .header("Authorization", "Bearer "+token)
                .pathParam("id_account",id)
                .pathParam("id_transaction",9878454)
                .get("/account/{id_account}/activity/{id_transaction}")
                .then()
                .statusCode(HttpStatus.SC_INTERNAL_SERVER_ERROR);
    }

    @Test(priority = 16)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Transaction By Id  and accountId - invalid Token")
    public void getTransactionByIdAndAccountIdInvalidToken(){
        given()
                .header("Authorization", "Bearer "+fakeToken)
                .pathParam("id_account",id)
                .pathParam("id_transaction",6)
                .get("/account/{id_account}/activity/{id_transaction}")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 17)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Get Transaction By Id  and accountId - empty Token")
    public void getTransactionByIdAndAccountIdEmptyToken(){
        given()
                .header("Authorization", "Bearer "+emptyToken)
                .pathParam("id_account",id)
                .pathParam("id_transaction",6)
                .get("/account/{id_account}/activity/{id_transaction}")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 18)
    @Epic("Sprint 4")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Edit alias invalid accountId")
    public void editAliasInvalidAccount(){
        given()
                .header("Authorization", "Bearer "+token)
                .put("/account/{id_account}",id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 19)
    @Epic("Sprint 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Edit empty alias")
    public void editEmptyAlias(){
        given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"alias\" : \"\" \n"+"}")
                .put("/account/{id_account}",id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test(priority = 19)
    @Epic("Sprint 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Edit empty alias invalid token")
    public void editEmptyAliasInvalidToken(){
        given()
                .header("Authorization", "Bearer "+fakeToken)
                .body("{\n"+
                        "\"alias\" : \"\" \n"+"}")
                .put("/account/{id_account}",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 20)
    @Epic("Sprint 3")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Edit empty alias empty token")
    public void editEmptyAliasEmptyToken(){
        given()
                .header("Authorization", "Bearer "+emptyToken)
                .body("{\n"+
                        "\"alias\" : \"\" \n"+"}")
                .put("/account/{id_account}",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }
}
