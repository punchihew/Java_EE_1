package lk.ijse.gdse66.hello.bo.custom;

import lk.ijse.gdse66.hello.bo.SuperBo;
import lk.ijse.gdse66.hello.dto.ItemDTO;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public interface ItemBO extends SuperBo {
    public ArrayList<ItemDTO> getAllItems(Connection connection) throws SQLException, ClassNotFoundException;

    public boolean saveItem(Connection connection ,ItemDTO itemDTO) throws SQLException, ClassNotFoundException;

    public boolean updateItem(Connection connection,ItemDTO itemDTO) throws SQLException, ClassNotFoundException;
    public boolean deleteItem(Connection connection,String itemCode) throws SQLException, ClassNotFoundException;
}
