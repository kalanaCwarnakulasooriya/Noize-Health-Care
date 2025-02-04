package lk.ijse.healthcare.entity;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class AddPatient {
    private int id;
    private String name;
    private String address;
    private String contactNumber;
    private String email;
    private LocalDate dob;
    private LocalDate regDate;
    private int genderId;
    private int userId;
    private int prescriptionId;
}
