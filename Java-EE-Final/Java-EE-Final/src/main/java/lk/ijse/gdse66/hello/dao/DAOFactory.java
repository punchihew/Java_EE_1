//package lk.ijse.gdse66.hello.dao;
//
//
//import lk.ijse.gdse66.hello.dao.custom.impl.CustomerDaoImpl;
//import lk.ijse.gdse66.hello.dao.custom.impl.ItemDAOImpl;
//import lk.ijse.gdse66.hello.dao.custom.impl.OrderDAOImpl;
//
//public class DAOFactory {
//    private static DAOFactory daoFactory;
//
//    private DAOFactory() {
//
//    }
//
//    public static DAOFactory getDAOFactory() {
//        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
//    }
//
//    public enum DAOTypes {
//        CUSTOMER,ITEM,ORDER
//
//    }
//
//    public <T extends SuperDAO> T getDAO(DAOTypes res) {
//        switch (res) {
//            case CUSTOMER:
//                return (T) new CustomerDaoImpl();
//            case ITEM:
//                return (T) new ItemDAOImpl();
//            case ORDER:
//                return (T) new OrderDAOImpl();
////            case ORDER_DETAILS:
////                return (T) new OrderDetailsDAOImpl();
////            case QUERY_DAO:
////                return (T) new QueryDAOImpl();
//            default:
//                return null;
//        }
//    }
//
//
//}

package lk.ijse.gdse66.hello.dao;


import lk.ijse.gdse66.hello.dao.custom.impl.CustomerDaoImpl;
import lk.ijse.gdse66.hello.dao.custom.impl.ItemDAOImpl;
import lk.ijse.gdse66.hello.dao.custom.impl.OrderDAOImpl;
import lk.ijse.gdse66.hello.dao.custom.impl.OrderDetailsDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {

    }

    public static DAOFactory getDAOFactory() {
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }

    public enum DAOTypes {
        CUSTOMER,ITEM,ORDER,ORDER_DETAILS

    }

    public <T extends SuperDAO> T getDAO(DAOTypes res) {
        switch (res) {
            case CUSTOMER:
                return (T) new CustomerDaoImpl();
            case ITEM:
                return (T) new ItemDAOImpl();
            case ORDER:
                return (T) new OrderDAOImpl();
            case ORDER_DETAILS:
                return (T) new OrderDetailsDAOImpl();

            default:
                return null;
        }
    }


}

