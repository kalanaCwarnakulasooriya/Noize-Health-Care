package lk.ijse.healthcare.bo.custom.impl;

import lk.ijse.healthcare.bo.custom.ItemBO;
import lk.ijse.healthcare.dao.DAOFactory;
import lk.ijse.healthcare.dao.SQLUtil;
import lk.ijse.healthcare.dao.custom.ItemDAO;
import lk.ijse.healthcare.dao.custom.impl.ItemDAOImpl;
import lk.ijse.healthcare.dto.ItemFormDto;
import lk.ijse.healthcare.dto.OrderDetailsFormDto;
import lk.ijse.healthcare.dto.tm.ItemTM;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ItemBOImpl implements ItemBO {
    ItemDAO itemDAO = (ItemDAO) DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.ITEM);
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
    public ItemFormDto findByItemId(String name) throws SQLException {
        return itemDAO.findById(name);
    }

    public boolean reduceQty(OrderDetailsFormDto orderDetailsFormDto) throws SQLException {
        return itemDAO.reduceQty(orderDetailsFormDto);
    }
    @Override
    public ArrayList<ItemFormDto> getAllItem() throws SQLException {
        ArrayList<ItemFormDto> stock = itemDAO.getAll();
        ArrayList<ItemFormDto> items = new ArrayList<>();
        for (ItemFormDto item : stock) {
            ItemFormDto item1 = new ItemFormDto();
            item1.setItemId(item.getItemId());
            item1.setName(item.getName());
            item1.setDescription(item.getDescription());
            item1.setExpireDate(item.getExpireDate());
            item1.setPackSize(item.getPackSize());
            item1.setUnitPrice(item.getUnitPrice());
            item1.setStockQty(item.getStockQty());
            items.add(item1);
        }
        return items;
    }

    @Override
    public boolean updateItem(ItemFormDto item) throws SQLException {
        ItemFormDto items = new ItemFormDto(item.getItemId(),item.getName(), item.getDescription(), item.getExpireDate(), item.getPackSize(), item.getUnitPrice(), item.getStockQty());
        return itemDAO.update(items);
    }

    @Override
    public boolean saveItem(ItemFormDto item) throws SQLException {
        ItemFormDto items = new ItemFormDto(item.getItemId(),item.getName(), item.getDescription(), item.getExpireDate(), item.getPackSize(), item.getUnitPrice(), item.getStockQty());
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
    public ArrayList<ItemFormDto> searchItem(String name) throws SQLException {
        ArrayList<ItemFormDto> items = itemDAO.search(name);
        ArrayList<ItemFormDto> itemTMS = new ArrayList<>();
        for (ItemFormDto item : items) {
            ItemFormDto item1 = new ItemFormDto(
                    item.getItemId(),
                    item.getName(),
                    item.getDescription(),
                    item.getExpireDate(),
                    item.getPackSize(),
                    item.getUnitPrice(),
                    item.getStockQty()
            );
            itemTMS.add(item1);
        }
        return itemTMS;
    }
}
