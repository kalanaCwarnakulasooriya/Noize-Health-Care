package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.ForgetPasswordBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ForgetPasswordDAO;
import lk.ijse.healthcare.dao.custom.impl.ForgetPasswordDAOImpl;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForgetPasswordBOImpl implements ForgetPasswordBO {
    ForgetPasswordDAO forgetPwd = new ForgetPasswordDAOImpl();
    public boolean isChangedUserPassword(ForgetPasswordFormDto forgetPasswordFormDto, String newPassword) throws SQLException {
        return forgetPwd.changePwd(forgetPasswordFormDto, newPassword);
    }

    public ForgetPasswordFormDto getUserData(String username) throws SQLException {
        ArrayList<ForgetPasswordFormDto> forgetPasswordFormDtos = forgetPwd.getAll();
        for (ForgetPasswordFormDto forgetPasswordFormDto : forgetPasswordFormDtos) {
            if (forgetPasswordFormDto.getUsername().equals(username)) {
                return forgetPasswordFormDto;
            }
        }
        return null;
    }
}
