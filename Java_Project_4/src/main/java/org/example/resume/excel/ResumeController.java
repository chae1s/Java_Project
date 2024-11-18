package org.example.resume.excel;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.example.resume.model.Career;
import org.example.resume.model.Education;
import org.example.resume.model.PersonalInfo;
import org.example.resume.view.ConsoleView;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

public class ResumeController {
    private ConsoleView consoleView;

    public ResumeController(ConsoleView consoleView) {
        this.consoleView = consoleView;
    }

    public void createExcel() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        createResumeSheet(workbook);
        createIntroduceSheet(workbook);

        FileOutputStream outputStream = new FileOutputStream(new File("이력서.xlsx"));

        workbook.write(outputStream);

        workbook.close();

    }

    public void createResumeSheet(XSSFWorkbook workbook) throws Exception {
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

        // 이미지 크기 조절
        int newWidth = (int) (35 * 2.83465);
        int newHeight = (int) (45 * 2.83465);

        BufferedImage originalImage = ImageIO.read(inputStream);

        Image image = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);

        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_4BYTE_ABGR);

        Graphics2D graphics2D = resizedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();

        // 이미지를 바이트 배열로 변환
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(resizedImage, "png", baos);

        byte[] imageBytes = baos.toByteArray();
        int pictureIndex = workbook.addPicture(imageBytes, Workbook.PICTURE_TYPE_PNG);

        // 이미지 삽입
        XSSFDrawing drawing = (XSSFDrawing) resumeSheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = new XSSFClientAnchor(0, 0, 0, 0, 0, 1, 1, 2);
        drawing.createPicture(anchor, pictureIndex);

        personalBody.setHeightInPoints(newHeight * 72 / 96);
        int columnWidth = (int) Math.floor(((float) newWidth / (float) 8) * 256);
        resumeSheet.setColumnWidth(0, columnWidth);

        personalBody.createCell(1).setCellValue(personalInfo.getName());
        personalBody.createCell(2).setCellValue(personalInfo.getEmail());
        personalBody.createCell(3).setCellValue(personalInfo.getAddress());
        personalBody.createCell(4).setCellValue(personalInfo.getPhone());
        personalBody.createCell(5).setCellValue(personalInfo.getBirth());

        Row educationHeader = resumeSheet.createRow(startRow++);
        educationHeader.createCell(0).setCellValue("졸업년도");
        educationHeader.createCell(1).setCellValue("학교명");
        educationHeader.createCell(2).setCellValue("전공");
        educationHeader.createCell(3).setCellValue("졸업여부");

        List<Education> educations = consoleView.getEducation();
        for (Education education : educations) {
            Row educationBody = resumeSheet.createRow(startRow++);

            educationBody.createCell(0).setCellValue(education.getGraduationYear());
            educationBody.createCell(1).setCellValue(education.getSchoolName());
            educationBody.createCell(2).setCellValue(education.getMajor());
            educationBody.createCell(3).setCellValue(education.getGraduationStatus());
        }

        Row careerHeader = resumeSheet.createRow(startRow++);
        careerHeader.createCell(0).setCellValue("근무기간");
        careerHeader.createCell(1).setCellValue("근무처");
        careerHeader.createCell(2).setCellValue("담당업무");
        careerHeader.createCell(3).setCellValue("근속연수");

        List<Career> careers = consoleView.getCareer();
        for (Career career : careers) {
            Row careerBody = resumeSheet.createRow(startRow++);

            careerBody.createCell(0).setCellValue(career.getPeriod());
            careerBody.createCell(1).setCellValue(career.getCompanyName());
            careerBody.createCell(2).setCellValue(career.getJobDuties());
            careerBody.createCell(3).setCellValue(career.getJobTenure());
        }

    }

    public void resizedColumn(Sheet sheet, int column) {
        for (int i = 0; i < column; i++) {
            sheet.autoSizeColumn(i);
        }
    }

    public void createIntroduceSheet(XSSFWorkbook workbook) throws IOException {
        Sheet introduceSheet = workbook.createSheet("자기소개서");

        CellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setWrapText(true);


        Row body = introduceSheet.createRow(0);
        Cell cell = body.createCell(0);
        cell.setCellValue(consoleView.getSelfIntroduction());
        cell.setCellStyle(cellStyle);

        introduceSheet.autoSizeColumn(0);
    }

}
