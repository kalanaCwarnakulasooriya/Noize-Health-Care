package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.AddPatientFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddPatientsBO extends SuperBO {
    public int getGenderIdByDescription(String description) throws SQLException;
    public ArrayList<String> getAllGenders() throws SQLException;
    public boolean savePatient(AddPatientFormDto patientDTO) throws SQLException;
}
