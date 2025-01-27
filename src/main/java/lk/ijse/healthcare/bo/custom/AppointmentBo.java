package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.dto.tm.AppointmentTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentBo {
    public ArrayList<AppointmentTM> getAllAppointment() throws SQLException;
    public boolean updateAppointment(AppointmentTM dto) throws SQLException;
    public int getAppointmentIdByDescription(String description) throws SQLException;
    public boolean saveAppointment(AppointmentTM dto) throws SQLException;
    public boolean deleteAppointment(String age) throws SQLException;
    public ArrayList<AppointmentTM> searchAppointment(String age) throws SQLException;
    public AppointmentTM findByAppointmentId(String selectName) throws SQLException;
    public ArrayList<String> getAllSAppointment() throws SQLException;
}
