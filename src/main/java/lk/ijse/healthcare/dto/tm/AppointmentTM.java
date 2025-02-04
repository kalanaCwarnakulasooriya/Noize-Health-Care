package lk.ijse.healthcare.dto.tm;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString

public class AppointmentTM {
    private int id;
    private String age;
    private String status;
    private String description;
    private String date;
    private String doctorId;
    private int userId;
}
