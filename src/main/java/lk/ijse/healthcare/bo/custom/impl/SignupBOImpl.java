package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.SignupBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.SignupDAO;
import lk.ijse.healthcare.dao.custom.impl.SignupDAOImpl;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.SignupFormDto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SignupBOImpl implements SignupBO {
    SignupDAO signupDAO = new SignupDAOImpl();
    @Override
    public int getUserId() throws SQLException {
        return signupDAO.getUserId();
    }

    @Override
    public int getRoleIdByDescription(String description) throws SQLException {
        return signupDAO.getIdBy(description);
    }

    @Override
    public ArrayList<String> getAllSRoles() throws SQLException {
        ArrayList<String> roles = new ArrayList<>();
        ArrayList<String> roles1 = signupDAO.getAllS();
        for (String role : roles1) {
            roles.add(role);
        }
        return roles;
    }

    @Override
    public boolean signupUser(SignupFormDto signupFormDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            if (SQLUtil.execute("INSERT INTO `user` (`Username`, `Password`) VALUES (?, ?)", signupFormDto.getUsername(), signupFormDto.getPassword())) {
                System.out.println("Data added to user table successfully.");
                ResultSet rst = SQLUtil.execute("SELECT LAST_INSERT_ID() AS `UserId`");
                if (rst != null && rst.next()) {
                    int userId = rst.getInt("UserId");
                    System.out.println("Retrieved last UserId: " + userId);
                    if (SQLUtil.execute("INSERT INTO `employee` (`ContactNumber`,`Email`, `Address`,`Name`, `Role`, `UserId`) VALUES (?, ?, ?, ?, ?, ?)", signupFormDto.getName(), signupFormDto.getEmail(), signupFormDto.getContactNumber(), signupFormDto.getAddress(), signupFormDto.getRole(), userId)) {
                        System.out.println("Data added to employee table successfully.");
                        connection.commit();
                        return true;
                    }
                }
            }
            throw new SQLException("Failed to save data to user or employee table.");
        } catch (Exception e) {
            System.err.println("Error during signup process: " + e.getMessage());
            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }
    }
}
