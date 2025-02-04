package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class Appointment {
    private String age;
    private String status;
    private String description;
    private String date;
    private String doctorId;
    private int userId;
}
