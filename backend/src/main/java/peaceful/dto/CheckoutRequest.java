package peaceful.dto;

import lombok.Data;

@Data
public class CheckoutRequest {

    private Integer customerId;
    private String couponCode;
}
