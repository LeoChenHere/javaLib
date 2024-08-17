package org.leochen.utils;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.event.KeyValuePair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import static java.util.Objects.isNull;

@Slf4j
public class HttpUtil {

    private String httpRequest(String method, String uri, String accessToken, List<KeyValuePair> pairList, String jsonInputString) {
        try{
            URL url = new URL (uri);
            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestProperty("Authorization","Bearer "+accessToken);
            con.setRequestMethod(method);
            con.setRequestProperty("Content-Type", "application/json");
            con.setRequestProperty("Accept", "application/json");
            con.setDoOutput(true);
            if( !isNull(jsonInputString) ){
                try(OutputStream os = con.getOutputStream()) {
                    byte[] input = jsonInputString.getBytes("utf-8");
                    os.write(input, 0, input.length);
                }
            }
            try(BufferedReader br = new BufferedReader(
                    new InputStreamReader(con.getInputStream(), "utf-8"))) {
                StringBuilder response = new StringBuilder();
                String responseLine = null;
                while ((responseLine = br.readLine()) != null) {
                    response.append(responseLine.trim());
                }
                log.info(response.toString());
                return response.toString();
            }
        }catch (IOException exception){
            exception.printStackTrace();
        }
        return null;
    }

    private String httpRequest(String method, String uri, String accessToken, String jsonInputString) {
        return httpRequest(method, uri, accessToken, null, jsonInputString);
    }

    public String httpPost(String uri, String accessToken, String jsonInputString) {
        return httpRequest("POST", uri, accessToken, jsonInputString);
    }

    public String httpGet(String uri, String accessToken, String jsonInputString) {
        return httpRequest("GET", uri, accessToken, jsonInputString);
    }

    public String httpPost(String uri, String accessToken, List<KeyValuePair> pairList, String jsonInputString) {
        return httpRequest("POST", uri, accessToken, pairList, jsonInputString);
    }

    public String httpGet(String uri, String accessToken, List<KeyValuePair> pairList, String jsonInputString) {
        return httpRequest("GET", uri, accessToken, pairList, jsonInputString);
    }
}
