package lk.ijse.healthcare.model;

import lk.ijse.healthcare.dto.LoginFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;

public class

LoginFormModel {
    public ResultSet btnLogin(LoginFormDto loginFormDto) throws Exception {
        String sql = "SELECT e.Name, e.Email, e.ContactNumber, e.Address, e.Role, u.Password " +
                "FROM user u " +
                "JOIN employee e ON u.UserId = e.UserId " +
                "WHERE u.Username = ?";

        ResultSet rst = SQLUtil.execute(sql, loginFormDto.getUsername());

        if (rst.next()) {
            return rst;
        }
        return null;
    }

}
