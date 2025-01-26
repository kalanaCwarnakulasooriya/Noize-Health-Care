package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.dto.AddPatientFormDto;
import lk.ijse.healthcare.dto.tm.PatientsTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CrudDAO<T> {
    public ArrayList<T> getAll() throws SQLException;
    public ArrayList<T> search(String name) throws SQLException;
    public T findById(String selectedContact) throws SQLException;
    public ArrayList<String> getAllMobile() throws SQLException;
    public boolean delete(String patientName) throws SQLException;
    public boolean update(T patientsTM) throws SQLException;
    public int getIdByDescription(String description) throws SQLException;
    public ArrayList<String> getAllGenders() throws SQLException;
    public boolean save(T patientDTO) throws SQLException;
}
