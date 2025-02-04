package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.dto.PrescriptionFormDto;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;
import lk.ijse.healthcare.entity.Prescription;

import java.sql.SQLException;
import java.util.ArrayList;

public interface PrescriptionDAO extends CrudDAO<Prescription> {
}
