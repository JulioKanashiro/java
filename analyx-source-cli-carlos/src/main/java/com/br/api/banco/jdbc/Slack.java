
package com.br.api.banco.jdbc;

import com.br.api.banco.jdbc.controller.SlackController;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.json.JSONObject;

/**
 *
 * @author gibas
 */
public class Slack {
    SlackController webhookFetcher = new SlackController();
    String slackWebhook = webhookFetcher.fetchSlackWebhook();
    
    private static HttpClient client = HttpClient.newHttpClient();
    public static String URL;
    
    public static void sendMessage(JSONObject content) throws IOException, InterruptedException{
        
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(content.toString()))
                .build();
        
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        
        System.out.println(String.format("Status: %s", response.statusCode()));
        System.out.println(String.format("Response: %s", response.body()));
    }
}
