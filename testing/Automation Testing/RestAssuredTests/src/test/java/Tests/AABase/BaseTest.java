package Tests.AABase;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.requestSpecification;

public class BaseTest {

    private static final String BASE_URL= "http://localhost:9090";
    private static final String USER_URL = "http://localhost:9090/user";
    private static final String CARDS_URL = "http://localhost:9090/cards";
    private static final String ACCOUNT_URL = "http://localhost:9090/account";
    private static final String TRANSACTION_URL = "http://localhost:9090/transactions";

    private static String ACCESS_TOKEN ="";




    public static void setAccessToken(String accessToken) {
        ACCESS_TOKEN = accessToken;
    }

    public static String getAccessToken() {
        return ACCESS_TOKEN;
    }
    public void setUp() {
        requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .build();

    }

    public RequestSpecification defaultRequestSpecification(){

        List<Filter> filters = new ArrayList<>();
        filters.add(new RequestLoggingFilter());
        filters.add(new ResponseLoggingFilter());
        filters.add(new AllureRestAssured());


        return new RequestSpecBuilder().setBaseUri(BASE_URL)
                .setBasePath("")
                .addFilters(filters)
                .setContentType(ContentType.JSON).build();
    }

    public RequestSpecification userRegisterSpecification(){
        return new RequestSpecBuilder().setBaseUri(USER_URL)
                .setBasePath("/register")
                .setContentType(ContentType.JSON).build();
    }
    public RequestSpecification userLoginSpecification(){
        return new RequestSpecBuilder().setBaseUri(USER_URL)
                .setBasePath("/login")
                .setContentType(ContentType.JSON).build();
    }
    public RequestSpecification userRequestSpecification(){
        return new RequestSpecBuilder().setBaseUri(USER_URL)
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }

    public RequestSpecification cardRequestSpecification(){
        return new RequestSpecBuilder().setBaseUri(CARDS_URL)
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }


    public RequestSpecification accountsRequestSpecification(){
        return  new RequestSpecBuilder().setBaseUri(ACCOUNT_URL)
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }

    public RequestSpecification transactionsRequestSpecification(){
        return  new RequestSpecBuilder().setBaseUri(TRANSACTION_URL)
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }

    public ResponseSpecification okResponseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_OK)
                .expectContentType(ContentType.JSON)
                .build();
    }

    public ResponseSpecification badRequestResponseSpecification(){
        return new ResponseSpecBuilder()
                .expectStatusCode(HttpStatus.SC_NOT_FOUND)
                .expectContentType(ContentType.TEXT)
                .build();
    }


}
