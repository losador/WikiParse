package com.company;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	    String link = "https://en.wikipedia.org/w/api.php?action=query&list=search&utf8=&format=json&srsearch=";
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter query in English:");
        String request = sc.next();
        URLEncoder.encode(request, StandardCharsets.UTF_8);
        URL obj = new URL(link + request);
        HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
        connection.setRequestMethod("GET");

        BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();

        String input;

        while ((input = in.readLine()) != null) {
            response.append(input);
        }

        in.close();

        String data = response.toString();
        JsonNode arrNode = new ObjectMapper().readTree(data).get("query").get("search");

        List<String> res = new ArrayList<>();
        if(arrNode.isArray()){
            for(JsonNode objNode : arrNode){
                String text = objNode.get("snippet").toString();
                res.add(text);
            }
        }
        String fin = res.toString();
        String result = fin.replaceAll("<[^>]+>", "");
        System.out.println(result);
    }



}
