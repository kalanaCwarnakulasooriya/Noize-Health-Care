package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginFormDto {
    private String username;
    private String password;
}
