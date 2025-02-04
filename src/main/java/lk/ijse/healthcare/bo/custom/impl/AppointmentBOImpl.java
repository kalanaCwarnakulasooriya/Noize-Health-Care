package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.AppointmentDAO;
import lk.ijse.healthcare.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBo {
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    @Override
    public ArrayList<AppointmentFormDto> getAllAppointment() throws SQLException {
        ArrayList<Appointment> appointments = appointmentDAO.getAll();
        ArrayList<AppointmentFormDto> appointment = new ArrayList<>();
        for (Appointment appointmentDto : appointments) {
            AppointmentFormDto dto = new AppointmentFormDto();
            dto.setAge(appointmentDto.getAge());
            dto.setStatus(appointmentDto.getStatus());
            dto.setDescription(appointmentDto.getDescription());
            dto.setDate(appointmentDto.getDate());
            dto.setDoctorId(appointmentDto.getDoctorId());
            dto.setUserId(appointmentDto.getUserId());
            appointment.add(dto);
        }
        return appointment;
    }

    @Override
    public boolean updateAppointment(AppointmentFormDto update) throws SQLException {
        Appointment appointment = new Appointment(
                update.getAge(),
                update.getStatus(),
                update.getDescription(),
                update.getDate(),
                update.getDoctorId(),
                update.getUserId()
        );
        return appointmentDAO.update(appointment);
    }

    @Override
    public int getAppointmentIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveAppointment(AppointmentFormDto save) throws SQLException {
        Appointment appointment = new Appointment(
                save.getAge(),
                save.getStatus(),
                save.getDescription(),
                save.getDate(),
                save.getDoctorId(),
                save.getUserId()
        );
        return appointmentDAO.save(appointment);
    }

    @Override
    public boolean deleteAppointment(String delete) throws SQLException {
        return appointmentDAO.delete(delete);
    }

    @Override
    public ArrayList<AppointmentFormDto> searchAppointment(String search) throws SQLException {
        ArrayList<Appointment> appointments = appointmentDAO.search(search);
        ArrayList<AppointmentFormDto> appointment = new ArrayList<>();
        for (Appointment appointmentDto : appointments) {
            AppointmentFormDto dto = new AppointmentFormDto();
            dto.setAge(appointmentDto.getAge());
            dto.setStatus(appointmentDto.getStatus());
            dto.setDescription(appointmentDto.getDescription());
            dto.setDate(appointmentDto.getDate());
            dto.setDoctorId(appointmentDto.getDoctorId());
            dto.setUserId(appointmentDto.getUserId());
            appointment.add(dto);
        }
        return appointment;
    }

    @Override
    public AppointmentFormDto findByAppointmentId(String id) throws SQLException {
        ArrayList<Appointment> appointments = appointmentDAO.search(id);
        for (Appointment appointment : appointments) {
            AppointmentFormDto dto = new AppointmentFormDto();
            dto.setAge(appointment.getAge());
            dto.setStatus(appointment.getStatus());
            dto.setDescription(appointment.getDescription());
            dto.setDate(appointment.getDate());
            dto.setDoctorId(appointment.getDoctorId());
            dto.setUserId(appointment.getUserId());
            return dto;
        }
        return null;
    }

    @Override
    public ArrayList<String> getAllSAppointment() throws SQLException {
        ArrayList<String> appointments = appointmentDAO.getAllS();
        ArrayList<String> appointment = new ArrayList<>();
        for (String dto : appointments) {
            appointment.add(dto);
        }
        return appointment;
    }
}
