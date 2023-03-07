package com.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class PostRequest {


    @BeforeClass
    public void beforeClass(){

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://reqres.in").
                addHeader("Content-Type","application/json").
                log(LogDetail.ALL);

         RestAssured.requestSpecification= requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification= responseSpecBuilder.build();
    }


    @Test
    public void ValidatePostRequest(){

//          String payload = "{\n" +
//                  "    \"name\": \"morpheus\",\n" +
//                  "    \"job\": \"leader\"\n" +
//                  "}";

        File file = new File("src/main/resources/Payload.json");//passing payload from json file

                given().
                        body(file).
                when().
                        post("/api/users").
                then().
                assertThat().body("id", notNullValue(),"name",equalTo("morpheus"));



    }


}
