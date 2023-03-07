package com.Spotify.oAuth2.api;

import Utils.ConfigLoader;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;


import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;

import static com.Spotify.oAuth2.api.SpecBuilder.getResponseSpec;
import static io.restassured.RestAssured.given;

public class TokenManager {

    //code to use token for other scripts before it expires.

//    private static String access_token;
//    private static Instant expiry_time;
//
//    public static String getToken(){
//        try{
//        if(access_token==null || Instant.now().isAfter(expiry_time)){
//            System.out.println("Renewing token ...");
//            Response response = renewToken();
//            access_token = response.path("access_token");
//            int expiryDurationInSeconds = response.path("expires_in");
//            expiry_time = Instant.now().plusSeconds(expiryDurationInSeconds - 300);
//
//        }else {
//            System.out.println("Token is good to use");
//        }
//
//        }catch (Exception e){
//            throw new RuntimeException("ABORT!!! failed to get token");
//
//        }
//    return access_token;
//    }


    public  static String renewToken(){

        HashMap<String, String> formParms = new HashMap<String, String>();
        formParms.put("client_id", ConfigLoader.getInstance().getClinetID());
        formParms.put("client_secret", ConfigLoader.getInstance().getClinetSecret());
        formParms.put("refresh_token", ConfigLoader.getInstance().getRefreshToken());
        formParms.put("grant_type", ConfigLoader.getInstance().getGrantType());

//        Response response = given().
//                baseUri("https://accounts.spotify.com").
//                contentType(ContentType.URLENC).
//                formParams(formParms).
//                log().all().
//                when().post("/api/token").
//                then().spec(getResponseSpec()).
//                extract().response();

        Response response= RestReusable.postAccount(formParms);

        if(response.statusCode() != 200){
            throw new RuntimeException("ABORT!!!  renew token failed");
        }
        return response.path("access_token");
    }
}
