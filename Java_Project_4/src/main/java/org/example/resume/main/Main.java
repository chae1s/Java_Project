package org.example.resume.main;


import org.example.resume.excel.ResumeController;
import org.example.resume.view.ConsoleView;

public class Main {
    public static void main(String[] args) {
        ConsoleView consoleView = new ConsoleView();

        ResumeController writer = new ResumeController(consoleView);

        try {
            writer.createExcel();


        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
}
