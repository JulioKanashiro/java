package com.br.api.banco.jdbc;
import com.br.api.banco.jdbc.controller.SlackController;
import java.io.IOException;
import org.json.JSONObject;


/**
 *
 * @author gibas
 */
public class App {     
    public static void main(String[] args) throws IOException, InterruptedException{    
        SlackController urlSlack = new SlackController();
        String webhook = urlSlack.getSlackWebhook();
        
        Slack.URL = webhook;
        
        JSONObject json = new JSONObject();
        
        json.put("text", "teste final 3.0!");
        
        Slack.sendMessage(json);
    }
}
