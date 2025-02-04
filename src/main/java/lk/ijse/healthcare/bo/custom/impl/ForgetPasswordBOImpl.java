package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.ForgetPasswordBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ForgetPasswordDAO;
import lk.ijse.healthcare.dao.custom.impl.ForgetPasswordDAOImpl;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;
import lk.ijse.healthcare.entity.ForgetPassword;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ForgetPasswordBOImpl implements ForgetPasswordBO {
    ForgetPasswordDAO forgetPwd = (ForgetPasswordDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PASSWORD);
    @Override
    public boolean isChangedUserPassword(ForgetPasswordFormDto forget, String newPwd) throws SQLException {
        ForgetPassword fpwd = new ForgetPassword(
                forget.getId(),
                forget.getUsername(),
                forget.getPassword(),
                forget.getEmail(),
                forget.getPhone(),
                forget.getOtp()
        );
        return forgetPwd.changePwd(fpwd, newPwd);
    }

    @Override
    public ForgetPasswordFormDto getUserData(String username) throws SQLException {
        ArrayList<ForgetPassword> dtos = forgetPwd.getAll();
        for (ForgetPassword dto : dtos) {
            if (dto.getUsername().equals(username)) {
                return new ForgetPasswordFormDto(dto.getId(), dto.getUsername(), dto.getPassword(), dto.getEmail(), dto.getPhone(), dto.getOtp());
            }
        }
        return null;
    }
}
