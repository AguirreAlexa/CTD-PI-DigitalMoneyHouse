package Tests.BCardsTests;

import Data.Factory.Cards;
import Tests.AABase.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.testng.Tag;

import static Data.Builder.Card.CardRequestBuilder.*;
import static io.restassured.RestAssured.requestSpecification;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static Data.Builder.User.LoginUserRequestBuilder.loginUser;
import static io.restassured.RestAssured.given;
import static io.restassured.path.json.JsonPath.from;


public class DCardTests extends BaseTest {

    Response response;
    int id = 62 ;
    String token = "";
    private static String emptyToken ="";
    private static String fakeToken ="eyJhbGciOiJSUzI1NiIsInR5cCIgOiAiSldUIiwia2lkIiA6ICJxaFVQbnZSb2ZTUllwbjZQRTVPTnhyYnVEQ05NWTdNUkw3bGxpa0Y1SVRvIn0.eyJleHAiOjE2NzA3OTY3NDMsImlhdCI6MTY3MDc5NjQ0MywianRpIjoiMmFlMjU1OTktNjE3Mi00NGYwLTlhZTctOGVjYmYzMDM2OWM3IiwiaXNzIjoiaHR0cDovL2xvY2FsaG9zdDo4MDgwL3JlYWxtcy9kbWgiLCJzdWIiOiI1YzliYWJjNi04ZWJlLTQ5MjItOTVkYy1hY2M4NzA1MWY0MTQiLCJ0eXAiOiJCZWFyZXIiLCJhenAiOiJ1c2Vycy1jbGllbnQiLCJzZXNzaW9uX3N0YXRlIjoiNmU5OTgwNzctMmQ1Yy00MzdjLTkyZTUtM2Y0ZjNkYWVkZTgxIiwiYWNyIjoiMSIsImFsbG93ZWQtb3JpZ2lucyI6WyIqIl0sInNjb3BlIjoiZW1haWwgcHJvZmlsZSIsInNpZCI6IjZlOTk4MDc3LTJkNWMtNDM3Yy05MmU1LTNmNGYzZGFlZGU4MSIsImVtYWlsX3ZlcmlmaWVkIjpmYWxzZSwibmFtZSI6Ik1hcmNlbG8gTG9wZXoiLCJwcmVmZXJyZWRfdXNlcm5hbWUiOiJtYXJjZWxpdG8iLCJnaXZlbl9uYW1lIjoiTWFyY2VsbyIsImZhbWlseV9uYW1lIjoiTG9wZXoiLCJlbWFpbCI6Im1hcmNlbGl0b0BtYWlsLmNvbSJ9.ZLesazIG93Ew6slCd-SVz3Rumk9xGZwWJaqOLY-VCdn1fzZViTPnH4L1HwAAlF9xulLGuvH5GWa0AhfTnoUCYwyvpYRiJD9Xu1hCqq8otU9iTcDiyg9dav3FmBCAY3PNuYaaVfbaSkdYSzH4AGWmtMthh0rqE4t32sw2pLCLVPsAD_uYOz7Oj8rQBFc7GvTpXt_Yw7BDGlWEQLvgthZ2ciDDxbpsUexCZ5xIep5EaVZ5UWrpR4wwQTt9PKEaLRgL0ROPFG28ZcvdVy4qGowmNrMcFaIb-kQrQnl0yksCrjmz79LfiZ6xyy-hSxkUZpNwSuju1BovcyynZtPOip-4GQ";

    public void setId(int id) {
        this.id = id;
    }

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



