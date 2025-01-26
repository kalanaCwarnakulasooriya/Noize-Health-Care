package lk.ijse.healthcare.model;

import lk.ijse.healthcare.dto.AddPatientFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AddPatientsFormModel {
    public int getGenderIdByDescription(String description) throws SQLException {
        String getGender = "SELECT GenderId FROM Gender WHERE Description = ?";
        ResultSet rstGender = SQLUtil.execute(getGender, description);
        try {
            if (rstGender.next()) {
                return rstGender.getInt("GenderId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }
    public ArrayList<String> getAllGenders() throws SQLException {
        ArrayList<String> gender = new ArrayList<>();
        String sqlGender = "SELECT Description FROM gender";
        ResultSet rstGender = SQLUtil.execute(sqlGender);

        while (rstGender.next()) {
            gender.add(rstGender.getString("Description"));
        }
        return gender;
    }

    public boolean savePatient(AddPatientFormDto patientDTO) throws SQLException {
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
