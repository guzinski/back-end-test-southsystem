package guzinski.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class OrderItem {
    String id;
    Long quantity;
    Double price;
}
