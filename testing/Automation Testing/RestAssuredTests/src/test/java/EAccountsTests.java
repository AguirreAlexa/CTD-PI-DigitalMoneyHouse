import Data.Factory.AliasCvu;
import Data.Factory.User;
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
import static io.restassured.path.json.JsonPath.from;

public class EAccountsTests extends BaseTest {

    BaseTest baseTest = new BaseTest();

    Response response;
    Integer id = 7;

    @BeforeClass
    public void setUp() {
        requestSpecification = accountsRequestSpecification();
    }




    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Edit alias by Account Id")
    public void patchAliasByAccountId(){
        String responses = given()
                .body("{\n"+
                        "\"alias\" : \"baul.cinco.tarjeta\" \n"+"}")
                .patch("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_OK).extract().body().asString();
        AliasCvu aliasCvu = from(responses).getObject("", AliasCvu.class);
        Assert.assertTrue(aliasCvu.getAlias().equals("baul.cinco.tarjeta"));
    }

    @Test
    @Epic("Sprint 2")
    @Tag("SMOKE")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Atempt to edit empty alias by Account Id")
    public void patchEmptyAliasByAccountId(){
        String responses = given()
                .body("{\n"+
                        "\"alias\" : \"\" \n"+"}")
                .patch("{id}", id)
                .then()
                .statusCode(HttpStatus.SC_BAD_REQUEST).extract().body().asString();


        Assert.assertTrue(responses.equals("No se puede actualizar por el alias vacio"));
    }
}
