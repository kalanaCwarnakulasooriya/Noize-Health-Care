package lk.ijse.healthcare.entity;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Patients {
    private String patientsName;
    private String patientsAddress;
    private String patientsContactNumber;
    private String patientsEmail;
    private Date patientsDob;
    private String patientsGender;
    private Date patientsRegDate;
}
