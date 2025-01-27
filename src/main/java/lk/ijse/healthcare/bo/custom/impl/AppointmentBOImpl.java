package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.AppointmentDAO;
import lk.ijse.healthcare.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.healthcare.dto.tm.AppointmentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBo {
    AppointmentDAO appointmentDAO = new AppointmentDAOImpl();
    @Override
    public ArrayList<AppointmentTM> getAllAppointment() throws SQLException {
        ArrayList<AppointmentTM> appointments = appointmentDAO.getAll();
        ArrayList<AppointmentTM> appointment = new ArrayList<>();
        for (AppointmentTM appointmentTM : appointments) {
            AppointmentTM newAppointment = new AppointmentTM();
            newAppointment.setAge(appointmentTM.getAge());
            newAppointment.setStatus(appointmentTM.getStatus());
            newAppointment.setDescription(appointmentTM.getDescription());
            newAppointment.setDate(appointmentTM.getDate());
            newAppointment.setDoctorId(appointmentTM.getDoctorId());
            appointmentTM.setUserId(appointmentTM.getUserId());
            appointment.add(appointmentTM);
        }
        return appointment;
    }

    @Override
    public boolean updateAppointment(AppointmentTM dto) throws SQLException {
        AppointmentTM appointment = new AppointmentTM(dto.getAge(), dto.getStatus(), dto.getDescription(), dto.getDate(), dto.getDoctorId(), dto.getUserId());
        return appointmentDAO.update(appointment);
    }

    @Override
    public int getAppointmentIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveAppointment(AppointmentTM dto) throws SQLException {
        AppointmentTM appointment = new AppointmentTM(dto.getAge(), dto.getStatus(), dto.getDescription(), dto.getDate(), dto.getDoctorId(), dto.getUserId());
        return appointmentDAO.save(appointment);
    }

    @Override
    public boolean deleteAppointment(String age) throws SQLException {
        return appointmentDAO.delete(age);
    }

    @Override
    public ArrayList<AppointmentTM> searchAppointment(String age) throws SQLException {
        ArrayList<AppointmentTM> appointments = appointmentDAO.search(age);
        ArrayList<AppointmentTM> appointment = new ArrayList<>();
        for (AppointmentTM appointmentTM : appointments) {
            AppointmentTM newAppointment = new AppointmentTM();
            newAppointment.setAge(appointmentTM.getAge());
            newAppointment.setStatus(appointmentTM.getStatus());
            newAppointment.setDescription(appointmentTM.getDescription());
            newAppointment.setDate(appointmentTM.getDate());
            newAppointment.setDoctorId(appointmentTM.getDoctorId());
            newAppointment.setUserId(appointmentTM.getUserId());
            appointment.add(newAppointment);
        }
        return appointment;
    }

    @Override
    public AppointmentTM findByAppointmentId(String selectName) throws SQLException {
        ArrayList<AppointmentTM> appointments = appointmentDAO.search(selectName);
        for (AppointmentTM appointmentTM : appointments) {
            AppointmentTM newAppointment = new AppointmentTM();
            newAppointment.setAge(appointmentTM.getAge());
            newAppointment.setStatus(appointmentTM.getStatus());
            newAppointment.setDescription(appointmentTM.getDescription());
            newAppointment.setDate(appointmentTM.getDate());
            newAppointment.setDoctorId(appointmentTM.getDoctorId());
            newAppointment.setUserId(appointmentTM.getUserId());
            return newAppointment;
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllSAppointment() throws SQLException {
        ArrayList<String> appointments = appointmentDAO.getAllS();
        ArrayList<String> appointment = new ArrayList<>();
        for (String appointmentTM : appointments) {
            appointment.add(appointmentTM);
        }
        return appointment;
    }
}
