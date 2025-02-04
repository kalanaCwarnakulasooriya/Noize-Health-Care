package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.DoctorBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.DoctorDAO;
import lk.ijse.healthcare.dao.custom.impl.DoctorDAOImpl;
import lk.ijse.healthcare.dto.DoctorFormDto;
import lk.ijse.healthcare.dto.tm.DoctorTM;
import lk.ijse.healthcare.entity.Doctor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DoctorBOImpl implements DoctorBO {
    DoctorDAO doctorDAO = (DoctorDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.DOCTOR);
    @Override
    public ArrayList<String> getAllSDoctor() throws SQLException {
        ArrayList<String> docIds = doctorDAO.getAllS();
        ArrayList<String> docId = new ArrayList<>();
        for (String doctorDto : docIds) {
            docId.add(doctorDto);
        }
        return docId;
    }

    @Override
    public ArrayList<DoctorFormDto> getAllDoctor() throws SQLException {
        ArrayList<Doctor> doctors = doctorDAO.getAll();
        ArrayList<DoctorFormDto> doctorDto = new ArrayList<>();
        for (Doctor doctor : doctors) {
            DoctorFormDto dto = new DoctorFormDto();
            dto.setId(doctor.getId());
            dto.setName(doctor.getName());
            dto.setEmail(doctor.getEmail());
            dto.setContactNumber(doctor.getContactNumber());
            dto.setAddress(doctor.getAddress());
            dto.setUserId(doctor.getUserId());
            doctorDto.add(dto);
        }
        return doctorDto;
    }

    @Override
    public ArrayList<DoctorFormDto> searchDoctor(String search) throws SQLException {
        ArrayList<Doctor> doctors = doctorDAO.search(search);
        ArrayList<DoctorFormDto> doctor = new ArrayList<>();
        for (Doctor doctorDto : doctors) {
            DoctorFormDto dto = new DoctorFormDto();
            dto.setId(doctorDto.getId());
            dto.setName(doctorDto.getName());
            dto.setEmail(doctorDto.getEmail());
            dto.setContactNumber(doctorDto.getContactNumber());
            dto.setAddress(doctorDto.getAddress());
            dto.setUserId(doctorDto.getUserId());
            doctor.add(dto);
        }
        return doctor;
    }

    @Override
    public DoctorFormDto findByDoctorId(String id) throws SQLException {
        ArrayList<Doctor> doctors = doctorDAO.search(id);
        for (Doctor doctor : doctors) {
            DoctorFormDto dto = new DoctorFormDto();
            dto.setId(doctor.getId());
            dto.setName(doctor.getName());
            dto.setEmail(doctor.getEmail());
            dto.setContactNumber(doctor.getContactNumber());
            dto.setAddress(doctor.getAddress());
            dto.setUserId(doctor.getUserId());
            return dto;
        }
        return null;
    }

    @Override
    public boolean deleteDoctor(String delete) throws SQLException {
        return doctorDAO.delete(delete);
    }

    @Override
    public int getIdByDescription(String description) throws SQLException {
        return 0;
    }

    @Override
    public boolean updateDoctor(DoctorFormDto update) throws SQLException {
        Doctor doctor = new Doctor(update.getId(), update.getName(), update.getEmail(), update.getContactNumber(), update.getAddress(), update.getUserId());
        return doctorDAO.update(doctor);
    }

    @Override
    public boolean saveDoctor(DoctorFormDto save) throws SQLException {
        Doctor doctor = new Doctor(save.getId(), save.getName(), save.getEmail(), save.getContactNumber(), save.getAddress(), save.getUserId());
        return doctorDAO.save(doctor);
    }
}
