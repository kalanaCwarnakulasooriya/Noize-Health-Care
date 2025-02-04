package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.QueryBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.QueryDAO;
import lk.ijse.healthcare.dao.custom.impl.QueryDAOImpl;
import lk.ijse.healthcare.dto.LoginFormDto;
import lk.ijse.healthcare.dto.SignupFormDto;
import lk.ijse.healthcare.entity.Login;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class QueryBOImpl implements QueryBO {
    QueryDAO queryDAO = new QueryDAOImpl();
    @Override
    public ResultSet btnLogin(LoginFormDto login) throws Exception {
        ResultSet rst = SQLUtil.execute("SELECT e.Name, e.Email, e.ContactNumber, e.Address, e.Role, u.Password " +
                "FROM user u " +
                "JOIN employee e ON u.UserId = e.UserId " +
                "WHERE u.Username = ?", login.getUsername()
        );
        if (rst.next()) {
            return rst;
        }
        return null;
    }

    @Override
    public int getUserId() throws SQLException {
        return 0;
    }

    @Override
    public int getRoleIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public ArrayList<String> getAllSRoles() throws SQLException {
        return null;
    }

    @Override
    public boolean signupUser(SignupFormDto signup) throws SQLException {
        return false;
    }
}
