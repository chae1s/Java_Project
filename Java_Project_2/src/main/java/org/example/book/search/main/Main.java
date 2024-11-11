package org.example.book.search.main;

import org.example.book.search.api.KakaoBookAPI;
import org.example.book.search.generator.PdfGenerator;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        KakaoBookAPI kakaoBookAPI = new KakaoBookAPI();
        PdfGenerator bookController = new PdfGenerator(kakaoBookAPI);

        Scanner scanner = new Scanner(System.in);
        System.out.print("책 제목을 입력하세요 : ");
        String title = scanner.nextLine();

        try {
            bookController.makeBookPDF(title);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

