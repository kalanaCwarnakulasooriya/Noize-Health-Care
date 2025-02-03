package lk.ijse.healthcare.bo.custom;

import java.sql.SQLException;
import java.util.HashMap;

public interface DashboardBO {
    public HashMap<String, String> statusDashboard() throws SQLException;
}
