package org.example.pdf;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class PdfFile {
    public static void main(String[] args) throws IOException {
        String dest = "book_table.pdf";
        new PdfFile().createPdf(dest);

    }

    public void createPdf(String dest) throws IOException {
        List<Map<String, String>> books = createDummyData();

        PdfWriter writer = new PdfWriter(dest);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        PdfFont headerFont = PdfFontFactory.createFont("Pretendard-Bold.ttf", PdfEncodings.IDENTITY_H);
        PdfFont bodyFont = PdfFontFactory.createFont("Pretendard-Medium.ttf", PdfEncodings.IDENTITY_H);

        float[] columnWidths = {1, 2, 2, 2, 2, 2};

        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        Cell headerCell1 = new Cell().add(new Paragraph("순번")).setFont(headerFont);
        Cell headerCell2 = new Cell().add(new Paragraph("제목")).setFont(headerFont);
        Cell headerCell3 = new Cell().add(new Paragraph("저자")).setFont(headerFont);
        Cell headerCell4 = new Cell().add(new Paragraph("출판사")).setFont(headerFont);
        Cell headerCell5 = new Cell().add(new Paragraph("출판일")).setFont(headerFont);
        Cell headerCell6 = new Cell().add(new Paragraph("이미지")).setFont(headerFont);

        table.addHeaderCell(headerCell1);
        table.addHeaderCell(headerCell2);
        table.addHeaderCell(headerCell3);
        table.addHeaderCell(headerCell4);
        table.addHeaderCell(headerCell5);
        table.addHeaderCell(headerCell6);

        int rowNum = 1;
        for (Map<String, String> book : books) {
            String title = book.get("title");
            String author = book.get("author");
            String publisher = book.get("publisher");
            String year = book.get("year");
            String thumbnail = book.get("thumbnail");

            Cell rowNumCell = new Cell().add(new Paragraph(String.valueOf(rowNum))).setFont(bodyFont);
            table.addCell(rowNumCell);
            rowNum++;

            Cell titleCell = new Cell().add(new Paragraph(title)).setFont(bodyFont);
            table.addCell(titleCell);

            Cell authorCell = new Cell().add(new Paragraph(author)).setFont(bodyFont);
            table.addCell(authorCell);

            Cell publisherCell = new Cell().add(new Paragraph(publisher)).setFont(bodyFont);
            table.addCell(publisherCell);

            Cell yearCell = new Cell().add(new Paragraph(year)).setFont(bodyFont);
            table.addCell(yearCell);

            ImageData imageData = ImageDataFactory.create(new File(thumbnail).toURI().toURL());
            Image image = new Image(imageData);

            Cell imageCell = new Cell().add(image.setAutoScale(true));
            table.addCell(imageCell);
        }

        document.add(table);

        document.close();

    }

    private static List<Map<String, String>> createDummyData() {
        List<Map<String, String>> books = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("책 개수 입력");
        int bookCount = scanner.nextInt();
        scanner.nextLine();

        for (int i = 1; i <= bookCount; i++) {
            Map<String, String> book = new HashMap<>();

            System.out.printf("%d번째 책 정보 입력\n", i);

            System.out.println("책 제목");
            book.put("title", scanner.nextLine());
            System.out.println("작가");
            book.put("author", scanner.nextLine());
            System.out.println("출판사");
            book.put("publisher", scanner.nextLine());
            System.out.println("출판일");
            book.put("year", scanner.nextLine());
            System.out.println("썸네일 URL");
            book.put("thumbnail", scanner.nextLine());

            books.add(book);
        }

        scanner.close();

        return books;
    }
}
