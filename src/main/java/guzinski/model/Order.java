package guzinski.model;

import lombok.Builder;
import lombok.Value;
import org.pcollections.PVector;

@Builder
@Value
public class Order {
    String id;
    PVector<OrderItem> items;
    String sellerName;
}
