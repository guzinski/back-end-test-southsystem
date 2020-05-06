package guzinski.model;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class Seller {
    String documentNumber;
    String name;
    Double salary;
}
