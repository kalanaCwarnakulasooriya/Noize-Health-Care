package lk.ijse.healthcare.bo;

import lk.ijse.healthcare.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;

    private BOFactory() {

    }

    public static BOFactory getInstance(){
        return boFactory == null ? boFactory = new BOFactory() : boFactory;
    }

    public enum BOType{
        ITEM, DOCTOR, PATIENT, APPOINTMENT, PRESCRIPTION, SIGNUP, DASHBOARD, PASSWORD, ADD_PATIENTS, ORDER
    }

    public SuperBO getBO(BOType type) {
        switch (type) {
            case ITEM:
                return new ItemBOImpl();
            case DOCTOR:
                return new DoctorBOImpl();
            case PATIENT:
                return new PatientsBOImpl();
            case APPOINTMENT:
                return new AppointmentBOImpl();
            case PRESCRIPTION:
                return new PrescriptionBOImpl();
            case SIGNUP:
                return new SignupBOImpl();
            case DASHBOARD:
                return new DashboardBOImpl();
            case ADD_PATIENTS:
                return new AddPatientsBOImpl();
            case PASSWORD:
                return new ForgetPasswordBOImpl();
            case ORDER:
                return new OrdersBOImpl();
            default:
                return null;
        }
    }
}
