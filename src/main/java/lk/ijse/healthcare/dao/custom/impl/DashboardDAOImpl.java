package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.DashboardDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DashboardDAOImpl implements DashboardDAO {
    @Override
    public ArrayList<String> getAll() throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> search(String search) throws SQLException {
        return null;
    }

    @Override
    public String findById(String id) throws SQLException {
        return "";
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
    public boolean update(String update) throws SQLException {
        return false;
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(String save) throws SQLException {
        return false;
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        HashMap<String,String> status = new HashMap<>();
        String[] tables = {"patient" , "doctor" , "prescription" , "appointment" , "orders" , "item"};
        String[] sqls = {"select count(*) as count from " , "select count(*) as count from " , "select count(*) as count from " , "select count(*) as count from ", "select count(*) as count from ", "select count(*) as count from "};
            for (int i = 0; i < tables.length ; i++) {
                ResultSet rst = SQLUtil.execute(sqls[i] + tables[i]);
                if (rst.next()){
                    status.put(tables[i], rst.getString(1));
                }
            }
        return status;
    }

    @Override
    public boolean changePwd(String user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<String> saveOrder) throws SQLException {
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
    public ResultSet btnLogin(String login) throws Exception {
        return null;
    }
}
