package org.example.pet.main;

import org.example.pet.controller.CustomerController;
import org.example.pet.controller.MedicalRecordController;
import org.example.pet.model.Customer;
import org.example.pet.model.MedicalRecord;
import org.example.pet.view.ConsoleView;

import java.util.Scanner;

public class PetMain {
    public static void main(String[] args) {
        MedicalRecordController medicalRecordController = new MedicalRecordController();
        CustomerController customerController = new CustomerController(medicalRecordController);
        ConsoleView view = new ConsoleView();

        while (true) {
            System.out.println("===애완동물진료관리시스템===");
            System.out.println("1. 신규 고객 정보 입력");
            System.out.println("2. 진료 기록 저장");
            System.out.println("3. 진료 기록 조회");
            System.out.println("4. 진료 기록 삭제");
            System.out.println("5. 종료");
            System.out.print("원하는 기능을 선택하세요:");

            Scanner scanner = new Scanner(System.in);
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    Customer newCustomer = view.getCustomerInfo();
                    String phone = newCustomer.getPhone();

                    if (customerController.isPhoneExist(phone)) {
                        view.printMessage("이미 등록된 전화번호입니다.");
                        continue;
                    }

                    customerController.addCustomer(newCustomer);
                    view.printMessage("고객 정보가 추가되었습니다.");

                    break;

                case 2:
                    phone = view.getPhone();
                    if (customerController.findCustomer(phone) == null) {
                        view.printMessage("해당 전화번호를 가진 고객 정보가 없습니다.");
                        break;
                    }

                    Customer customer = customerController.findCustomer(phone);
                    MedicalRecord newRecord = view.getMedicalRecordInfo();
                    newRecord.setPhone(phone);

                    medicalRecordController.addMedicalRecord(newRecord);
                    customer.addMedicalRecords(newRecord);

                    view.printMessage("진료 기록이 저장되었습니다.");

                    break;

                case 3:
                    phone = view.getPhone();
                    if (customerController.findCustomer(phone) == null) {
                        view.printMessage("해당 전화번호를 가진 진료 기록이 없습니다.");
                        break;
                    }

                    customer = customerController.findCustomer(phone);
                    view.printMedicalRecordInfo(customer);

                    break;

                case 4:
                    phone = view.getPhone();
                    if (customerController.findCustomer(phone) == null) {
                        view.printMessage("해당 전화번호를 가진 고객 정보가 없습니다.");
                        break;
                    }

                    medicalRecordController.deleteMedicalRecord(phone);
                    view.printMessage("진료 기록 정보가 삭제되었습니다.");

                    break;

                case 5:
                    System.out.println("프로그램을 종료합니다.");
                    return;

                default:
                    System.out.println("잘못된 선택입니다.");
                    break;
            }
        }
    }
}
