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
import org.example.book.search.model.Book;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class PdfGenerator2 {
    public static void makeBookPDF(List<Book> books, String fileName) throws IOException {

        PdfWriter writer = new PdfWriter(new FileOutputStream(fileName));

        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf, PageSize.A4);


        float[] columnWidths = {2, 2, 2, 2};

        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        table.addHeaderCell(createCell("제목", true));
        table.addHeaderCell(createCell("저자", true));
        table.addHeaderCell(createCell("출판사", true));
        table.addHeaderCell(createCell("이미지", true));


        for (Book book : books) {
            table.addCell(createCell(book.getTitle(), false));

            table.addCell(createCell(book.getAuthors(), false));

            table.addCell(createCell(book.getPublisher(), false));

            ImageData imageData = ImageDataFactory.create(book.getThumbnail());
            Image image = new Image(imageData).setAutoScale(true);

            table.addCell(new Cell().add(image));

        }

        document.add(table);

        document.close();

    }

    private static Cell createCell(String content, boolean isHeader) throws IOException {
        PdfFont headerFont = PdfFontFactory.createFont("Pretendard-Bold.ttf", PdfEncodings.IDENTITY_H);
        PdfFont bodyFont = PdfFontFactory.createFont("Pretendard-Medium.ttf", PdfEncodings.IDENTITY_H);

        Paragraph paragraph = new Paragraph(content);
        Cell cell = new Cell().add(paragraph);
        cell.setPadding(5);
        if (isHeader) {
            cell.setFontSize(14);
            cell.setFont(headerFont);
        } else {
            cell.setFont(bodyFont);
        }

        return cell;
    }
}
