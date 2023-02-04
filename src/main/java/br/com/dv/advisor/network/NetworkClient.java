package br.com.dv.advisor.network;

import br.com.dv.advisor.config.MusicAdvisorConfig;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NetworkClient {

    public static JsonObject makeGetRequest(String endpoint) {
        try {
            URI uri = new URI(MusicAdvisorConfig.RESOURCE_PATH + endpoint);
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .header("Authorization", "Bearer " + MusicAdvisorConfig.ACCESS_TOKEN)
                    .uri(uri)
                    .GET()
                    .build();

            HttpResponse<String> httpResponse = HttpClient.newHttpClient()
                    .send(httpRequest, HttpResponse.BodyHandlers.ofString());

            if (httpResponse.statusCode() != 200) {
                return null;
            }

            String jsonString = httpResponse.body();

            return JsonParser.parseString(jsonString).getAsJsonObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
