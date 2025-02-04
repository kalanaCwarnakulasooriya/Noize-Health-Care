package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.PrescriptionBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.PrescriptionDAO;
import lk.ijse.healthcare.dao.custom.impl.PrescriptionDAOImpl;
import lk.ijse.healthcare.dto.PrescriptionFormDto;
import lk.ijse.healthcare.dto.tm.PrescriptionTM;
import lk.ijse.healthcare.entity.Prescription;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PrescriptionBOImpl implements PrescriptionBO {
    PrescriptionDAO prescriptionDAO = (PrescriptionDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PRESCRIPTION);
    @Override
    public ArrayList<PrescriptionFormDto> getAllPrescription() throws SQLException {
        ArrayList<Prescription> prescriptions = prescriptionDAO.getAll();
        ArrayList<PrescriptionFormDto> prescriptionDto = new ArrayList<>();
        for (Prescription prescription : prescriptions) {
            PrescriptionFormDto dto = new PrescriptionFormDto(
                    prescription.getId(),
                    prescription.getDate(),
                    prescription.getMediDetails(),
                    prescription.getDosage(),
                    prescription.getUserId(),
                    prescription.getDoctorId()
            );
            prescriptionDto.add(dto);
        }
        return prescriptionDto;
    }

    @Override
    public ArrayList<PrescriptionFormDto> searchPrescription(String search) throws SQLException {
        return null;
    }

    @Override
    public PrescriptionFormDto findById(String id) throws SQLException {
        return null;
    }

    @Override
    public ArrayList<String> getAllS() throws SQLException {
        return null;
    }

    @Override
    public boolean savePrescription(PrescriptionFormDto save) throws SQLException {
        Prescription prescription = new Prescription(
                save.getId(),
                save.getMediDetails(),
                save.getDosage(),
                save.getDate(),
                save.getUserId(),
                save.getDoctorId()
        );
        return prescriptionDAO.save(prescription);
    }

    @Override
    public boolean updatePrescription(PrescriptionFormDto update) throws SQLException {
        Prescription prescription = new Prescription(
                update.getId(),
                update.getMediDetails(),
                update.getDosage(),
                update.getDate(),
                update.getUserId(),
                update.getDoctorId()
        );
        return prescriptionDAO.update(prescription);
    }

    @Override
    public boolean deletePrescription(String delete) throws SQLException {
        return prescriptionDAO.delete(delete);
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        return 0;
    }
}
