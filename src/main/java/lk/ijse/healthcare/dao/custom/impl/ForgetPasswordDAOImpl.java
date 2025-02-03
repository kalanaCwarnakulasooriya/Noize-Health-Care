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
    public boolean changePwd(ForgetPasswordFormDto forgetPasswordFormDto, String newPassword) throws SQLException {
        return SQLUtil.execute("UPDATE user SET Password = ? WHERE Username = ?", newPassword, forgetPasswordFormDto.getUsername());
    }

    @Override
    public ForgetPasswordFormDto findById(String username) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM user WHERE Username = ?", username);

        if (rst.next()) {
            int userId = rst.getInt("UserId");
            ResultSet forgetRst = SQLUtil.execute("SELECT * FROM employee WHERE UserId = ?", userId);

            if (forgetRst.next()) {
                ForgetPasswordFormDto forgetPasswordFormDto = new ForgetPasswordFormDto();
                forgetPasswordFormDto.setId(forgetRst.getString("UserId"));
                forgetPasswordFormDto.setPhone(forgetRst.getString("ContactNumber"));
                forgetPasswordFormDto.setEmail(forgetRst.getString("Email"));

                forgetPasswordFormDto.setUsername(username);

                System.out.println(forgetPasswordFormDto.toString());

                return forgetPasswordFormDto;
            }
        }
        return null;
    }

    @Override
    public ArrayList<ForgetPasswordFormDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<ForgetPasswordFormDto> search(String name) throws SQLException {
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
    public boolean update(ForgetPasswordFormDto dto) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(ForgetPasswordFormDto dto) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }
}
