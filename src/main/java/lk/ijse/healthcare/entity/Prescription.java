package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Prescription {
    private int id;
    private String mediDetails;
    private String dosage;
    private String date;
    private int userId;
    private String doctorId;
}
