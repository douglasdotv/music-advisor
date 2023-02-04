package br.com.dv.advisor.oauth2;

import br.com.dv.advisor.config.MusicAdvisorConfig;
import br.com.dv.advisor.view.MusicAdvisorView;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Objects;

public class Authorization {

    private final MusicAdvisorView view;
    private static HttpServer server;
    private static String code;

    public Authorization(MusicAdvisorView view) {
        this.view = view;
    }

    public void createHttpServer() {
        try {
            server = HttpServer.create();
            server.bind(new InetSocketAddress((8080)), 0);
        } catch (IOException e) {
            e.printStackTrace();
        }

        server.start();

        server.createContext("/", exchange -> {
            String query = exchange.getRequestURI().toString();
            String response;
            if (query.contains("code=")) {
                code = query.split("=")[1];
                response = "Got the code. Return back to your program.";
            } else {
                response = "Authorization code not found. Try again.";
            }
            exchange.sendResponseHeaders(200, response.length());
            exchange.getResponseBody().write(response.getBytes());
            exchange.getResponseBody().close();
        });

        view.displayWaitingForCodeMsg();
        while (code == null) {
            sleep();
        }

        MusicAdvisorConfig.AUTH_CODE = code;

        server.stop(5);
    }

    public void getAccessToken() {
        view.displayAuthMakingHttpRequestMsg();

        HttpRequest request = HttpRequest.newBuilder()
                .header("Content-Type", "application/x-www-form-urlencoded")
                .uri(URI.create(MusicAdvisorConfig.SERVER_PATH + "/api/token"))
                .POST(HttpRequest.BodyPublishers.ofString(
                        "grant_type=authorization_code" +
                                "&code=" + MusicAdvisorConfig.AUTH_CODE +
                                "&client_id=" + MusicAdvisorConfig.CLIENT_ID +
                                "&client_secret=" + MusicAdvisorConfig.CLIENT_SECRET +
                                "&redirect_uri=" + MusicAdvisorConfig.REDIRECT_URI))
                .build();

        try {
            HttpClient httpClient = HttpClient.newBuilder().build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            if (response != null && response.body().contains("access_token")) {
                parseAccessToken(response.body());
            }
            view.displayAuthResponseMsg(Objects.requireNonNull(response).body());
        } catch (InterruptedException | IOException e) {
            System.out.println("Error");
        }
    }

    private static void sleep() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void parseAccessToken(String body) {
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        MusicAdvisorConfig.ACCESS_TOKEN = jsonObject.get("access_token").getAsString();
    }

}
