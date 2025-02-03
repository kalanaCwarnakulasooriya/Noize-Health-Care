package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.ItemBO;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dao.custom.impl.ItemDAOImpl;
import lk.ijse.healthcare.dto.tm.ItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = new ItemDAOImpl();
    @Override
    public ArrayList<String> getAllSItem() throws SQLException {
        ArrayList<String> itemNames = itemDAO.getAllS();
        ArrayList<String> names = new ArrayList<>();
        for (String name : itemNames) {
            names.add(name);
        }
        return names;
    }

    @Override
    public ItemTM findByItemId(String name) throws SQLException {
        return itemDAO.findById(name);
    }

    //    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException {
//        return SQLUtil.execute("UPDATE item SET StockQuantity = StockQuantity - ? WHERE ItemId = ?", orderDetailsFormDto.getQuantity(), orderDetailsFormDto.getItemId());
//    }
    @Override
    public ArrayList<ItemTM> getAllItem() throws SQLException {
        ArrayList<ItemTM> stock = itemDAO.getAll();
        ArrayList<ItemTM> items = new ArrayList<>();
        for (ItemTM item : stock) {
            ItemTM item1 = new ItemTM();
            item1.setName(item.getName());
            item1.setDescription(item.getDescription());
            item1.setExpireDate(item.getExpireDate());
            item1.setPackSize(item.getPackSize());
            item1.setUnitPrice(item.getUnitPrice());
            item1.setQty(item.getQty());
            items.add(item1);
        }
        return items;
    }

    @Override
    public boolean updateItem(ItemTM item) throws SQLException {
        ItemTM items = new ItemTM(item.getName(), item.getDescription(), item.getExpireDate(), item.getPackSize(), item.getUnitPrice(), item.getQty());
        return itemDAO.update(items);
    }

    @Override
    public boolean saveItem(ItemTM item) throws SQLException {
        ItemTM items = new ItemTM(item.getName(), item.getDescription(), item.getExpireDate(), item.getPackSize(), item.getUnitPrice(), item.getQty());
        return itemDAO.save(items);
    }

    @Override
    public int getIdByItem(String name) throws SQLException {
        return itemDAO.getIdBy(name);
    }

    @Override
    public boolean deleteItem(String name) throws SQLException {
        return itemDAO.delete(name);
    }

    @Override
    public ArrayList<ItemTM> searchItem(String name) throws SQLException {
        ArrayList<ItemTM> items = itemDAO.search(name);
        ArrayList<ItemTM> itemTMS = new ArrayList<>();
        for (ItemTM item : items) {
            ItemTM item1 = new ItemTM(
                    item.getName(),
                    item.getDescription(),
                    item.getExpireDate(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getQty()
            );
            itemTMS.add(item1);
        }
        return itemTMS;
    }
}
