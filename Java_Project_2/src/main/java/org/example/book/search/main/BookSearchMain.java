package org.example.book.search.main;


import org.example.book.search.api.KakaoBookAPI2;
import org.example.book.search.generator.PdfGenerator2;
import org.example.book.search.model.Book;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class BookSearchMain {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("책 제목을 입력하세요 : ");
        String title = scanner.nextLine();

        try {
            List<Book> books = KakaoBookAPI2.searchBooks(title);
            if (books.isEmpty()) {
                System.out.println("검색 결과가 없습니다.");
            } else {
                String fileName = title + " 도서목록.pdf";
                PdfGenerator2.makeBookPDF(books, fileName);

            }
        } catch (IOException e) {
            System.out.println("에러 발생 : " + e.getMessage());
        }
    }
}
