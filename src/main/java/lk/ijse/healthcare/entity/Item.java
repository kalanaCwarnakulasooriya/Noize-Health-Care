package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString

public class Item {
    private int itemId;
    private String name;
    private String description;
    private String expireDate;
    private String packSize;
    private double unitPrice;
    private int stockQty;
}
