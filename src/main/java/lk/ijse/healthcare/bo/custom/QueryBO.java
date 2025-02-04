package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.dto.LoginFormDto;
import lk.ijse.healthcare.entity.Login;

import java.sql.ResultSet;

public interface QueryBO extends SignupBO{
    public ResultSet btnLogin(LoginFormDto login) throws Exception;
}
