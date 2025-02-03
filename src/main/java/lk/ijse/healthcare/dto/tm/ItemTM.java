package lk.ijse.healthcare.dto.tm;

import lombok.*;

import javafx.scene.image.ImageView;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor

public class ItemTM  {
    private String name;
    private String description;
    private String expireDate;
    private String packSize;
    private double unitPrice;
    private int qty;
//    private ImageView type;
}
