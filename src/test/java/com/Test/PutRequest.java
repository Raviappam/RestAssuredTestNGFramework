package com.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class PutRequest {

    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                addHeader("Content-Type","application/json").
                log(LogDetail.ALL);

        RestAssured.requestSpecification= requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification= responseSpecBuilder.build();
    }


    @Test
    public void ValidatePutRequest() {

        String payload = "{\n" +
                "    \"name\": \"morpheus\",\n" +
                "    \"job\": \"zion resident\"\n" +
                "}";

        given().
                body(payload).
                when().
                put("/api/users/2").
                then().
                assertThat().
                body("job", notNullValue())
                .body("job", equalTo("zion resident"));
    }


    }


