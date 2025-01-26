package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.dto.AddPatientFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AddPatientsDAO extends CrudDAO<AddPatientFormDto> {
    public int getGenderIdByDescription(String description) throws SQLException;
    public ArrayList<String> getAllGenders() throws SQLException;
    public boolean savePatient(AddPatientFormDto patientDTO) throws SQLException;
}
