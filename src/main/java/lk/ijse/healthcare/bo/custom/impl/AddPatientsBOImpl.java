package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.AddPatientsBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.AddPatientsDAO;
import lk.ijse.healthcare.dao.custom.impl.AddPatientsDAOImpl;
import lk.ijse.healthcare.dto.AddPatientFormDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPatientsBOImpl implements AddPatientsBO {
    AddPatientsDAO addPatientsDAO = new AddPatientsDAOImpl();

    @Override
    public int getGenderIdByDescription(String description) throws SQLException {
        return addPatientsDAO.getIdByDescription(description);
    }

    @Override
    public ArrayList<String> getAllGenders() throws SQLException {
        return addPatientsDAO.getAllS();
    }

    @Override
    public boolean savePatient(AddPatientFormDto patientDTO) throws SQLException {
        return addPatientsDAO.save(patientDTO);
    }
}
