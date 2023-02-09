import Data.Factory.Cards;
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


public class CCardTests extends BaseTest {

    Response response;
    int id = 8;


    @BeforeClass
    public void setUp() {
        requestSpecification = cardRequestSpecification();
    }


    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all cards, response has headers")
    public void headersOnGetAllCardsResponse() {
        response = given().get();
        Headers headers = response.getHeaders();
        Assert.assertTrue(headers.exist());
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get all cards, Status Code is 200")
    public void httpStatus200OnGetAllCards() {
        response = given().get();
        int httpStatus = response.getStatusCode();
        Assert.assertEquals(HttpStatus.SC_OK, httpStatus);
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get all cards, response has content-type: application/json")
    public void contentTypeOnGetAllCardsResponse() {
        response = given().get();
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all cards, each user has no empty values")
    public void fieldsNotEmptyGetAllCardsResponse() {
        String responses = given()

                .get().then().extract().body().asString();

        List<Cards> cardsList = from(responses).getList("", Cards.class);
        for (Cards cards : cardsList) {
            Assert.assertFalse(cards.getId() == null || cards.getAccountId()== null ||
                    cards.getType().isEmpty());
        }

    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get all cards, each user hasn´t null values")
    public void fieldsNotNullGetAllUsersResponse() {
        String responses = given()

                .get().then().extract().body().asString();

        List<Cards> cardsList = from(responses).getList("", Cards.class);
        for (Cards cards : cardsList) {
            Assert.assertTrue(cards.getId() != null || cards.getAccountId()!= null ||
                    cards.getType()!=null);
        }

    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("When get a card by id, Status Code is 200")
    public void httpStatus200OnGetCardById() {

        given().get("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK);

    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("When get a card by id, response has content-type:application/json")
    public void contentTypeOnGetCardByIDResponse() {
        response = given().get("{id}", id);
        String contentType = response.getContentType();
        Assert.assertEquals("application/json", contentType);
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get a card by id, response has no empty values")
    public void fieldsNotEmptyGetCardByIDResponse() {
        String responses = given()
                .get("{id}", id).then().extract().body().asString();
        Cards cards = from(responses).getObject("", Cards.class);
            Assert.assertFalse((cards.getId() != null) || (cards.getAccountId() != null) ||
                    !cards.getCardNumber().isEmpty() || !cards.getOwner().isEmpty() || (cards.getExpirationDate() != null) ||
                    (cards.getBalance()!=null) || !cards.getLastNumbers().isEmpty() || !cards.getType().isEmpty());
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.CRITICAL)
    @Description("When get a card by id, response has no null values")
    public void fieldsNotNullGetCardByIDResponse() {
        String responses = given()

                .get("{id}", id).then().extract().body().asString();

        Cards cards = from(responses).getObject("", Cards.class);

        Assert.assertFalse((cards.getId() != null) || (cards.getAccountId() != null) ||
                (cards.getCardNumber() !=null)|| (cards.getOwner()!=null)|| (cards.getExpirationDate() != null) ||
                (cards.getBalance()!=null) || (cards.getLastNumbers() !=null) || (cards.getType()!=null));

    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Post Card")
    public void postCard(){

        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \"2\", \n"+
                        "\"cardNumber\" : \"1234567891234567\", \n"+
                        "\"owner\" : \"Marcelo Fernandez\", \n"+
                        "\"securityNumber\" : \"552\", \n"+
                        "\"expirationDate\" : \"2022-12-31\", \n"+
                        "\"lastNumbers\" : \"4567\", \n"+
                        "\"balance\" : \"1200\", \n"+
                        "\"type\" : \"Débito\" \n"+"}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();
        Cards card = from(responses).getObject("", Cards.class);
        Assert.assertTrue((card.getType().equals("Débito")) && (card.getLastNumbers().equals("4567")) &&
                (card.getCardNumber().equals("1234567891234567")) && (card.getOwner().equals("Marcelo Fernandez")) &&
                (card.getExpirationDate().equals("2022-12-31")) && (card.getBalance() == 1200.0) && (card.getAccountId()==2));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Edit successfully card by id")
    public void putCardById(){

        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \"2\", \n"+
                        "\"cardNumbers\" : \"1234567891234567\", \n"+
                        "\"owner\" : \"Marcelo Lopez\", \n"+
                        "\"securityNumber\" : \"552\", \n"+
                        "\"expirationDate\" : \"2022-12-31\", \n"+
                        "\"lastNumbers\" : \"4567\", \n"+
                        "\"balance\" : \"200.0\", \n"+
                        "\"type\" : \"Débito\" \n"+"}")
                .put("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();
        Cards card = from(responses).getObject("", Cards.class);
        Assert.assertTrue(card.getType().equals("Débito")&&card.getAccountId().equals(2));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail edit card - AccountId missing")
    public void putCardWithoutAccountIdById(){
        String responses = given()
                .body("{\n"+
                        "\"type\" : \"Crédito\" \n"+"}")
                .put("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("El campo accountId no puede estar vacio"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail edit card - Type missing")
    public void putCardWithoutTypeById(){
        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \"1\" \n"+"}")
                .put("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("El campo type no puede estar vacio"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail edit card - Type missing")
    public void putCardWithEmptyValuesId(){
        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \"\", \n"+
                        "\"type\" : \"\" \n"+"}")
                .put("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Debe completar todos los campos"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - Null Values")
    public void postCardWithNullValues(){
        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \n null \n, \n"+
                        "\"type\" : \n null \n"+"}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Debe completar todos los campos"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - Type missing")
    public void postCardWithoutTypeValue(){
        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \"8\" \n"+"}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Type"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - AccountId missing")
    public void postCardWithoutAccountIdValue(){
        String responses = given()
                .body("{\n"+
                        "\"type\" : \"Débito\" \n"+"}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo AccountId"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - Type missing")
    public void postCardWithTypeNullValue(){
        String responses = given()
                .body("{\n"+
                        "\"accountId\" : \"8\" \n"+"}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Type"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  card - AccountId missing")
    public void postCardWithAccountIdNullValue(){
        String responses = given()
                .body("{\n"+
                        "\"type\" : \"null\" \n"+"}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo AccountId"));
    }


}