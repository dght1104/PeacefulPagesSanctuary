package peaceful.dto;

import lombok.Data;

@Data
public class CartRequest {

    private Integer customerId;
    private Integer productId;
    private Integer quantity;
}
