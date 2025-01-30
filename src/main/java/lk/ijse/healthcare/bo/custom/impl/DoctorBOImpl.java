package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.DoctorBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.DoctorDAO;
import lk.ijse.healthcare.dao.custom.impl.DoctorDAOImpl;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.tm.DoctorTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorBOImpl implements DoctorBO {
    DoctorDAO doctorDAO = new DoctorDAOImpl();
    @Override
    public ArrayList<String> getAllSDoctor() throws SQLException {
        ArrayList<String> docIds = doctorDAO.getAllS();
        ArrayList<String> docId = new ArrayList<>();
        for (String doctorTM : docIds) {
            docId.add(doctorTM);
        }
        return docId;
    }

    @Override
    public ArrayList<DoctorTM> getAllDoctor() throws SQLException {
        ArrayList<DoctorTM> doctors = doctorDAO.getAll();
        ArrayList<DoctorTM> doctorTMS = new ArrayList<>();
        for (DoctorTM doctorTM : doctors) {
            DoctorTM newDoctors = new DoctorTM();
            newDoctors.setId(doctorTM.getId());
            newDoctors.setName(doctorTM.getName());
            newDoctors.setEmail(doctorTM.getEmail());
            newDoctors.setContactNumber(doctorTM.getContactNumber());
            newDoctors.setAddress(doctorTM.getAddress());
            newDoctors.setUserId(doctorTM.getUserId());
            doctorTMS.add(newDoctors);
        }
        return doctorTMS;
    }

    @Override
    public ArrayList<DoctorTM> searchDoctor(String name) throws SQLException {
        ArrayList<DoctorTM> doctorTMS = doctorDAO.search(name);
        ArrayList<DoctorTM> doctors = new ArrayList<>();
        for (DoctorTM doctorTM : doctorTMS) {
            DoctorTM newDoctors = new DoctorTM();
            newDoctors.setId(doctorTM.getId());
            newDoctors.setName(doctorTM.getName());
            newDoctors.setEmail(doctorTM.getEmail());
            newDoctors.setContactNumber(doctorTM.getContactNumber());
            newDoctors.setAddress(doctorTM.getAddress());
            newDoctors.setUserId(doctorTM.getUserId());
            doctors.add(newDoctors);
        }
        return doctors;
    }

    @Override
    public DoctorTM findByDoctorId(String selectedName) throws SQLException {
        return doctorDAO.findById(selectedName);
    }

    @Override
    public boolean deleteDoctor(String name) throws SQLException {
        return doctorDAO.delete(name);
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean updateDoctor(DoctorTM doctorFormDto) throws SQLException {
        DoctorTM doctor = new DoctorTM(doctorFormDto.getId(), doctorFormDto.getName(), doctorFormDto.getEmail(), doctorFormDto.getContactNumber(), doctorFormDto.getAddress(), doctorFormDto.getUserId());
        return doctorDAO.update(doctor);
    }

    @Override
    public boolean saveDoctor(DoctorTM doctorFormDto) throws SQLException {
        DoctorTM doctor = new DoctorTM(doctorFormDto.getId(), doctorFormDto.getName(), doctorFormDto.getEmail(), doctorFormDto.getContactNumber(), doctorFormDto.getAddress(), doctorFormDto.getUserId());
        return doctorDAO.save(doctor);
    }
}
