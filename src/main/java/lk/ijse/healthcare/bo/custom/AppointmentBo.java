package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.AppointmentFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBo extends SuperBO {
    public ArrayList<AppointmentFormDto> getAllAppointment() throws SQLException;
    public boolean updateAppointment(AppointmentFormDto updateDto) throws SQLException;
    public int getAppointmentIdByDescription(String description) throws SQLException;
    public boolean saveAppointment(AppointmentFormDto saveDto) throws SQLException;
    public boolean deleteAppointment(String delete) throws SQLException;
    public ArrayList<AppointmentFormDto> searchAppointment(String search) throws SQLException;
    public AppointmentFormDto findByAppointmentId(String id) throws SQLException;
    public ArrayList<String> getAllSAppointment() throws SQLException;
}
