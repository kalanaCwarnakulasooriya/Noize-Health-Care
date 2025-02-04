package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.AddPatientsBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.AddPatientsDAO;
import lk.ijse.healthcare.dao.custom.impl.AddPatientsDAOImpl;
import lk.ijse.healthcare.dto.AddPatientFormDto;
import lk.ijse.healthcare.entity.AddPatient;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPatientsBOImpl implements AddPatientsBO {
    AddPatientsDAO addPatientsDAO = (AddPatientsDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ADDPATIENTS);

    @Override
    public int getGenderIdByDescription(String description) throws SQLException {
        return addPatientsDAO.getIdBy(description);
    }

    @Override
    public ArrayList<String> getAllGenders() throws SQLException {
        return addPatientsDAO.getAllS();
    }

    @Override
    public boolean savePatient(AddPatientFormDto save) throws SQLException {
        AddPatient addPatient = new AddPatient(save.getId(),
                save.getName(),
                save.getAddress(),
                save.getContactNumber(),
                save.getEmail(),
                save.getDob(),
                save.getRegDate(),
                save.getGenderId(),
                save.getUserId(),
                save.getPrescriptionId()
                );
        return addPatientsDAO.save(addPatient);
    }
}
