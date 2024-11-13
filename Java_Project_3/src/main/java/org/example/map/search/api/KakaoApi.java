package org.example.map.search.api;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.io.entity.EntityUtils;

import java.io.IOException;
import java.net.URLEncoder;

public class KakaoApi {

    private static final String API_KEY =System.getenv("KAKAO_APIKEY");

    public static double[] getAddressCoordinate(String address) throws Exception {
        String apiUrl = "https://dapi.kakao.com/v2/local/search/address.json";
        String encodedAddress = URLEncoder.encode(address, "UTF-8");
        String requestUrl = apiUrl + "?query=" + encodedAddress;

        CloseableHttpClient client = HttpClients.createDefault();

        HttpGet request = new HttpGet(requestUrl);
        request.setHeader("Authorization", "KakaoAK " + API_KEY);

        try (CloseableHttpResponse response = client.execute(request)) {
            String responseBody = EntityUtils.toString(response.getEntity());

            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(responseBody, JsonObject.class);
            JsonArray documents = jsonObject.getAsJsonArray("documents");

            if (documents.size() > 0) {
                JsonObject document = documents.get(0).getAsJsonObject();
                double latitude = document.get("y").getAsDouble();
                double longitude = document.get("x").getAsDouble();

                return new double[]{latitude, longitude};
            } else {
                return null;
            }
        }



    }

}
