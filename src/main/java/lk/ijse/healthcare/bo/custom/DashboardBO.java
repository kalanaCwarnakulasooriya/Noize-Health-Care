package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;

import java.sql.SQLException;
import java.util.HashMap;

public interface DashboardBO extends SuperBO {
    public HashMap<String, String> statusDashboard() throws SQLException;
}
