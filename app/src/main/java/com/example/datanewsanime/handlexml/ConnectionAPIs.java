package com.example.datanewsanime.handlexml;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionAPIs {
    String url_news = "https://www.animenewsnetwork.com/encyclopedia/reports.xml?id=155";


    public static String getData(String uri){
        BufferedReader br = null;
        try {
            URL url = new URL(uri);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            br = new BufferedReader(new InputStreamReader(huc.getInputStream()));

            String line;

            while ((line = br.readLine())!=null){
                sb.append(line+"\n");
            }

            return sb.toString();

        }catch (Exception e){
            e.printStackTrace();
            return null;
        } finally {
            if(br == null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
