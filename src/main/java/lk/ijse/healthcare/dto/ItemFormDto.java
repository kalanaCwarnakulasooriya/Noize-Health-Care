package lk.ijse.healthcare.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class ItemFormDto {
    private int itemId;
    private String name;
    private String description;
    private String expireDate;
    private String packSize;
    private double unitPrice;
    private int stockQty;
}
