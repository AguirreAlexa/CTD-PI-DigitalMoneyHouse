package Model;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.Filter;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.requestSpecification;

public class BaseTest {



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


        return new RequestSpecBuilder().setBaseUri("http://localhost:8080")
                .setBasePath("")
                .addFilters(filters)
                .setContentType(ContentType.JSON).build();
    }

    public RequestSpecification userRequestSpecification(){
        return new RequestSpecBuilder().setBaseUri("http://localhost:8080/user")
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }

    public RequestSpecification cardRequestSpecification(){
        return  new RequestSpecBuilder().setBaseUri("http://localhost:8080/cards")
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }


    public RequestSpecification accountsRequestSpecification(){
        return  new RequestSpecBuilder().setBaseUri("http://localhost:8080/account")
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }

    public RequestSpecification transactionsRequestSpecification(){
        return  new RequestSpecBuilder().setBaseUri("http://localhost:8080/transactions")
                .setBasePath("")
                .setContentType(ContentType.JSON).build();
    }
}
