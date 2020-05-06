package guzinski.model;

import lombok.Builder;
import lombok.Value;
import org.pcollections.PVector;

@Builder
@Value
public class FileInputData {
    String fileName;
    PVector<Seller> sellers;
    PVector<Costumer> costumers;
    PVector<Order> orders;
}
