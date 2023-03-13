package com.Spotify.oAuth2.Test;

import Utils.ConfigLoader;
import com.Spotify.oAuth2.Pojo.Error;
import com.Spotify.oAuth2.Pojo.ErrorRoute;
import com.Spotify.oAuth2.Pojo.Playlist;
import com.Spotify.oAuth2.api.applicationApi.Playtrackapi;
import io.qameta.allure.*;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static com.Spotify.oAuth2.api.SpecBuilder.getRequestSpec;
import static com.Spotify.oAuth2.api.SpecBuilder.getResponseSpec;
import static  io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


@Epic("Spotify 0auth 2.0")//To add the EPic
@Feature("Play list API")//To add the Feature


public class PlayListTests extends BaseTest{

    //private static final Logger logger = LogManager.getLogger(PlayListTests.class);
    @Story("Create play list story")//To add the Story
    @Description("this validates creation of play list")// to add the description in the reports
    @Test(description = "Create Play List APi") // to display different name than method name use this allure annotation

    public void CreatePlayList() {

        Playlist requestplaylist = new Playlist().
                setName("New Playlist").
                setDescription("New playlist description").
                setPublic(false);
        Allure.addAttachment("Request Data", requestplaylist.toString());

        Response response = Playtrackapi.post(requestplaylist);
        Allure.addAttachment("Response Code", Integer.toString(response.statusCode()));
        assertThat(response.statusCode(), equalTo(201));

        Playlist responseplaylist = response.as(Playlist.class);
        Allure.addAttachment("Response Body", response.getBody().asString());


        Allure.addAttachment("Response Playlist Name", responseplaylist.getName());
        Allure.addAttachment("Request Playlist Name", requestplaylist.getName());
        assertThat(responseplaylist.getName(), equalTo(requestplaylist.getName()));

        Allure.addAttachment("Response Playlist Description", responseplaylist.getDescription());
        Allure.addAttachment("Request Playlist Description", requestplaylist.getDescription());
        assertThat(responseplaylist.getDescription(), equalTo(requestplaylist.getDescription()));

        Allure.addAttachment("Response Playlist Public Flag", Boolean.toString(responseplaylist.getPublic()));
        Allure.addAttachment("Request Playlist Public Flag", Boolean.toString(requestplaylist.getPublic()));
        assertThat(responseplaylist.getPublic(), equalTo(requestplaylist.getPublic()));

    }

    @Test
    public void GetPlayList(){

        Playlist requestplaylist = new Playlist().
        setName("Updated Playlist Name").
        setDescription("Updated playlist description").
        setPublic(false);
        Allure.addAttachment("Request Data", requestplaylist.toString());

        Response response = Playtrackapi.get(ConfigLoader.getInstance().getPlayListID());
        Allure.addAttachment("Response Code", Integer.toString(response.statusCode()));
        assertThat(response.statusCode(),equalTo(200));
        Playlist responseplaylist = response.as(Playlist.class);
        Allure.addAttachment("Response Body", response.getBody().asString());

        Allure.addAttachment("Response Playlist Name", responseplaylist.getName());
        Allure.addAttachment("Request Playlist Name", requestplaylist.getName());
        assertThat(responseplaylist.getName(), equalTo(requestplaylist.getName()));

        Allure.addAttachment("Response Playlist Description", responseplaylist.getDescription());
        Allure.addAttachment("Request Playlist Description", requestplaylist.getDescription());
        assertThat(responseplaylist.getDescription(), equalTo(requestplaylist.getDescription()));

        Allure.addAttachment("Response Playlist Public Flag", Boolean.toString(responseplaylist.getPublic()));
        Allure.addAttachment("Request Playlist Public Flag", Boolean.toString(requestplaylist.getPublic()));
        assertThat(responseplaylist.getPublic(), equalTo(requestplaylist.getPublic()));

    }

    @Test
    public void UpdatePlayList() {

        Playlist requestplaylist = new Playlist().
                setName("Updated Playlist Name").
                setDescription("Updated playlist description").
                setPublic(false);
        Allure.addAttachment("Request Data", requestplaylist.toString());

        Response response = Playtrackapi.update(ConfigLoader.getInstance().getPlayListID(), requestplaylist);

        Allure.addAttachment("Response Code", Integer.toString(response.statusCode()));
        assertThat(response.statusCode(), equalTo(200));


    }

    @Story("Create play list story")
    @Test
    public void Negetive_CreatePlayList() {

        Playlist requestplaylist = new Playlist().
                setName("").
                setDescription("Updated playlist description").
                setPublic(false);
        Allure.addAttachment("Request Data", requestplaylist.toString());

        Response response = Playtrackapi.post(requestplaylist);
        Allure.addAttachment("Response Code", Integer.toString(response.statusCode()));
        assertThat(response.statusCode(), equalTo(400));

        ErrorRoute errorRoute = response.as(ErrorRoute.class);
        Allure.addAttachment("Response Body", response.getBody().asString());

        Allure.addAttachment("Error Status", Integer.toString(errorRoute.getError().getStatus()));
        assertThat(errorRoute.getError().getStatus(), equalTo(400));

        Allure.addAttachment("Error Message", errorRoute.getError().getMessage());
        Allure.addAttachment("Error Message", errorRoute.getError().getMessage());
        assertThat(errorRoute.getError().getMessage(), equalTo("Missing required field: name"));

    }



}






