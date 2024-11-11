package org.example.book.search.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.example.book.search.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class KakaoBookAPI {
    private static final String apiKey = System.getenv("KAKAO_APIKEY");


    public Book readBook(String title) throws IOException {
        System.out.println(title);
        String encodedTitle = URLEncoder.encode(title, "UTF-8");
        String urlString = "https://dapi.kakao.com/v3/search/book?target=title&query=" + encodedTitle;

        URL url = new URL(urlString);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "KakaoAK " + apiKey);
        connection.setRequestProperty("Accept", "application/json");

        int responseCode = connection.getResponseCode();
        System.out.println(responseCode);

        Book book = null;
        if (responseCode == 200) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }

            in.close();

            connection.disconnect();

            JsonObject bookData = JsonParser.parseString(content.toString()).getAsJsonObject();
            JsonObject documents = bookData.get("documents").getAsJsonArray().get(0).getAsJsonObject();
            JsonArray authorArray = documents.getAsJsonArray("authors");
            System.out.println(authorArray);

            StringBuffer authors = new StringBuffer();
            for (int i = 0; i < authorArray.size(); i++) {
                authors.append(authorArray.get(i).getAsString());
                if (i < authorArray.size() - 1) {
                    authors.append(", ");
                }
            }

            String publisher = documents.get("publisher").getAsString();
            String thumbnail = documents.get("thumbnail").getAsString();
            book = new Book(title, authors.toString(), publisher, thumbnail);

        }

        return book;
    }

}
