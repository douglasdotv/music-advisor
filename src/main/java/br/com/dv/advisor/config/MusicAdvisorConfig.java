package br.com.dv.advisor.config;

public class MusicAdvisorConfig {

    /*
    The values for client ID and client secret shown here are not valid.
    They should be replaced with valid values from an app created on the Spotify Developer Dashboard.

    It should be noted that sharing credentials is a bad practice.
    (But it's ok for this project.)
     */

    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String RESOURCE_PATH = "https://api.spotify.com/v1/browse";
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String AUTH_CODE = "";
    public static String ACCESS_TOKEN = "";
    public static String CLIENT_ID = "29ecc94fa60a49119c453b457423d872";
    public static String CLIENT_SECRET = "6ae1fdbc271d4eb1b48d937cbadf943a";
    public static int ITEMS_PER_PAGE = 5;
    public static String AUTH_URL = SERVER_PATH +
            "/authorize" +
            "?client_id=" + CLIENT_ID +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=code";

}
