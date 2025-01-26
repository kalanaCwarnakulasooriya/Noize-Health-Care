package lk.ijse.healthcare.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString

public class DoctorFormDto {
    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private int userId;
}
