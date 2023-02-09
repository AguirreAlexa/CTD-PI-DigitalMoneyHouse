package Tests.DTransactionsTests;


import Data.Builder.Transaction.TransactionRequestBuilder;
import Data.Factory.Transactions;
import Data.Factory.Transference;

import Data.Model.Transaction.TransactionRequest;
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

import java.util.List;

import static Data.Builder.Transaction.TransactionRequestBuilder.*;
import static Data.Builder.User.LoginUserRequestBuilder.loginUser;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.requestSpecification;
import static io.restassured.path.json.JsonPath.from;

public class DTransactionsTests extends BaseTest {

    Response response;
    int id = 8 ;
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
    @Severity(SeverityLevel.NORMAL)
    @Description("Add new  transaction")
    public void postTransaction() {
       given()
                .header("Authorization", "Bearer "+token)
                .body(defaultTransaction())
                .post("/transaction")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }


    @Test(priority = 2)
    @Epic("Sprint 2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - accountDestinyId missing")
    public void postTransactionWithoutAccountDestinyIdValue() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body(defaultTransactionNullDestiny())
                .post("/transaction")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

       // Assert.assertTrue(responses.equals("Falta completar el campo Account Destiny Id"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - accountOriginId missing")
    public void postTransactionWithoutAccountOriginIdValue() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountDestinyId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post("/transaction")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();

        Assert.assertTrue(responses.equals("No existe la cuenta de origen"));
    }


    @Test
    @Epic("Sprint 2")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Amount missing")
    public void postTransactionWithoutAmountValue() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"1\" ,\n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post()
                .then()
                .statusCode(HttpStatus.SC_NOT_FOUND).extract().body().asString();

       // Assert.assertTrue(responses.equals("Falta completar el campo Amount"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Date missing")
    public void postTransactionWithoutDateValue() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"120\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post("/transaction")
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();


    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Detail missing")
    public void postTransactionWithoutDetailValue() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"120\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post("/transaction")
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();

    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Fail add new  transaction - Type missing")
    public void postTransactionWithoutTypeValue() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"120\" ,\n" +
                        "\"accountDestinyId\" : \"2\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\" \n" + "}")
                .post("/transaction")
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();

    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Add transaction ")
    public void postTransactionStatusCode() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"120\" ,\n" +
                        "\"amount\" : \"1.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post("/transaction")
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
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"2\" ,\n" +
                        "\"accountDestinyId\" : \"120\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post("/transaction")
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
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"accountOriginId\" : \"120\" ,\n" +
                        "\"accountDestinyId\" : \"2\" ,\n" +
                        "\"amount\" : \"1.0\", \n" +
                        "\"date\" : \" 2022-11-10\", \n" +
                        "\"detail\" : \"Cuota\", \n" +
                        "\"type\" : \"Débito\" \n" + "}")
                .post("/transaction")
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
                .header("Authorization", "Bearer "+token)
                .get("/transaction")
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
                .header("Authorization", "Bearer "+token)
                .get("/transaction")
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
                .header("Authorization", "Bearer "+token)
                .get("/transaction")
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
                .header("Authorization", "Bearer "+token)
                .delete("/transaction/{id}", id)
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT).toString();
    }


    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Get transference ")
    public void getTransferenceOk() {
        given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"OriginCardId\" : \"2\" ,\n" +
                        "\"destinyAccountId\" : \"120\" ,\n" +
                        "\"amount\" : \"1.0\", \n" +
                        "\"date\" : \" 2022-11-10\" \n" + "}")
                .get("/account/2/transferences")
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
                .header("Authorization", "Bearer "+token)

                .body("{\n" +
                        "\"OriginCardId\" : \"null\" ,\n" +
                        "\"destinyAccountId\" : \"null\" ,\n" +
                        "\"amount\" : \"null\", \n" +
                        "\"date\" : \" null\" \n" + "}")
                .post("account/2/transferences")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

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
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"OriginCardId\" : \"120\" ,\n" +
                        "\"destinyAccountId\" : \"null\" ,\n" +
                        "\"amount\" : \"150.0\", \n" +
                        "\"date\" : \" 2020-10-22\" \n" + "}")
                .post("/account/120/transferences")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();


    }

    @Test
    @Epic("Sprint 3")
    @Tag("SMOKE")
    @Severity(SeverityLevel.NORMAL)
    @Description("Create transference with null Amount value ")
    public void createTransferenceNullAmount() {
        Response response = (Response) given()
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"OriginCardId\" : \"120\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"null\", \n" +
                        "\"date\" : \" 2020-10-22\" \n" + "}")
                .post("/account/120/transferences")
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST)
                .extract().body();

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
                .header("Authorization", "Bearer "+token)
                .body("{\n" +
                        "\"OriginCardId\" : \"120\" ,\n" +
                        "\"destinyAccountId\" : \"2\" ,\n" +
                        "\"amount\" : \"10.0\", \n" +
                        "\"date\" : \" 2022-11-26\" \n" + "}")
                .post("account/120/transferences")
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
    public void getTransferenceResponseNotNullValues() {
        String responses = given()
                .header("Authorization", "Bearer "+token)
                .get("account/120/transferences")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .extract().body().asString();

        Transactions transactions = from(responses).getObject("", Transactions.class);
        Assert.assertTrue((transactions.getAmount() != null ) ||(transactions.getAccountDestinyId() != null));
    }

}