    @Test(priority=1)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - Status code 200")
    public void postCard(){
       String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(defaultCardRequest())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_OK)
               .extract()
               .body()
               .asString();
        Cards cards = from(responses).getObject("", Cards.class);
      //  setId(cards.getId());
    }

    @Test(priority=2)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - with empty token")
    public void postCardWithEmptyToken(){
        given()
                .header("Authorization", "Bearer "+emptyToken)
                .body(defaultCardRequest())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority=3)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - with invalid token")
    public void postCardWithInvalidToken(){
        given()
                .header("Authorization", "Bearer "+fakeToken)
                .body(defaultCardRequest())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority=4)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - without cardNumber")
    public void postCardWithEmptyCardNumber(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(defaultCardRequestWithoutCardNumber())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract()
                .body().asString();
        Assert.assertEquals(responses,"Falta completar el campo CardNumber");
    }

    @Test(priority=5)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - with null Owner")
    public void postCardNullOwner(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(defaultCardRequestWithNullOwner())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract()
                .body().asString();
        Assert.assertEquals(responses,"Falta completar el campo Owner");
    }

    @Test(priority=6)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - with null expiration date")
    public void postCardNullExpirationDate(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(defaultCardRequestWithNullExpirationDate())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract()
                .body().asString();
        //Assert.assertEquals(responses,"Falta completar el campo ExpirationDate");
    }

    @Test(priority=7)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Post Card - with null security number")
    public void postCardNullSecurityNumber(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(defaultCardRequestWithNullSecurityNumber())
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract()
                .body().asString();
        Assert.assertEquals(responses,"Falta completar el campo SecurityNumber");
    }

    @Test(priority=8)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all cards, response has headers")
    public void headersOnGetAllCardsResponse() {
        response = given().header("Authorization", "Bearer "+token).get("/cards");
        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.exist());
    }

    @Test(priority = 9)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get all cards, Status Code is 200")
    public void httpStatus200OnGetAllCards() {
        response = given().header("Authorization", "Bearer "+token).get("/cards");
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test(priority = 10)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all cards, response has content-type: application/json")
    public void contentTypeOnGetAllCardsResponse() {
        response = given().header("Authorization", "Bearer "+token).get("/cards");
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test(priority = 11)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all cards, each user has no empty values")
    public void fieldsNotEmptyGetAllCardsResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("/cards").then().extract().body().asString();

        List<Cards> cardsList = from(responses).getList("", Cards.class);
        for (Cards cards : cardsList) {
            Assert.assertFalse(cards.getId() == null || cards.getAccountId()== null ||
                    cards.getType().isEmpty());
        }

    }

    @Test(priority = 12)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all cards, each user hasn´t null values")
    public void fieldsNotNullGetAllUsersResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("/cards").then().extract().body().asString();

        List<Cards> cardsList = from(responses).getList("", Cards.class);
        for (Cards cards : cardsList) {
            Assert.assertTrue(cards.getId() != null || cards.getAccountId()!= null ||
                    cards.getType()!=null);
        }

    }

    @Test(priority = 13)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a card by id, Status Code is 200")
    public void httpStatus200OnGetCardById() {

        given()
                .header("Authorization", "Bearer "+token)
                .get("/cards/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test(priority = 14)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get a card by id, response has content-type:application/json")
    public void contentTypeOnGetCardByIDResponse() {
        response = given().header("Authorization", "Bearer "+token)
                .get("/cards/{id}", id);
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test(priority = 15)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get a card by id, response has no empty values")
    public void fieldsNotEmptyGetCardByIDResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("/cards/{id}", id).then().extract().body().asString();
        Cards cards = from(responses).getObject("", Cards.class);
            Assert.assertTrue((cards.getId() != null) || (cards.getAccountId() != null) ||
                    !cards.getCardNumber().isEmpty() || !cards.getOwner().isEmpty() || (cards.getExpirationDate() != null) ||
                    (cards.getBalance()!=null) || !cards.getLastNumbers().isEmpty() || !cards.getType().isEmpty());
    }

    @Test(priority = 16)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get a card by id, response has no null values")
    public void fieldsNotNullGetCardByIDResponse() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("/cards/{id}", id).then().extract().body().asString();

        Cards cards = from(responses).getObject("", Cards.class);

        Assert.assertTrue((cards.getId() != null) || (cards.getAccountId() != null) ||
                (cards.getCardNumber() !=null)|| (cards.getOwner()!=null)|| (cards.getExpirationDate() != null) ||
                (cards.getBalance()!=null) || (cards.getLastNumbers() !=null) || (cards.getType()!=null));

    }

    @Test(priority = 17)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully card by id")
    public void putCardById(){

        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(randomCard())
                .put("/cards/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();
        Cards card = from(responses).getObject("", Cards.class);
        Assert.assertTrue(card.getBalance().equals(9999)&&card.getAccountId().equals(4));
    }

    @Test(priority = 18)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail edit card - AccountId missing")
    public void putCardWithoutAccountIdById(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"type\" : \"Crédito\" \n"+"}")
                .put("/cards/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertEquals(responses,"Falta completar el campo account id");
    }

    @Test(priority = 19)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail edit card - Type missing")
    public void putCardWithoutTypeById(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"accountId\" : \"1\" \n"+"}")
                .put("/cards/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo tipo"));
    }

    @Test(priority = 20)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail edit card - Type missing")
    public void putCardWithEmptyValuesId(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"accountId\" : \"\", \n"+
                        "\"type\" : \"\" \n"+"}")
                .put("/cards/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo account id"));
    }

    @Test(priority = 21)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - Null Values")
    public void postCardWithNullValues(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"accountId\" : \n null \n, \n"+
                        "\"type\" : \n null \n"+"}")
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("No se encuentra la cuenta asociada"));
    }

    @Test(priority = 22)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - Type missing")
    public void postCardWithoutTypeValue(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"accountId\" : \"8\" \n"+"}")
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        //Assert.assertTrue(responses.equals("Falta completar el campo Type"));
    }

    @Test(priority = 23)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - AccountId missing")
    public void postCardWithoutAccountIdValue(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"type\" : \"Débito\" \n"+"}")
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("No se encuentra la cuenta asociada"));
    }


    @Test(priority = 24)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - Type missing")
    public void postCardWithTypeNullValue(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"accountId\" : \"8\" \n"+"}")
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Type"));
    }

    @Test(priority = 25)
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - AccountId missing")
    public void postCardWithAccountIdNullValue(){
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n"+
                        "\"type\" : \"null\" \n"+"}")
                .post("/cards")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("No se encuentra la cuenta asociada"));
    }

    @Test(priority = 25)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete card by Id")
    public void deleteCardByID(){
        given()
                .header("Authorization", "Bearer "+token)
                .delete("/cards/{id}",id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test(priority = 26)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete card by invalid Id")
    public void deleteCardByInvalidID(){
        given()
                .header("Authorization", "Bearer "+token)
                .delete("/cards/{id}",98999999)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test(priority = 27)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete card by invalid token")
    public void deleteCardByInvalidToken(){
        given()
                .header("Authorization", "Bearer "+fakeToken)
                .delete("/cards/{id}",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

    @Test(priority = 28)
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Delete card by empty token")
    public void deleteCardByEmptyToken(){
        given()
                .header("Authorization", "Bearer "+emptyToken)
                .delete("/cards/{id}",id)
                .then()
                .statusCode(HttpStatus.SC_UNAUTHORIZED);
    }

}