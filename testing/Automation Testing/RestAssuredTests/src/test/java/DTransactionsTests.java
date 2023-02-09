import Data.Factory.Cards;
import Data.Factory.Transactions;
import Data.Factory.Transference;
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

import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.path.json.JsonPath.from;

public class DTransactionsTests extends BaseTest {

    BaseTest baseTest = new BaseTest();

    Response response;
    Integer id = 8;

    @BeforeClass
    public void setUp() {
        requestSpecification = transactionsRequestSpecification();

    }


    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Null Values")
    public void postTransactionWithNullValues() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \n null \n, \n" +
                        "\"accountDestinyId\" : \n null \n, \n" +
                        "\"amount\" : \n null \n, \n" +
                        "\"date\" : \n null \n, \n" +
                        "\"detail\" : \n null \n, \n" +
                        "\"type\" : \n null \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Debe completar todos los campos"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - accountDestinyId missing")
    public void postTransactionWithoutAccountDestinyIdValue() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Account Destiny Id"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - accountOriginId missing")
    public void postTransactionWithoutAccountOriginIdValue() {
        String responses = given()
                .body("{\n" +
                        "\"accountDestinyId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Account Origin Id"));
    }


    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Amount missing")
    public void postTransactionWithoutAmountValue() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Amount"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Date missing")
    public void postTransactionWithoutDateValue() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Date"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Detail missing")
    public void postTransactionWithoutDetailValue() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Detail"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Type missing")
    public void postTransactionWithoutTypeValue() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("Falta completar el campo Type"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add transaction ")
    public void postTransactionStatusCode() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_OK).toString();

    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add transaction ")
    public void postTransactionResponseNotNull() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .extract().body().asString();
        Transactions transactions = from(responses).getObject("", Transactions.class);
        Assert.assertTrue((transactions.getId() != null) || (transactions.getAccountOriginId() != null) ||
                (transactions.getAccountDestinyId() != null) || (transactions.getAmount() != null) || (transactions.getDate() != null)
                || (transactions.getDetail() != null) || (transactions.getType() != null));

    }


    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add transaction ")
    public void postTransactionResponseNotEmpty() {
        String responses = given()
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .extract().body().asString();
        Transactions transactions = from(responses).getObject("", Transactions.class);
        Assert.assertTrue((transactions.getId() != null) || (transactions.getAccountOriginId() != null) ||
                (transactions.getAccountDestinyId() != null) || (transactions.getAmount() != null) || (transactions.getDate() != null)
                || (transactions.getDetail().isEmpty()) || (transactions.getType().isEmpty()));
    }


    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get transactions Status Code ")
    public void getTransactionStatusCode() {
        String responses = given()
                .get()
                .then()
                .statusCode(HttpStatus.SC_OK).toString();
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get transactions - Not Null Response ")
    public void getTransactionResponseNotNull() {
        String responses = given()
                .get()
                .then()
                .extract().body().asString();
        List<Transactions> transactionsList = from(responses).getList("", Transactions.class);
        for (Transactions transactions : transactionsList) {
            Assert.assertTrue((transactions.getId() != null) || (transactions.getAccountOriginId() != null) ||
                    (transactions.getAccountDestinyId() != null) || (transactions.getAmount() != null) || (transactions.getDate() != null)
                    || (transactions.getDetail() != null) || (transactions.getType() != null));
        }
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get transactions - Not Empty Response ")
    public void getTransactionResponseNotEmpty() {
        String responses = given()
                .get()
                .then()
                .extract().body().asString();
        List<Transactions> transactionsList = from(responses).getList("", Transactions.class);
        for (Transactions transactions : transactionsList) {
            Assert.assertTrue((transactions.getId() != null) || (transactions.getAccountOriginId() != null) ||
                    (transactions.getAccountDestinyId() != null) || (transactions.getAmount() != null) || (transactions.getDate() != null)
                    || (transactions.getDetail().isEmpty()) || (transactions.getType().isEmpty()));
        }
    }


    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Delete transactions By ID ")
    public void deleteTransactionByIdStatusCode() {
        String responses = given()
                .delete("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT).toString();
    }


    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference ")
    public void createTransferenceOk() {
        given()
                .body("{\n" +
                        "\"OriginCardId\" : \"2\" ,\n" +
                        "\"destinyAccountId\" : \"1\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference with null values ")
    public void createTransferenceNullValues() {
        Response response = (Response) given()
                .body("{\n" +
                        "\"OriginCardId\" : \"null\" ,\n" +
                        "\"destinyAccountId\" : \"null\" ,\n" +
                        "\"amount\" : \"null\", \n" +
                        "\"date\" : \" null\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Debe completar todos los campos"));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference with null OriginCard value ")
    public void createTransferenceNullOriginCard() {
        Response response = (Response) given()
                .body("{\n" +
                        "\"OriginCardId\" : \"null\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2020-10-22\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo Origin Card Id"));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference with null destinyAccountId value ")
    public void createTransferenceNullDestinyAccount() {
        Response response = (Response) given()
                .body("{\n" +
                        "\"OriginCardId\" : \"1\" ,\n" +
                        "\"destinyAccountId\" : \"null\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2020-10-22\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo Destiny Account Id"));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference with null Amount value ")
    public void createTransferenceNullAmount() {
        Response response = (Response) given()
                .body("{\n" +
                        "\"OriginCardId\" : \"1\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"null\", \n" +
                        "\"date\" : \" 2020-10-22\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo Amount"));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference with null Date value ")
    public void createTransferenceNullDate() {
        Response response = (Response) given()
                .body("{\n" +
                        "\"OriginCardId\" : \"1\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" null\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

        String bodyString = response.getBody().asString();
        Assert.assertTrue(bodyString.contains("Falta completar el campo Date"));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate correct response")
    public void createTransferenceResponse() {
        String responses = given()
                .body("{\n" +
                        "\"OriginCardId\" : \"1\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-26\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();

        Transference transference = from(responses).getObject("", Transference.class);
        Assert.assertTrue((transference.getCardId() == 1) && (transference.getDestinyAccountId() == 2) &&
                (transference.getAmount() == 150.0) && (transference.getDate().equals("2022-11-26")));
    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Validate response hasn't null values")
    public void createTransferenceResponseNotNullValues() {
        String responses = given()
                .body("{\n" +
                        "\"OriginCardId\" : \"1\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-26\" \n" + "}")
                .post("{id}/transferences", id)
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();

        Transference transference = from(responses).getObject("", Transference.class);
        Assert.assertTrue((transference.getCardId() != null ) ||(transference.getDestinyAccountId() != null) ||
                (transference.getAmount() != null) || (transference.getDate() != null));
    }

}


