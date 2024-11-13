package org.example.resume.main;


import org.example.resume.model.Career;
import org.example.resume.model.Education;
import org.example.resume.model.PersonalInfo;
import org.example.resume.view.ConsoleView;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        ConsoleView view = new ConsoleView();

        try {
            PersonalInfo personalInfo = view.getPersonalInfo();

            List<Career> careers = view.getCareer();

            List<Education> educations = view.getEducation();

            String introduction = view.getSelfIntroduction();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}
