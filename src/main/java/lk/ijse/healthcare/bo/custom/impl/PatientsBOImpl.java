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
        ArrayList<PatientsFormDto> patientsTMS = new ArrayList<>();
        for (PatientsFormDto patient : patients) {
            PatientsFormDto patientsTM = new PatientsFormDto();
            patientsTM.setPatientsName(patient.getPatientsName());
            patientsTM.setPatientsAddress(patient.getPatientsAddress());
            patientsTM.setPatientsContactNumber(patient.getPatientsContactNumber());
            patientsTM.setPatientsEmail(patient.getPatientsEmail());
            patientsTM.setPatientsDob(patient.getPatientsDob());
            patientsTM.setPatientsGender(patient.getPatientsGender());
            patientsTM.setPatientsRegDate(patient.getPatientsRegDate());
            patientsTMS.add(patientsTM);
        }
        return patients;
    }

    @Override
    public ArrayList<PatientsFormDto> searchPatients(String name) throws SQLException {
        ArrayList<PatientsFormDto> patients = patientsDAO.search(name);
        ArrayList<PatientsFormDto> patientsTMS = new ArrayList<>();
        for (PatientsFormDto patient : patients) {
            PatientsFormDto patientsTM = new PatientsFormDto();
            patientsTM.setPatientsName(patient.getPatientsName());
            patientsTM.setPatientsAddress(patient.getPatientsAddress());
            patientsTM.setPatientsContactNumber(patient.getPatientsContactNumber());
            patientsTM.setPatientsEmail(patient.getPatientsEmail());
            patientsTM.setPatientsDob(patient.getPatientsDob());
            patientsTM.setPatientsGender(patient.getPatientsGender());
            patientsTM.setPatientsRegDate(patient.getPatientsRegDate());
            patientsTMS.add(patientsTM);
        }
        return patients;
    }

    @Override
    public PatientsFormDto findPatientsById(String selectedContact) throws SQLException {
        return patientsDAO.findById(selectedContact);
    }

    @Override
    public ArrayList<String> getAllPatientMobile() throws SQLException {
        return patientsDAO.getAllS();
    }

    @Override
    public boolean deletePatient(String patientName) throws SQLException {
        return patientsDAO.delete(patientName);
    }

    @Override
    public boolean updatePatient(PatientsFormDto patients) throws SQLException {
        return patientsDAO.update(patients);
    }
}
