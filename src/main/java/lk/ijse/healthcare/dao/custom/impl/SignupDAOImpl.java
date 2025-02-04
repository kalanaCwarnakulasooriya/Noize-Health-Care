package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.SignupDAO;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.SignupFormDto;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.entity.Signup;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SignupDAOImpl implements SignupDAO {
    @Override
    public int getUserId() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT LAST_INSERT_ID() AS `UserId`");
        if (rst.next()) {
            return rst.getInt("UserId");
        }
        return -1;
    }

    @Override
    public String getNewOrderId() throws SQLException {
        return "";
    }

    @Override
    public ResultSet btnLogin(Signup login) throws Exception {
        return null;
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT RoleId FROM role WHERE Description = ?", id);
        if (rst.next()) {
            return rst.getInt("RoleId");
        }
        return -1;
    }

    @Override
    public boolean save(Signup save) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(Signup user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<Signup> saveOrder) throws SQLException {
        return false;
    }

    @Override
    public ArrayList<Signup> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<Signup> search(String search) throws SQLException {
        return null;
    }

    @Override
    public Signup findById(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ArrayList<String> roles = new ArrayList<>();
        ResultSet rst = SQLUtil.execute("SELECT Description FROM role");
        while (rst.next()) {
            roles.add(rst.getString("Description"));
        }
        return roles;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return false;
    }

    @Override
    public boolean update(Signup update) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();

        try {
            connection.setAutoCommit(false);
            if (SQLUtil.execute("INSERT INTO `user` (`Username`, `Password`) VALUES (?, ?)", update.getUsername(), update.getPassword())) {
                System.out.println("Data added to user table successfully.");
                ResultSet rst = SQLUtil.execute("SELECT LAST_INSERT_ID() AS `UserId`");
                if (rst != null && rst.next()) {
                    int userId = rst.getInt("UserId");
                    System.out.println("Retrieved last UserId: " + userId);
                    if (SQLUtil.execute("INSERT INTO `employee` (`ContactNumber`,`Email`, `Address`,`Name`, `Role`, `UserId`) VALUES (?, ?, ?, ?, ?, ?)", update.getName(), update.getEmail(), update.getContactNumber(), update.getAddress(), update.getRole(), userId)) {
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
