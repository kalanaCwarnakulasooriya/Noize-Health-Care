package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.AddPatientsDAO;
import lk.ijse.healthcare.dto.AddPatientFormDto;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dto.tm.AppointmentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPatientsDAOImpl implements AddPatientsDAO {
    @Override
    public ArrayList<AddPatientFormDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<AddPatientFormDto> search(String name) throws SQLException {
        return null;
    }

    @Override
    public AddPatientFormDto findById(String selectedContact) throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String patientName) throws SQLException {
        return false;
    }

    @Override
    public boolean update(AddPatientFormDto patientsTM) throws SQLException {
        return false;
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT GenderId FROM Gender WHERE Description = ?", description);
            if (rst.next()) {
                return rst.getInt("GenderId");
            }
        return -1;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ResultSet rstGender = SQLUtil.execute("SELECT Description FROM gender");

        ArrayList<String> gender = new ArrayList<>();
        while (rstGender.next()) {
            gender.add(rstGender.getString("Description"));
        }
        return gender;
    }

    @Override
    public boolean save(AddPatientFormDto patientDTO) throws SQLException {
        return SQLUtil.execute(
                "INSERT INTO patient VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                patientDTO.getId(),
                patientDTO.getName(),
                patientDTO.getEmail(),
                patientDTO.getContactNumber(),
                patientDTO.getAddress(),
                patientDTO.getDob(),
                patientDTO.getGenderId(),
                patientDTO.getRegDate(),
                patientDTO.getUserId(),
                patientDTO.getPrescriptionId()
        );
    }

}
