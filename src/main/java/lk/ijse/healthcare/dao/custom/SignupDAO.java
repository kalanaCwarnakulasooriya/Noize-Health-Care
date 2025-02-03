package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.dto.SignupFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SignupDAO extends CrudDAO<SignupFormDto> {
    public int getUserId() throws SQLException;
}
