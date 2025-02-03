package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<String> getAllSItem() throws SQLException;
    public ItemTM findByItemId(String name) throws SQLException;
    public ArrayList<ItemTM> getAllItem() throws SQLException;
    public boolean updateItem(ItemTM item) throws SQLException;
    public boolean saveItem(ItemTM item) throws SQLException;
    public int getIdByItem(String name) throws SQLException;
    public boolean deleteItem(String name) throws SQLException;
    public ArrayList<ItemTM> searchItem(String name) throws SQLException;
    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException;

}
