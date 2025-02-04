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
                AppointmentFormDto appointment = new AppointmentFormDto(
                        rst.getString("Age"),
                        rst.getString("Status"),
                        rst.getString("Description"),
                        rst.getString("Date"),
                        rst.getString("DoctorId"),
                        rst.getInt("UserId")
                );
                appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public boolean update(AppointmentFormDto appointment) throws SQLException {
        return SQLUtil.execute("UPDATE appointment SET Status = ?, Description = ?, Date = ? WHERE Age = ?",
                appointment.getStatus(),
                appointment.getDescription(),
                appointment.getDate(),
                appointment.getAge()
                );
    }

    @Override
    public int getIdBy(String name) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(AppointmentFormDto appointment) throws SQLException {
            return SQLUtil.execute("INSERT INTO appointment(Age,Status,Description,Date,DoctorId,UserId) VALUES (?,?,?,?,?,?)",
                    appointment.getAge(),
                    appointment.getStatus(),
                    appointment.getDescription(),
                    appointment.getDate(),
                    appointment.getDoctorId(),
                    1
            );
    }

    @Override
    public HashMap<String, String> status() throws SQLException {
        return null;
    }

    @Override
    public boolean changePwd(AppointmentFormDto user, String newPassword) throws SQLException {
        return false;
    }

    @Override
    public double getOrder(String orderId) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveOrderDetails(ArrayList<AppointmentFormDto> orderDetailsDto) throws SQLException {
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
    public ResultSet btnLogin(AppointmentFormDto loginFormDto) throws Exception {
        return null;
    }

    @Override
    public boolean delete(String age) throws SQLException {
        return SQLUtil.execute("DELETE FROM appointment WHERE Age = ?", age);
    }

    @Override
    public ArrayList<AppointmentFormDto> search(String age) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from appointment where Age like ?;", age + "%");
        ArrayList<AppointmentFormDto> appointments = new ArrayList<>();
        while (rst.next()) {
            AppointmentFormDto newAppointments = new AppointmentFormDto(
                    rst.getString("Age"),
                    rst.getString("Status"),
                    rst.getString("Description"),
                    rst.getString("Date"),
                    rst.getString("DoctorId"),
                    rst.getInt("UserId")
            );
            appointments.add(newAppointments);
        }
        return appointments;
    }

    @Override
    public AppointmentFormDto findById(String selectName) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointment WHERE AppointmentId = ?", selectName);

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
