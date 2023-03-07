package com.Spotify.oAuth2.api.applicationApi;

import Utils.ConfigLoader;
import com.Spotify.oAuth2.Pojo.Playlist;
import com.Spotify.oAuth2.api.RestReusable;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static com.Spotify.oAuth2.api.SpecBuilder.getRequestSpec;
import static com.Spotify.oAuth2.api.SpecBuilder.getResponseSpec;
import static com.Spotify.oAuth2.api.TokenManager.renewToken;
import static io.restassured.RestAssured.given;

public class Playtrackapi {

//    static  String access_token = "BQDyvhOBLgTchdEiDbibsXhCDYUE7pU7Z53H2lSfiT-c30HuumKi-MEMXVpO-0PlRP3O1xZfUp_PU6D-Ym6i5W0mzg3YqucRl4FgW-P8q5jkaEdjhdz47B3e__-jfwvdZ8ytTWtcnO9oVhuk1GytiszAHUFKEZzb3Cc2a1OcSyZ3uPnnebORYkDGRBGKZF5Vgz7j4Y9BCi8MPHf5oLKwxzK3-BKiYUj9pOUzCV7QANyan6a3lkMkgdPINj5g0oAAATDGfLDCig2yWGSBPA";



//    public static Response post(Playlist requestplaylist){
//        return RestReusable.post("/users/31a5hxq5yggwa3vks626yisvow6y/playlists",renewToken(),requestplaylist);
//    }

    @Step
    public static Response post(Playlist requestplaylist){
        return RestReusable.post("/users/" + ConfigLoader.getInstance().getUserID() + "/playlists",renewToken(),requestplaylist);
    }

    @Step
    public static Response get(String playlistId){
        return RestReusable.get("/playlists/" + playlistId,renewToken());

    }
    @Step
    public static Response update(String playlistId, Playlist  requestplaylist){
        return RestReusable.update("/playlists/" + playlistId,renewToken(),requestplaylist);



    }

}
