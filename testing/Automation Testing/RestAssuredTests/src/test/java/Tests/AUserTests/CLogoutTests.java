package Tests.AUserTests;

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

public class CLogoutTests extends BaseTest {


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

    @Test(priority=1)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to logout with invalid token")
    public void logoutInvalidToken() {
        given().header("Authorization", "Bearer "+fakeToken)
                .post("/logout")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);

    }

    @Test(priority=2)
    @Tag("SMOKE")
    @Epic("Sprint 1")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Attempt to logout with empty token")
    public void logoutEmptyToken() {
        given().header("Authorization", "Bearer "+emptyToken)
                .post("/logout")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }


    @Test(priority=3)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Successful logout returns status 200")
    public void statusCodeLogout () {
        given().header("Authorization", "Bearer "+token)
                .post("/logout")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test(priority=4)
    @Epic("Sprint 1")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Successful logout returns message")
    public void fieldsNotEmptyGetAllUsersResponse() {
       loginOkGetToken();
       String responses= given().header("Authorization", "Bearer "+token)
                .post("/logout")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .body()
                .asString();
       Assert.assertEquals(responses,"User logout successful");
    }


}
