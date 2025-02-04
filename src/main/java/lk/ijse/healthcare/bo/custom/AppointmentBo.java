package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.AppointmentFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBo extends SuperBO {
    public ArrayList<AppointmentFormDto> getAllAppointment() throws SQLException;
    public boolean updateAppointment(AppointmentFormDto dto) throws SQLException;
    public int getAppointmentIdByDescription(String description) throws SQLException;
    public boolean saveAppointment(AppointmentFormDto dto) throws SQLException;
    public boolean deleteAppointment(String age) throws SQLException;
    public ArrayList<AppointmentFormDto> searchAppointment(String age) throws SQLException;
    public AppointmentFormDto findByAppointmentId(String selectName) throws SQLException;
    public ArrayList<String> getAllSAppointment() throws SQLException;
}
