package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ForgetPasswordDAO;
import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class ForgetPasswordDAOImpl implements ForgetPasswordDAO{
    @Override
    public boolean changePwd(ForgetPasswordFormDto pwd, String newPwd) throws SQLException {
        return SQLUtil.execute("UPDATE user SET Password = ? WHERE Username = ?", newPwd, pwd.getUsername());
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<ForgetPasswordFormDto> saveOrder) throws SQLException {
        return false;
    }

    @Override
    public int getUserId() throws SQLException {
        return 0;
    }

    @Override
    public String getNewOrderId() throws SQLException {
        return "";
    }

    @Override
    public ResultSet btnLogin(ForgetPasswordFormDto login) throws Exception {
        return null;
    }

    @Override
    public ForgetPasswordFormDto findById(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE Username = ?", id);

        if (rst.next()) {
            int userId = rst.getInt("UserId");
            ResultSet forgetRst = SQLUtil.execute("SELECT * FROM employee WHERE UserId = ?", userId);

            if (forgetRst.next()) {
                ForgetPasswordFormDto dto = new ForgetPasswordFormDto();
                dto.setId(forgetRst.getString("UserId"));
                dto.setPhone(forgetRst.getString("ContactNumber"));
                dto.setEmail(forgetRst.getString("Email"));

                dto.setUsername(id);

                System.out.println(dto.toString());

                return dto;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ForgetPasswordFormDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<ForgetPasswordFormDto> search(String search) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        return null;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return false;
    }

    @Override
    public boolean update(ForgetPasswordFormDto update) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(ForgetPasswordFormDto save) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }
}
