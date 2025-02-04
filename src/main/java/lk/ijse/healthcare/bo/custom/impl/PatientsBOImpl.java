package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.PatientsBO;
import lk.ijse.healthcare.dao.custom.PatientsDAO;
import lk.ijse.healthcare.dao.custom.impl.PatientsDAOImpl;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.tm.PatientsTM;
import lk.ijse.healthcare.entity.Patients;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientsBOImpl implements PatientsBO {
    PatientsDAO patientsDAO = new PatientsDAOImpl();

    @Override
    public ArrayList<PatientsFormDto> getAllPatients() throws SQLException {
        ArrayList<Patients> patients = patientsDAO.getAll();
        ArrayList<PatientsFormDto> patientsDto = new ArrayList<>();
        for (Patients patient : patients) {
            PatientsFormDto dto = new PatientsFormDto();
            dto.setPatientsName(patient.getPatientsName());
            dto.setPatientsAddress(patient.getPatientsAddress());
            dto.setPatientsContactNumber(patient.getPatientsContactNumber());
            dto.setPatientsEmail(patient.getPatientsEmail());
            dto.setPatientsDob(patient.getPatientsDob());
            dto.setPatientsGender(patient.getPatientsGender());
            dto.setPatientsRegDate(patient.getPatientsRegDate());
            patientsDto.add(dto);
        }
        return patientsDto;
    }

    @Override
    public ArrayList<PatientsFormDto> searchPatients(String search) throws SQLException {
        ArrayList<Patients> patients = patientsDAO.search(search);
        ArrayList<PatientsFormDto> patientsDto = new ArrayList<>();
        for (Patients patient : patients) {
            PatientsFormDto dto = new PatientsFormDto();
            dto.setPatientsName(patient.getPatientsName());
            dto.setPatientsAddress(patient.getPatientsAddress());
            dto.setPatientsContactNumber(patient.getPatientsContactNumber());
            dto.setPatientsEmail(patient.getPatientsEmail());
            dto.setPatientsDob(patient.getPatientsDob());
            dto.setPatientsGender(patient.getPatientsGender());
            dto.setPatientsRegDate(patient.getPatientsRegDate());
            patientsDto.add(dto);
        }
        return patientsDto;
    }

    @Override
    public PatientsFormDto findPatientsById(String id) throws SQLException {
        ArrayList<Patients> patients = patientsDAO.getAll();
        for (Patients patient : patients) {
            if (patient.getPatientsName().equals(id)) {
                PatientsFormDto dto = new PatientsFormDto();
                dto.setPatientsName(patient.getPatientsName());
                dto.setPatientsAddress(patient.getPatientsAddress());
                dto.setPatientsContactNumber(patient.getPatientsContactNumber());
                dto.setPatientsEmail(patient.getPatientsEmail());
                dto.setPatientsDob(patient.getPatientsDob());
                dto.setPatientsGender(patient.getPatientsGender());
                dto.setPatientsRegDate(patient.getPatientsRegDate());
                return dto;
            }
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllPatientMobile() throws SQLException {
        return patientsDAO.getAllS();
    }

    @Override
    public boolean deletePatient(String delete) throws SQLException {
        return patientsDAO.delete(delete);
    }

    @Override
    public boolean updatePatient(PatientsFormDto update) throws SQLException {
        Patients patients = new Patients(update.getPatientsName(), update.getPatientsAddress(), update.getPatientsContactNumber(), update.getPatientsEmail(), update.getPatientsDob(), update.getPatientsGender(), update.getPatientsRegDate());
        return patientsDAO.update(patients);
    }
}
