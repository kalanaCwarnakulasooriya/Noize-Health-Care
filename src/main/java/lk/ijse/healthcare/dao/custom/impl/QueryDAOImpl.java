package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.QueryDAO;
import lk.ijse.healthcare.dto.LoginFormDto;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public ArrayList<LoginFormDto> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<LoginFormDto> search(String name) throws SQLException {
        return null;
    }

    @Override
    public LoginFormDto findById(String name) throws SQLException {
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
    public boolean update(LoginFormDto dto) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(LoginFormDto dto) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(LoginFormDto user, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<LoginFormDto> orderDetailsDto) throws SQLException {
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
    public ResultSet btnLogin(LoginFormDto loginFormDto) throws Exception {
        ResultSet rst = SQLUtil.execute("SELECT e.Name, e.Email, e.ContactNumber, e.Address, e.Role, u.Password " +
                "FROM user u " +
                "JOIN employee e ON u.UserId = e.UserId " +
                "WHERE u.Username = ?", loginFormDto.getUsername()
        );
        if (rst.next()) {
            return rst;
        }
        return null;
    }

}
