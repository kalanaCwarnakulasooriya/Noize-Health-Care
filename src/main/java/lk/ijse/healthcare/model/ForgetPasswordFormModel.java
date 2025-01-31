package lk.ijse.healthcare.model;

import lk.ijse.healthcare.db.DBConnection;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ForgetPasswordFormModel {
    public static Boolean isChangedUserPassword(ForgetPasswordFormDto forgetPasswordFormDto, String newPassword) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "UPDATE user SET Password = ? WHERE Username = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, newPassword);
        pstm.setString(2, forgetPasswordFormDto.getUsername());

        return pstm.executeUpdate() > 0;
    }

    public static ForgetPasswordFormDto getUserData(String username) throws SQLException {
        Connection connection = DBConnection.getInstance().getConnection();
        String sql = "SELECT * FROM user WHERE Username = ?";
        PreparedStatement pstm = connection.prepareStatement(sql);
        pstm.setString(1, username);
        ResultSet rst = pstm.executeQuery();

        if (rst.next()) {
            int userId = rst.getInt("UserId");
            String forgetSql = "SELECT * FROM employee WHERE UserId = ?";
            PreparedStatement forgetPstm = connection.prepareStatement(forgetSql);
            forgetPstm.setInt(1, userId);
            ResultSet forgetRst = forgetPstm.executeQuery();

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
}
