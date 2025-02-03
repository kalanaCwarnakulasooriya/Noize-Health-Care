package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.PrescriptionBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.PrescriptionDAO;
import lk.ijse.healthcare.dao.custom.impl.PrescriptionDAOImpl;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionBOImpl implements PrescriptionBO {
    PrescriptionDAO prescriptionDAO = (PrescriptionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRESCRIPTION);
    @Override
    public ArrayList<PrescriptionTM> getAllPrescription() throws SQLException {
        ArrayList<PrescriptionTM> prescriptions = prescriptionDAO.getAll();
        ArrayList<PrescriptionTM> prescriptionTMS = new ArrayList<>();
        for (PrescriptionTM prescription : prescriptions) {
            PrescriptionTM prescriptionTM = new PrescriptionTM(
                    prescription.getId(),
                    prescription.getDate(),
                    prescription.getMediDetails(),
                    prescription.getDosage(),
                    prescription.getUserId(),
                    prescription.getDoctorId()
            );
            prescriptionTMS.add(prescriptionTM);
        }
        return prescriptionTMS;
    }

    @Override
    public ArrayList<PrescriptionTM> searchPrescription(String name) throws SQLException {
        return null;
    }

    @Override
    public PrescriptionTM findById(String selectedContact) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        return null;
    }

    @Override
    public boolean savePrescription(PrescriptionTM dto) throws SQLException {
        PrescriptionTM prescription = new PrescriptionTM(
                dto.getId(),
                dto.getMediDetails(),
                dto.getDosage(),
                dto.getDate(),
                dto.getUserId(),
                dto.getDoctorId()
        );
        return prescriptionDAO.save(prescription);
    }

    @Override
    public boolean updatePrescription(PrescriptionTM prescriptionTM) throws SQLException {
        PrescriptionTM prescription = new PrescriptionTM(
                prescriptionTM.getId(),
                prescriptionTM.getMediDetails(),
                prescriptionTM.getDosage(),
                prescriptionTM.getDate(),
                prescriptionTM.getUserId(),
                prescriptionTM.getDoctorId()
        );
        return prescriptionDAO.update(prescription);
    }

    @Override
    public boolean deletePrescription(String id) throws SQLException {
        return prescriptionDAO.delete(id);
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        return 0;
    }
}
