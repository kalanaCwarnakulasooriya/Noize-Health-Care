package lk.ijse.healthcare.dao;

import lk.ijse.healthcare.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return daoFactory == null ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes {
        ITEM, PATIENT, DOCTOR, APPOINTMENT, ORDER, PRESCRIPTION, SIGNUP, DASHBOARD, ADDPATIENTS, PASSWORD, ORDER_DETAILS, QUERY
    }

    public SuperDAO getDAO(DAOTypes types) {
        switch (types) {
            case ITEM:
                return new ItemDAOImpl();
            case PATIENT:
                return new PatientsDAOImpl();
            case DOCTOR:
                return new DoctorDAOImpl();
            case APPOINTMENT:
                return new AppointmentDAOImpl();
            case ORDER:
                return new OrdersDAOImpl();
            case PRESCRIPTION:
                return new PrescriptionDAOImpl();
            case SIGNUP:
                return new SignupDAOImpl();
            case DASHBOARD:
                return new DashboardDAOImpl();
            case ADDPATIENTS:
                return new AddPatientsDAOImpl();
            case PASSWORD:
                return new ForgetPasswordDAOImpl();
            case ORDER_DETAILS:
                return new OrderDetailsDAOImpl();
            case QUERY:
                return new QueryDAOImpl();
            default:
                return null;
        }
    }
}