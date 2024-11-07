package org.example.pet.controller;

import org.example.pet.model.MedicalRecord;

import java.util.ArrayList;
import java.util.List;

public class MedicalRecordController {
    private List<MedicalRecord> medicalRecords = new ArrayList<>();

    // 진료 기록을 등록하는 메서드
    public void addMedicalRecord(MedicalRecord medicalRecord) {
        medicalRecords.add(medicalRecord);
    }

    // 진료 기록을 삭제하는 메서드
    public void deleteMedicalRecord(String phone) {
        for (int i = 0; i < medicalRecords.size(); i++) {
            if (medicalRecords.get(i).getPhone().equals(phone)) {
                medicalRecords.remove(i);

                break;
            }
        }
    }

    // 전화번호에 해당하는 모든 진료기록을 검색하여 새로운 List를 만들어주는 메서드
    public List<MedicalRecord> findMedicalRecord(String phone) {
        List<MedicalRecord> result = new ArrayList<>();
        for (MedicalRecord medicalRecord : medicalRecords) {
            if (medicalRecord.getPhone().equals(phone)) {
                result.add(medicalRecord);
            }
        }

        return result;
    }
}
