package lk.ijse.healthcare.dto.tm;

import lombok.*;

@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class DoctorTM {
    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private int userId;
}
