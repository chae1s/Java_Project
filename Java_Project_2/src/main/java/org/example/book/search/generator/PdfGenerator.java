package org.example.book.search.generator;

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
import org.example.book.search.api.KakaoBookAPI;
import org.example.book.search.model.Book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class PdfGenerator {

    private KakaoBookAPI kakaoBookAPI;

    public PdfGenerator(KakaoBookAPI kakaoBookAPI) {
        this.kakaoBookAPI = kakaoBookAPI;
    }

    public void makeBookPDF(String title) throws IOException {
        Book book = kakaoBookAPI.readBook(title);

        PdfWriter writer = new PdfWriter(new FileOutputStream(title + ".pdf"));

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);

        PdfFont headerFont = PdfFontFactory.createFont("Pretendard-Bold.ttf", PdfEncodings.IDENTITY_H);
        PdfFont bodyFont = PdfFontFactory.createFont("Pretendard-Medium.ttf", PdfEncodings.IDENTITY_H);

        float[] columnWidths = {2, 2, 2, 2};

        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        Cell headerCell1 = new Cell().add(new Paragraph("제목")).setFont(headerFont);
        Cell headerCell2 = new Cell().add(new Paragraph("저자")).setFont(headerFont);
        Cell headerCell3 = new Cell().add(new Paragraph("출판사")).setFont(headerFont);
        Cell headerCell4 = new Cell().add(new Paragraph("이미지")).setFont(headerFont);

        table.addHeaderCell(headerCell1);
        table.addHeaderCell(headerCell2);
        table.addHeaderCell(headerCell3);
        table.addHeaderCell(headerCell4);

        Cell titleCell = new Cell().add(new Paragraph(book.getTitle())).setFont(bodyFont);
        table.addCell(titleCell);

        Cell authorsCell = new Cell().add(new Paragraph(book.getAuthors())).setFont(bodyFont);
        table.addCell(authorsCell);

        Cell publisherCell = new Cell().add(new Paragraph(book.getPublisher())).setFont(bodyFont);
        table.addCell(publisherCell);

        URL url = new URL(book.getThumbnail());
        ImageData imageData = ImageDataFactory.create(url);
        Image image = new Image(imageData);

        Cell thumbnailCell = new Cell().add(image.setAutoScale(true));
        table.addCell(thumbnailCell);

        document.add(table);

        document.close();

    }
}
