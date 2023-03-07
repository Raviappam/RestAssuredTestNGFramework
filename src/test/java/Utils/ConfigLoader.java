package Utils;

import io.qameta.allure.Step;

import java.util.Properties;

public class ConfigLoader {

    private  final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){

        properties = PropertyUtils.propertyLoader("src/test/resorces/Config.Properties");
    }

    public static ConfigLoader getInstance(){
        if(configLoader==null){
            configLoader = new ConfigLoader();
        }

        return configLoader;
    }


    public String getClinetID(){

        String prop = properties.getProperty("client_id");
        if(prop!=null)return prop;
        else throw new RuntimeException("property client_id is not specified in the config.properties file");

    }

    public String getClinetSecret(){

        String prop = properties.getProperty("client_secret");
        if(prop!=null)return prop;
        else throw new RuntimeException("property client_secret is not specified in the config.properties file");

    }

    public String getRefreshToken(){

        String prop = properties.getProperty("refresh_token");
        if(prop!=null)return prop;
        else throw new RuntimeException("property refresh_token is not specified in the config.properties file");

    }
    public String getGrantType(){

        String prop = properties.getProperty("grant_type");
        if(prop!=null)return prop;
        else throw new RuntimeException("property grant_typeis not specified in the config.properties file");

    }
    public String getUserID(){

        String prop = properties.getProperty("user_id");
        if(prop!=null)return prop;
        else throw new RuntimeException("property user_id is not specified in the config.properties file");

    }

}
