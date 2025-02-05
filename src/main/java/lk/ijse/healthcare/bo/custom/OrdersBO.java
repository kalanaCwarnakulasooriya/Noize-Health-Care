package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.OrdersFormDto;

import java.sql.SQLException;
import java.util.ArrayList;

public interface OrdersBO extends SuperBO {
    public String getNewOrderId() throws SQLException;
    public boolean saveOrder(OrdersFormDto save) throws SQLException;
    public boolean save(OrderDetailsFormDto save) throws SQLException;
    public double getOrder(String orderId) throws SQLException;
}
