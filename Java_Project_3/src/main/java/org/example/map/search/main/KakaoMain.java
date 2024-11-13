package org.example.map.search.main;

import org.example.map.search.api.KakaoApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class KakaoMain {
    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("주소를 입력하세요 : ");
        try {
            String address = reader.readLine();

            double[] coordinates = KakaoApi.getAddressCoordinate(address);

            if (coordinates != null) {
                System.out.println("주소 : " + address);
                System.out.println("위도 : " + coordinates[0]);
                System.out.println("경도 : " + coordinates[1]);
            } else {
                System.out.println("주소를 찾을 수 없습니다.");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
