package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.ForgetPasswordFormDto;

import java.sql.SQLException;

public interface ForgetPasswordBO extends SuperBO {
    public boolean isChangedUserPassword(ForgetPasswordFormDto forgetPasswordFormDto, String newPassword) throws SQLException;
    public ForgetPasswordFormDto getUserData(String username) throws SQLException;
}
