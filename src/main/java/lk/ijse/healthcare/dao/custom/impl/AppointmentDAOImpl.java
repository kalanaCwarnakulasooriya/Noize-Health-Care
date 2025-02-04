package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.AppointmentDAO;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public ArrayList<AppointmentFormDto> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointment");
        ArrayList<AppointmentFormDto> appointments = new ArrayList<>();
        while (rst.next()) {
                AppointmentFormDto dto = new AppointmentFormDto(
                        rst.getString("Age"),
                        rst.getString("Status"),
                        rst.getString("Description"),
                        rst.getString("Date"),
                        rst.getString("DoctorId"),
                        rst.getInt("UserId")
                );
                appointments.add(dto);
        }
        return appointments;
    }

    @Override
    public boolean update(AppointmentFormDto update) throws SQLException {
        return SQLUtil.execute("UPDATE appointment SET Status = ?, Description = ?, Date = ? WHERE Age = ?",
                update.getStatus(),
                update.getDescription(),
                update.getDate(),
                update.getAge()
                );
    }

    @Override
    public int getIdBy(String id) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(AppointmentFormDto save) throws SQLException {
            return SQLUtil.execute("INSERT INTO appointment(Age,Status,Description,Date,DoctorId,UserId) VALUES (?,?,?,?,?,?)",
                    save.getAge(),
                    save.getStatus(),
                    save.getDescription(),
                    save.getDate(),
                    save.getDoctorId(),
                    1
            );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(AppointmentFormDto user, String newPwd) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<AppointmentFormDto> saveOrder) throws SQLException {
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
    public ResultSet btnLogin(AppointmentFormDto login) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String delete) throws SQLException {
        return SQLUtil.execute("DELETE FROM appointment WHERE Age = ?", delete);
    }

    @Override
    public ArrayList<AppointmentFormDto> search(String search) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from appointment where Age like ?;", search + "%");
        ArrayList<AppointmentFormDto> appointments = new ArrayList<>();
        while (rst.next()) {
            AppointmentFormDto dto = new AppointmentFormDto(
                    rst.getString("Age"),
                    rst.getString("Status"),
                    rst.getString("Description"),
                    rst.getString("Date"),
                    rst.getString("DoctorId"),
                    rst.getInt("UserId")
            );
            appointments.add(dto);
        }
        return appointments;
    }

    @Override
    public AppointmentFormDto findById(String id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointment WHERE AppointmentId = ?", id);

        if (rst.next()) {
            return new AppointmentFormDto(
                    rst.getString("Age"),
                    rst.getString("Status"),
                    rst.getString("Description"),
                    rst.getString("Date"),
                    rst.getString("DoctorId"),
                    rst.getInt("UserId")
            );
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT AppointmentId FROM appointment");
        ArrayList<String> appointments = new ArrayList<>();
        while (rst.next()) {
            appointments.add(rst.getString("AppointmentId"));
        }
        return appointments;
    }
}
