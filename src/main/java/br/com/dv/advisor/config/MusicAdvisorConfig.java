package br.com.dv.advisor.config;

public class MusicAdvisorConfig {

    public static String SERVER_PATH = "https://accounts.spotify.com";
    public static String RESOURCE_PATH = "https://api.spotify.com/v1/browse";
    public static String REDIRECT_URI = "http://localhost:8080";
    public static String CLIENT_ID = "29ecc94fa60a49119c453b457423d872";
    public static String CLIENT_SECRET = "5d3fdb1430da454b8a5c3dd145f2aee8"; // (Fake CLIENT_SECRET)
    public static String ACCESS_TOKEN = "";
    public static String AUTH_CODE = "";
    public static String AUTH_URL = SERVER_PATH +
            "/authorize" +
            "?client_id=" + CLIENT_ID +
            "&redirect_uri=" + REDIRECT_URI +
            "&response_type=code";

}
