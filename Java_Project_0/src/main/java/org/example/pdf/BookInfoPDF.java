package org.example.pdf;


import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Year;
import java.util.*;

public class BookInfoPDF {
    public static void main(String[] args) {
        HashMap<String, String> bookInfo = new HashMap<>();
        bookInfo.put("title", "모모");
        bookInfo.put("author", "미하엘 엔데");
        bookInfo.put("publisher", "비룡소");
        bookInfo.put("year", "2015-03-13");
        bookInfo.put("price", "11000");
        bookInfo.put("pages", "368");

        try {
            // pdf 생성을 위한 PdfWriter 객체 생성
            PdfWriter writer = new PdfWriter(new FileOutputStream("book_information.pdf"));

            // PdfWriter 객체를 사용하여 PdfDocument 객체 생성
            PdfDocument pdf = new PdfDocument(writer);

            // Document 객체 생성
            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont("Pretendard-Medium.ttf", PdfEncodings.IDENTITY_H);

            document.setFont(font);

            for (String key : bookInfo.keySet()) {
                Paragraph paragraph = new Paragraph(key + ":" + bookInfo.get(key));
                document.add(paragraph);
            }

            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
