package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.ForgetPasswordBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ForgetPasswordDAO;
import lk.ijse.healthcare.dao.custom.impl.ForgetPasswordDAOImpl;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForgetPasswordBOImpl implements ForgetPasswordBO {
    ForgetPasswordDAO forgetPwd = (ForgetPasswordDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PASSWORD);
    @Override
    public boolean isChangedUserPassword(ForgetPasswordFormDto forget, String newPwd) throws SQLException {
        return forgetPwd.changePwd(forget, newPwd);
    }

    @Override
    public ForgetPasswordFormDto getUserData(String username) throws SQLException {
        ArrayList<ForgetPasswordFormDto> dtos = forgetPwd.getAll();
        for (ForgetPasswordFormDto dto : dtos) {
            if (dto.getUsername().equals(username)) {
                return dto;
            }
        }
        return null;
    }
}
