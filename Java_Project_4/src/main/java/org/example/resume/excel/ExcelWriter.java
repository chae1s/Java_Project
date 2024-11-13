package org.example.resume.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.example.resume.model.Career;
import org.example.resume.model.Education;
import org.example.resume.model.PersonalInfo;
import org.example.resume.view.ConsoleView;

import java.io.*;
import java.util.List;

public class ExcelWriter {

    public void createResumeSheet() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        ConsoleView consoleView = new ConsoleView();
        PersonalInfo personalInfo = consoleView.getPersonalInfo();

        // 첫번째 이력서 시트 생성
        Sheet resumeSheet = workbook.createSheet("이력서");

        // 개인 이력 입력
        int startRow = 0;
        Row personalHeader = resumeSheet.createRow(startRow++);
        personalHeader.createCell(0).setCellValue("사진");
        personalHeader.createCell(1).setCellValue("이름");
        personalHeader.createCell(2).setCellValue("이메일");
        personalHeader.createCell(3).setCellValue("주소");
        personalHeader.createCell(4).setCellValue("전화번호");
        personalHeader.createCell(5).setCellValue("생년월일");

        Row personalBody = resumeSheet.createRow(startRow++);

        // 이미지 불러오기
        InputStream inputStream = new FileInputStream(personalInfo.getFileName());


        byte[] imageBytes = IOUtils.toByteArray(inputStream);
        int pictureIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

        int newWidth = (int) (35 * 2.83465);
        int newHeight = (int) (45 * 2.83465);

        XSSFCreationHelper helper = workbook.getCreationHelper();
        XSSFDrawing drawing = (XSSFDrawing) resumeSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(0);
        anchor.setRow1(1);

        personalBody.setHeightInPoints(newHeight * 72 / 96);
        int columnWidth = (int) Math.floor(((float) newWidth / (float) 8) * 256);
        resumeSheet.setColumnWidth(0, columnWidth);

        XSSFPicture picture = drawing.createPicture(anchor, pictureIndex);
        picture.resize();

        personalBody.createCell(1).setCellValue(personalInfo.getName());
        personalBody.createCell(2).setCellValue(personalInfo.getEmail());
        personalBody.createCell(3).setCellValue(personalInfo.getAddress());
        personalBody.createCell(4).setCellValue(personalInfo.getPhone());
        personalBody.createCell(5).setCellValue(personalInfo.getBirth());




        FileOutputStream outputStream = new FileOutputStream(new File("resume.xlsx"));

        workbook.write(outputStream);

        workbook.close();
    }

}
