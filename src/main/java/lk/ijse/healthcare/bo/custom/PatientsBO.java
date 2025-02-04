package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.PatientsFormDto;
import lk.ijse.healthcare.dto.tm.PatientsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientsBO extends SuperBO {
    public ArrayList<PatientsFormDto> getAllPatients() throws SQLException;
    public ArrayList<PatientsFormDto> searchPatients(String name) throws SQLException;
    public PatientsFormDto findPatientsById(String selectedContact) throws SQLException;
    public ArrayList<String> getAllPatientMobile() throws SQLException;
    public boolean deletePatient(String patientName) throws SQLException;
    public boolean updatePatient(PatientsFormDto patientsTM) throws SQLException;
}
