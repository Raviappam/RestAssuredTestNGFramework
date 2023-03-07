package com.Spotify.oAuth2.api;

import com.Spotify.oAuth2.Pojo.Playlist;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.HashMap;

import static com.Spotify.oAuth2.api.SpecBuilder.getRequestSpec;
import static com.Spotify.oAuth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class RestReusable {

    @Step
    public static Response post(String path, String token , Object requestplaylist){


        return given(getRequestSpec()).
                body(requestplaylist).
//                auth().oauth2(token).     //we cna use insteadof below command to optimize the code
                header("Authorization", "Bearer " + token).
                when().post(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    @Step
    public static Response get(String path, String token){

        return given(getRequestSpec()).
                header("Authorization", "Bearer " + token).

                when().get(path).
                then().spec(getResponseSpec()).
                extract().
                response();
    }

    @Step
    public static Response update(String path, String token, Object  requestplaylist){

        return given(getRequestSpec()).
                body(requestplaylist).
                header("Authorization", "Bearer " + token).
                when().put(path).
                then().spec(getResponseSpec()).
                extract().
                response();

    }

    public static Response postAccount(HashMap<String, String>formParms){
       return given().
                baseUri("https://accounts.spotify.com").
                contentType(ContentType.URLENC).
                formParams(formParms).
                log().all().
                when().post("/api/token").
                then().spec(getResponseSpec()).
                extract().response();
    }
}
