package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Setter
@ToString

public class Doctor {
    private int id;
    private String name;
    private String email;
    private String contactNumber;
    private String address;
    private int userId;
}
