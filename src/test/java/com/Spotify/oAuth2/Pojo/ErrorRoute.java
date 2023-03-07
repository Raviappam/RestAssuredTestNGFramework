package com.Spotify.oAuth2.Pojo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class ErrorRoute {


        @JsonProperty("error")
        private Error error;

        @JsonProperty("error")
        public Error getError() {
            return error;
        }

        @JsonProperty("error")
        public void setError(Error error) {
            this.error = error;
        }

    }

