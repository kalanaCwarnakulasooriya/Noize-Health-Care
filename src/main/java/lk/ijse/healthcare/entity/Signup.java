package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Signup {
    String username;
    String password;
    String address;
    String email;
    String name;
    String contactNumber;
    int role;
}
