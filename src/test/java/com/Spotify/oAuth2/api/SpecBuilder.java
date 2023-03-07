package com.Spotify.oAuth2.api;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class SpecBuilder {

    @Step
    public static RequestSpecification getRequestSpec(){

    return new RequestSpecBuilder().
            setBaseUri(System.getProperty("BASE_URI")).
//                setBaseUri("https://api.spotify.com").
                setBasePath("/v1/").
                setContentType(ContentType.JSON).
            addFilter(new AllureRestAssured()).    //to get request and responce in the report
                log(LogDetail.ALL).build();


    }
    @Step
    public static ResponseSpecification getResponseSpec(){

       return new ResponseSpecBuilder().
//                expectContentType(ContentType.JSON).   //this we can use when responce body has an ouput
        log(LogDetail.ALL).build();

    }
}
