package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dto.AppointmentFormDto;
import lk.ijse.healthcare.dto.tm.AppointmentTM;
import lk.ijse.healthcare.entity.Appointment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface AppointmentDAO extends CrudDAO<Appointment> {
}
