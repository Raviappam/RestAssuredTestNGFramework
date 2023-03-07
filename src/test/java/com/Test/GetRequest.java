package com.Test;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.Collections;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class GetRequest {

   @Test
    public void GetValidation(){
              given().
                      baseUri("https://reqres.in").
                      header("Authorization","application/json").
              when().
                      get("/api/users").
              then().
                      log().all().    //to get response in the log
                      assertThat().statusCode(200).
                      body("data.last_name", contains("Bluth","Weaver","Wong","Holt","Morris","Ramos"),"data.last_name",not(equalTo(Collections.EMPTY_MAP)),
                              "data[0].last_name",equalTo("Bluth")
                              );

    }


    @Test
    public void GetValidationwithrequestandresponce(){
        given().
                baseUri("https://reqres.in").
                header("Authorization","application/json").
                //log().uri().  //to get the request in the logs
               config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                when().
                get("/api/users").
                then().
                //log().body().  //to get response in the log
                assertThat().statusCode(200);



    }


}
