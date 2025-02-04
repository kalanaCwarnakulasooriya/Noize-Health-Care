package lk.ijse.healthcare.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class PrescriptionFormDto {
    private int id;
    private String mediDetails;
    private String dosage;
    private String date;
    private int userId;
    private String doctorId;
}
