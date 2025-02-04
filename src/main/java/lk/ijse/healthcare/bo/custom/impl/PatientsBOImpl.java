package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.PatientsBO;
import lk.ijse.healthcare.dao.custom.PatientsDAO;
import lk.ijse.healthcare.dao.custom.impl.PatientsDAOImpl;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.tm.PatientsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public class PatientsBOImpl implements PatientsBO {
    PatientsDAO patientsDAO = new PatientsDAOImpl();

    @Override
    public ArrayList<PatientsFormDto> getAllPatients() throws SQLException {
        ArrayList<PatientsFormDto> patients = patientsDAO.getAll();
        ArrayList<PatientsFormDto> patientsDto = new ArrayList<>();
        for (PatientsFormDto patient : patients) {
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
        return patients;
    }

    @Override
    public ArrayList<PatientsFormDto> searchPatients(String search) throws SQLException {
        ArrayList<PatientsFormDto> patients = patientsDAO.search(search);
        ArrayList<PatientsFormDto> patientsDto = new ArrayList<>();
        for (PatientsFormDto patient : patients) {
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
        return patients;
    }

    @Override
    public PatientsFormDto findPatientsById(String id) throws SQLException {
        return patientsDAO.findById(id);
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
        return patientsDAO.update(update);
    }
}
