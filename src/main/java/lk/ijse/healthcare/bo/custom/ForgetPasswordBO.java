package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;
import lk.ijse.healthcare.entity.ForgetPassword;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ForgetPasswordBO extends SuperBO {
    public boolean isChangedUserPassword(ForgetPasswordFormDto forget, String newPwd) throws SQLException;
    public ForgetPasswordFormDto getUserData(String username) throws SQLException;
}
