package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.AppointmentBo;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.AppointmentDAO;
import lk.ijse.healthcare.dao.custom.impl.AppointmentDAOImpl;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class AppointmentBOImpl implements AppointmentBo {
    AppointmentDAO appointmentDAO = (AppointmentDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.APPOINTMENT);
    @Override
    public ArrayList<AppointmentFormDto> getAllAppointment() throws SQLException {
        ArrayList<AppointmentFormDto> appointments = appointmentDAO.getAll();
        ArrayList<AppointmentFormDto> appointment = new ArrayList<>();
        for (AppointmentFormDto appointmentDto : appointments) {
            AppointmentFormDto newAppointment = new AppointmentFormDto();
            newAppointment.setAge(appointmentDto.getAge());
            newAppointment.setStatus(appointmentDto.getStatus());
            newAppointment.setDescription(appointmentDto.getDescription());
            newAppointment.setDate(appointmentDto.getDate());
            newAppointment.setDoctorId(appointmentDto.getDoctorId());
            newAppointment.setUserId(appointmentDto.getUserId());
            appointment.add(newAppointment);
        }
        return appointment;
    }

    @Override
    public boolean updateAppointment(AppointmentFormDto dto) throws SQLException {
        AppointmentFormDto appointment = new AppointmentFormDto(
                dto.getAge(),
                dto.getStatus(),
                dto.getDescription(),
                dto.getDate(),
                dto.getDoctorId(),
                dto.getUserId()
        );
        return appointmentDAO.update(appointment);
    }

    @Override
    public int getAppointmentIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean saveAppointment(AppointmentFormDto dto) throws SQLException {
        AppointmentFormDto appointment = new AppointmentFormDto(
                dto.getAge(),
                dto.getStatus(),
                dto.getDescription(),
                dto.getDate(),
                dto.getDoctorId(),
                dto.getUserId()
        );
        return appointmentDAO.save(appointment);
    }

    @Override
    public boolean deleteAppointment(String age) throws SQLException {
        return appointmentDAO.delete(age);
    }

    @Override
    public ArrayList<AppointmentFormDto> searchAppointment(String age) throws SQLException {
        ArrayList<AppointmentFormDto> appointments = appointmentDAO.search(age);
        ArrayList<AppointmentFormDto> appointment = new ArrayList<>();
        for (AppointmentFormDto appointmentDto : appointments) {
            AppointmentFormDto newAppointment = new AppointmentFormDto();
            newAppointment.setAge(appointmentDto.getAge());
            newAppointment.setStatus(appointmentDto.getStatus());
            newAppointment.setDescription(appointmentDto.getDescription());
            newAppointment.setDate(appointmentDto.getDate());
            newAppointment.setDoctorId(appointmentDto.getDoctorId());
            newAppointment.setUserId(appointmentDto.getUserId());
            appointment.add(newAppointment);
        }
        return appointment;
    }

    @Override
    public AppointmentFormDto findByAppointmentId(String selectName) throws SQLException {
        ArrayList<AppointmentFormDto> appointments = appointmentDAO.search(selectName);
        for (AppointmentFormDto appointmentDto : appointments) {
            AppointmentFormDto newAppointment = new AppointmentFormDto();
            newAppointment.setAge(appointmentDto.getAge());
            newAppointment.setStatus(appointmentDto.getStatus());
            newAppointment.setDescription(appointmentDto.getDescription());
            newAppointment.setDate(appointmentDto.getDate());
            newAppointment.setDoctorId(appointmentDto.getDoctorId());
            newAppointment.setUserId(appointmentDto.getUserId());
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
