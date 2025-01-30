package lk.ijse.healthcare.dto.tm;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PrescriptionTM {
    private int id;
    private String mediDetails;
    private String dosage;
    private String date;
    private int userId;
    private String doctorId;
//    private String appoId;
//    private String patientId;
}
