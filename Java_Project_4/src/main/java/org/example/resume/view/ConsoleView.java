package org.example.resume.view;

import org.example.resume.model.Career;
import org.example.resume.model.Education;
import org.example.resume.model.PersonalInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ConsoleView {

    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public PersonalInfo getPersonalInfo() throws IOException {
        System.out.print("사진 파일명을 입력하세요. : ");
        String fileName = reader.readLine();

        System.out.print("이름을 입력하세요 : ");
        String name = reader.readLine();

        System.out.print("이메일을 입력하세요 : ");
        String email = reader.readLine();

        System.out.print("주소를 입력하세요 : ");
        String address = reader.readLine();

        System.out.print("전화번호를 입력하세요 : ");
        String phone = reader.readLine();

        System.out.print("생년월일을 입력하세요 (예: 1990-01-01) : ");
        String birth = reader.readLine();

        return new PersonalInfo(fileName, name, email, address, phone, birth);
    }

    public List<Career> getCareer() throws IOException {
        List<Career> careers = new ArrayList<>();

        while (true) {
            System.out.println("경력 정보를 입력하세요 (종료는 q)");
            System.out.println("근무기간 근무처 담당업무 근속연수");

            String career = reader.readLine();
            if (career.equals("q")) {
                break;
            } else {
                String[] careerInfo = career.split(" ");
                if (careerInfo.length == 4) {
                    careers.add(new Career(careerInfo[0], careerInfo[1], careerInfo[2], careerInfo[3]));
                }
            }
        }

        return careers;
    }

    public List<Education> getEducation() throws IOException {
        List<Education> educations = new ArrayList<>();
        while (true) {
            System.out.println("학력 정보를 입력하세요 (종료는 q)");
            System.out.println("졸업년도 학교명 전공 졸업여부");

            String education = reader.readLine();
            if (education.equals("q")) {
                break;
            } else {
                String[] eduInfo = education.split(" ");
                if (eduInfo.length == 4) {
                    educations.add(new Education(eduInfo[0], eduInfo[1], eduInfo[2], eduInfo[3]));
                }
            }
        }

        return educations;

    }

    public String getSelfIntroduction() throws IOException {
        System.out.println("자기소개서를 입력하세요. 여러 줄을 입력하려면 빈 줄을 입력하세요.");
        StringBuffer buffer = new StringBuffer();
        String line;
        while (!(line = reader.readLine()).isEmpty()) {
            buffer.append(line);
        }

        return buffer.toString();
    }


}
