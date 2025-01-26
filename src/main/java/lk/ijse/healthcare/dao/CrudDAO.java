package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.dto.tm.PatientsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<T> getAllPatients() throws SQLException;
    public ArrayList<T> searchPatients(String name) throws SQLException;
    public T findById(String selectedContact) throws SQLException;
    public ArrayList<String> getAllPatientMobile() throws SQLException;
    public boolean deletePatient(String patientName) throws SQLException;
    public boolean updatePatient(T patientsTM) throws SQLException;
}
