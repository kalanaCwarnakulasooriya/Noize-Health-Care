package lk.ijse.healthcare.dao.custom;

import lk.ijse.healthcare.dao.CrudDAO;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemDAO extends CrudDAO<ItemFormDto> {
    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException;
}
