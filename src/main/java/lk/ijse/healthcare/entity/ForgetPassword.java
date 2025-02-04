package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class ForgetPassword {
    private String id;
    private String username;
    private String password;
    private String email;
    private String phone;
    private int otp;
}
