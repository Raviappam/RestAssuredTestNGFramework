package com.Spotify.oAuth2.Test;

import com.Spotify.oAuth2.Pojo.Error;
import com.Spotify.oAuth2.Pojo.ErrorRoute;
import com.Spotify.oAuth2.Pojo.Playlist;
import com.Spotify.oAuth2.api.applicationApi.Playtrackapi;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.Spotify.oAuth2.api.SpecBuilder.getRequestSpec;
import static com.Spotify.oAuth2.api.SpecBuilder.getResponseSpec;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@Epic("Spotify 0auth 2.0")//To add the EPic
@Feature("Play list API")//To add the Feature
public class PlayListTests extends BaseTest{

    @Story("Create play list story")//To add the Story
    @Description("this validates creation of play list")// to add the description in the reports
    @Test(description = "Create Play List APi") // to display different name than method name use this allure annotation
    public void CreatePlayList(){

        Playlist requestplaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);

//        String Payload = "{\n" +
//                "  \"name\": \"New Playlist\",\n" +
//                "  \"description\": \"New playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";

        Response response=  Playtrackapi.post(requestplaylist);
        assertThat(response.statusCode(),equalTo(201));
        Playlist responseplaylist = response.as(Playlist.class);

//        Playlist responseplaylist = given(getRequestSpec()).
//                body(requestplaylist).
//                when().post("/users/31a5hxq5yggwa3vks626yisvow6y/playlists").
//                then().spec(getResponseSpec()).
//                assertThat().statusCode(201).
//                extract().
//                response().
//                as(Playlist.class);


        assertThat(responseplaylist.getName(), equalTo(requestplaylist.getName()));
        assertThat(responseplaylist.getDescription(), equalTo(requestplaylist.getDescription()));
        assertThat(responseplaylist.getPublic(), equalTo(requestplaylist.getPublic()));

}

    @Test
    public void GetPlayList(){

        Playlist requestplaylist = new Playlist().
        setName("Updated Playlist Name").
        setDescription("Updated playlist description").
        setPublic(false);

        Response response = Playtrackapi.get("6qwGEIp5wwKGWluBO3VHzm");
        assertThat(response.statusCode(),equalTo(200));
        Playlist responseplaylist = response.as(Playlist.class);

//        Playlist responseplaylist = given(getRequestSpec()).
//
//                when().get("/playlists/6qwGEIp5wwKGWluBO3VHzm").
//                then().spec(getResponseSpec()).
//                assertThat().statusCode(200).
//                extract().
//                response().
//                as(Playlist.class);
        assertThat(responseplaylist.getName(), equalTo(requestplaylist.getName()));
        assertThat(responseplaylist.getDescription(), equalTo(requestplaylist.getDescription()));
        assertThat(responseplaylist.getPublic(), equalTo(requestplaylist.getPublic()));

    }

    @Test
    public void UpdatePlayList() {

        Playlist requestplaylist = new Playlist().
                setName("Updated Playlist Name").
                setDescription("Updated playlist description").
                setPublic(false);

//        String payload = "{\n" +
//                "  \"name\": \"Updated Playlist Name\",\n" +
//                "  \"description\": \"Updated playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";

        Response response = Playtrackapi.update("6qwGEIp5wwKGWluBO3VHzm", requestplaylist);
        assertThat(response.statusCode(),equalTo(200));


//       given(getRequestSpec()).
//                body(requestplaylist).
//                when().put("/playlists/6qwGEIp5wwKGWluBO3VHzm").
//                then().spec(getResponseSpec()).
//                assertThat().statusCode(200);


    }

    @Story("Create play list story")
    @Test
    public void Negetive_CreatePlayList(){

        Playlist requestplaylist = new Playlist().
                setName("").
                setDescription("Updated playlist description").
                setPublic(false);

//        String Payload = "{\n" +
//                "  \"name\": \"\",\n" +
//                "  \"description\": \"New playlist description\",\n" +
//                "  \"public\": false\n" +
//                "}";

        Response response=  Playtrackapi.post(requestplaylist);
        assertThat(response.statusCode(),equalTo(400));
        ErrorRoute errorRoute = response.as(ErrorRoute.class);


//       ErrorRoute errorRoute = given(getRequestSpec()).
//                body(requestplaylist).
//                when().post("/users/31a5hxq5yggwa3vks626yisvow6y/playlists").
//                then().spec(getResponseSpec()).
//                assertThat().statusCode(400).
//                extract().
//                response().as(ErrorRoute.class);

        assertThat(errorRoute.getError().getStatus(),equalTo(400));
        assertThat(errorRoute.getError().getMessage(),equalTo("Missing required field: name"));

//                body("error.status",equalTo(400),"error.message",equalTo("Missing required field: name") );

    }



}






