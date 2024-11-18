package org.example.resume.main;


import org.example.resume.excel.ExcelWriter;
import org.example.resume.model.Career;
import org.example.resume.model.Education;
import org.example.resume.model.PersonalInfo;
import org.example.resume.view.ConsoleView;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();

        ExcelWriter writer = new ExcelWriter(consoleView);

        try {
            writer.createExcel();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
