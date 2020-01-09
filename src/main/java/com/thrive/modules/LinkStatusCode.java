package com.thrive.modules;

//import io.restassured.response.Response;

import java.net.HttpURLConnection;
import java.net.URL;

public class LinkStatusCode {
    HttpURLConnection connect;
    public static int connection(String url) {
        int status = 0;
        try {
            HttpURLConnection connect = (HttpURLConnection)(new URL(url)).openConnection();
            connect.connect();
            status = connect.getResponseCode();
            System.out.println(status);

        } catch (Exception e) {
            e.getCause();
        }
        return status;
    }
}
