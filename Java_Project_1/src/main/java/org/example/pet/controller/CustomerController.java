package org.example.pet.controller;

import org.example.pet.model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerController {
    private List<Customer> customers;
    private MedicalRecordController medicalRecordController;

    public CustomerController(MedicalRecordController medicalRecordController) {
        this.customers = new ArrayList<>();
        this.medicalRecordController = medicalRecordController;
    }

    // 고객 정보를 등록하는 메서드
    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    // 고객 정보를 삭제하는 메서드
    public void deleteCustomer(String phone) {
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getPhone().equals(phone)) {
                customers.remove(i);

                medicalRecordController.deleteMedicalRecord(phone);

                break;
            }
        }
    }

    // 고객 등록 여부를 확인하는 메서드
    public Customer findCustomer(String phone) {
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone)) {
                return customer;
            }
        }

        return null;
    }

    // 기존의 전화번호로 등록된 고객이 있는지 확인하는 메서드
    public boolean isPhoneExist(String phone) {
        for (Customer customer : customers) {
            if (customer.getPhone().equals(phone)) {
                return true;
            }
        }

        return false;
    }
}
