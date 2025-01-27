package lk.ijse.healthcare.dao.custom.impl;

import lk.ijse.healthcare.dao.custom.AppointmentDAO;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.dao.SQLUtil;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentDAOImpl implements AppointmentDAO {
    @Override
    public ArrayList<AppointmentTM> getAll() throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointment");
        ArrayList<AppointmentTM> appointments = new ArrayList<>();
        while (rst.next()) {
                AppointmentTM appointment = new AppointmentTM(
                        rst.getString("Age"),
                        rst.getString("Status"),
                        rst.getString("Description"),
                        rst.getString("Date"),
                        rst.getString("DoctorId"),
                        rst.getString("UserId")
                );
                appointments.add(appointment);
        }
        return appointments;
    }

    @Override
    public boolean update(AppointmentTM appointmentFormDto) throws SQLException {
        return SQLUtil.execute("UPDATE appointment SET Status = ?, Description = ?, Date = ? WHERE Age = ?",
                appointmentFormDto.getStatus(),
                appointmentFormDto.getDescription(),
                appointmentFormDto.getDate(),
                appointmentFormDto.getAge()
                );
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean save(AppointmentTM appointment) throws SQLException {
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
    public boolean delete(String age) throws SQLException {
        return SQLUtil.execute("DELETE FROM appointment WHERE Age = ?", age);
    }

    @Override
    public ArrayList<AppointmentTM> search(String age) throws SQLException {
        ResultSet rst = SQLUtil.execute("select * from appointment where Age like ?;", age + "%");
        ArrayList<AppointmentTM> appointments = new ArrayList<>();
        while (rst.next()) {
            AppointmentTM newAppointments = new AppointmentTM(
                    rst.getString("Age"),
                    rst.getString("Status"),
                    rst.getString("Description"),
                    rst.getString("Date"),
                    rst.getString("DoctorId"),
                    rst.getString("UserId")
            );
            appointments.add(newAppointments);
        }
        return appointments;
    }

    @Override
    public AppointmentTM findById(String selectName) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM appointment WHERE AppointmentId = ?", selectName);

        if (rst.next()) {
            return new AppointmentTM(
                    rst.getString("Age"),
                    rst.getString("Status"),
                    rst.getString("Description"),
                    rst.getString("Date"),
                    rst.getString("DoctorId"),
                    rst.getString("UserId")
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
