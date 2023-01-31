package br.com.dv.advisor.oauth2;

import br.com.dv.advisor.MusicAdvisorConfig;
import br.com.dv.advisor.MusicAdvisorView;
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

    public Authorization(MusicAdvisorView view) {
        this.view = view;
    }

    public void createHttpServer() {
        HttpServer server;

        try {
            server = HttpServer.create();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            server.bind(new InetSocketAddress(8080), 0);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        server.start();

        view.displayAuthWaitingMsg(MusicAdvisorConfig.AUTH_URL);

        server.createContext("/",
                exchange -> {
                    String query = exchange.getRequestURI().getQuery();
                    MusicAdvisorConfig.AUTH_CODE = query.substring(5);

                    if (MusicAdvisorConfig.AUTH_CODE.isEmpty()) {
                        // Authorization code not found
                        String response = "Authorization code not found. Try again.";
                        exchange.sendResponseHeaders(200, response.length());
                        exchange.getResponseBody().write(response.getBytes());
                    } else {
                        // Got the code
                        String response = "Got the code. Return back to your program.";
                        exchange.sendResponseHeaders(200, response.length());
                        exchange.getResponseBody().write(response.getBytes());
                        view.displayAuthCodeReceivedMsg();
                        // Shut down the server
                        server.stop(0);

                        // POST Request
                        getAccessToken();
                    }
                }
        );
    }

    private void getAccessToken() {
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

    private void parseAccessToken(String body) {
        JsonObject jsonObject = JsonParser.parseString(body).getAsJsonObject();
        MusicAdvisorConfig.ACCESS_TOKEN = jsonObject.get("access_token").getAsString();
    }

}
