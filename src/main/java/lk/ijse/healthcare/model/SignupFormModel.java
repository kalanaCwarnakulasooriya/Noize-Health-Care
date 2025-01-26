package lk.ijse.healthcare.model;

import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.SignupFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SignupFormModel {
    public int getUserId() throws SQLException {
        String getId = "SELECT LAST_INSERT_ID() AS `UserId`";
        ResultSet rst = SQLUtil.execute(getId);
        try {
            if (rst.next()) {
                return rst.getInt("UserId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int getRoleIdByDescription(String description) throws SQLException {
        String getRole = "SELECT RoleId FROM role WHERE Description = ?";
        ResultSet rst = SQLUtil.execute(getRole, description);
        try {
            if (rst.next()) {
                return rst.getInt("RoleId");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public ArrayList<String> getAllRoles() throws SQLException {
        ArrayList<String> roles = new ArrayList<>();
        String getAllRoles = "SELECT Description FROM role";
        ResultSet rst = SQLUtil.execute(getAllRoles);
        try {
            while (rst.next()) {
                roles.add(rst.getString("Description"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return roles;
    }

    public Boolean signupUser(SignupFormDto signupFormDto) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sqlAddUser = "INSERT INTO `user` (`Username`, `Password`) VALUES (?, ?)";
        String sqlGetId = "SELECT LAST_INSERT_ID() AS `UserId`";

        try {
            connection.setAutoCommit(false);
            if (SQLUtil.execute(sqlAddUser, signupFormDto.getUsername(), signupFormDto.getPassword())) {
                System.out.println("Data added to user table successfully.");
                ResultSet rst = SQLUtil.execute(sqlGetId);
                if (rst != null && rst.next()) {
                    int userId = rst.getInt("UserId");
                    System.out.println("Retrieved last UserId: " + userId);
                    String sqlAddEmployee = "INSERT INTO `employee` (`ContactNumber`,`Email`, `Address`,`Name`, `Role`, `UserId`) VALUES (?, ?, ?, ?, ?, ?)";
                    if (SQLUtil.execute(sqlAddEmployee, signupFormDto.getName(), signupFormDto.getEmail(), signupFormDto.getContactNumber(), signupFormDto.getAddress(), signupFormDto.getRole(), userId)) {
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
