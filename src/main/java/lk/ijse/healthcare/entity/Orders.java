package lk.ijse.healthcare.entity;

import lombok.*;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor

public class Orders {
    private int orderId;
    private Date orderDate;
    private int patientId;

    private ArrayList<OrderDetails> orderDetailsFormDtos;
}
