package org.example.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.model.Member;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelWriter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Member> members = new ArrayList<>();

        while (true) {
            System.out.print("이름 입력");
            String name = scanner.nextLine();
            if (name.equals("quit")) {
                break;
            }

            System.out.print("나이 입력");
            int age = scanner.nextInt();
            scanner.nextLine();

            System.out.print("생년월일 입력");
            String birthDate = scanner.nextLine();

            System.out.print("전화번호 입력");
            String phone = scanner.nextLine();

            System.out.print("주소 입력");
            String address = scanner.nextLine();

            System.out.print("결혼 여부");
            boolean isMarried = scanner.nextBoolean();
            scanner.nextLine();

            Member member = new Member(name, age, birthDate, phone, address, isMarried);

            members.add(member);
        }

        scanner.close();


        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            Sheet sheet = workbook.createSheet("회원정보");

            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("이름");
            headerRow.createCell(1).setCellValue("나이");
            headerRow.createCell(2).setCellValue("생년월일");
            headerRow.createCell(3).setCellValue("전화번호");
            headerRow.createCell(4).setCellValue("주소");
            headerRow.createCell(5).setCellValue("결혼여부");

            for (int i = 0; i < members.size(); i++) {
                Member member = members.get(i);

                Row row = sheet.createRow(i + 1);
                row.createCell(0).setCellValue(member.getName());
                row.createCell(1).setCellValue(member.getAge());
                row.createCell(2).setCellValue(member.getBirthdate());
                row.createCell(3).setCellValue(member.getPhone());
                row.createCell(4).setCellValue(member.getAddress());
                row.createCell(5).setCellValue(member.isMarried());
            }

            String fileName = "member.xlsx";

            FileOutputStream outputStream = new FileOutputStream(new File(fileName));

            workbook.write(outputStream);

            workbook.close();

            System.out.printf("엑셀 파일 저장. : %s\n", fileName);
        } catch (IOException e) {
            System.out.println("엑셀 파일 저장 중 오류 발생");
            e.printStackTrace();
        }
    }
}
