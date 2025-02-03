package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.DashboardBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.DashboardDAO;
import lk.ijse.healthcare.dao.custom.impl.DashboardDAOImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DashboardBOImpl implements DashboardBO {
    DashboardDAO dashboardDAO = new DashboardDAOImpl();

    @Override
    public HashMap<String, String> statusDashboard() throws SQLException {
        return dashboardDAO.status();
    }
}
