package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;
import lk.ijse.healthcare.entity.Item;
import lk.ijse.healthcare.entity.OrderDetails;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<Item> {
    public boolean reduceQty(OrderDetails orderDetailsFormDto) throws SQLException;
}
