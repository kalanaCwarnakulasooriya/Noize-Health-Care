package lk.ijse.healthcare.bo.custom;

import lk.ijse.healthcare.bo.SuperBO;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;

import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBO {
    public ArrayList<String> getAllSItem() throws SQLException;
    public ItemFormDto findByItemId(String id) throws SQLException;
    public ArrayList<ItemFormDto> getAllItem() throws SQLException;
    public boolean updateItem(ItemFormDto update) throws SQLException;
    public boolean saveItem(ItemFormDto save) throws SQLException;
    public int getIdByItem(String id) throws SQLException;
    public boolean deleteItem(String delete) throws SQLException;
    public ArrayList<ItemFormDto> searchItem(String search) throws SQLException;
    public boolean reduceQty(OrderDetailsFormDto order) throws SQLException;

}
