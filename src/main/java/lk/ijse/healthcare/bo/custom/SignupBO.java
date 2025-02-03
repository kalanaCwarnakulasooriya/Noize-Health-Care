package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.dto.SignupFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SignupBO {
    public int getUserId() throws SQLException;
    public int getRoleIdByDescription(String description) throws SQLException;
    public ArrayList<String> getAllSRoles() throws SQLException;
    public boolean signupUser(SignupFormDto signupFormDto) throws SQLException;
}
