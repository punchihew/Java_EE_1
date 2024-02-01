package lk.ijse.gdse66.hello.dao.custom.impl;


import lk.ijse.gdse66.hello.dao.SQLUtil;
import lk.ijse.gdse66.hello.dao.custom.OrderDetailsDAO;
import lk.ijse.gdse66.hello.entity.OrderDetail;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

public class OrderDetailsDAOImpl implements OrderDetailsDAO {
    @Override
    public ArrayList<OrderDetail> getAll(Connection connection) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public boolean save(Connection connection, OrderDetail entity) throws SQLException, ClassNotFoundException {
        return SQLUtil.execute(connection,"INSERT INTO order_detail VALUES (?,?,?,?)",entity.getItemCode(), entity.getOrderID(),entity.getQty(),entity.getUnitPrice());

    }

    @Override
    public boolean update(Connection connection, OrderDetail entity) throws SQLException, ClassNotFoundException {
        return false;
    }

    @Override
    public boolean delete(Connection connection, String Id) throws SQLException, ClassNotFoundException {
        return false;
    }
}
