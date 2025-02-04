package lk.ijse.healthcare.entity;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString

public class OrderDetailsFormDto {
    private int orderId;
    private int itemId;
    private double quantity;
    private double price;
}
